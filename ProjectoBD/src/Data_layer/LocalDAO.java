/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_layer;

import Business_layer.Local;
import static Data_layer.ConnectBD.conn;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author andreramos
 */
public class LocalDAO extends Observable {

    public LocalDAO() {
    }

    public int size() throws SQLException {

        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select count(nl) from Locais");
        if (rs.next()) {
            return rs.getInt(1);
        }

        return 0;
    }

    public boolean isEmpty() throws SQLException {
        return this.size() == 0;
    }

    public int sizeLEspecies(Object key) throws SQLException {
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select count(*) from Loc_Especie where nl='" + (String) key + "'");
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public Local get(Object key) throws SQLException {
        Local local = null;
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select nl,cp,pl,l,dl from Locais where nl='" + (String) key + "'");
        if (rs.next()) {
            local = new Local(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getString(5), getLEspecies(key));
        }
        return local;
    }

    public ArrayList<String> getLEspecies(Object key) throws SQLException {
        ArrayList<String> lista = new ArrayList<>();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from Loc_Especie where nl='" + (String) key + "'");
        while (rs.next()) {
            lista.add(rs.getString(2));
        }
        return lista;
    }

    public Local[] getAll() throws SQLException {
        Local[] lista = new Local[this.size()];
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select nl,cp,pl,l,dl from Locais order by nl");
        for (int i = 0; rs.next(); i++) {
            lista[i] = new Local(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getString(5), getLEspecies(rs.getString(1)));
        }
        return lista;
    }

    public int put(Local value) throws SQLException {
        Statement stm = conn.createStatement();
        String sql = "INSERT INTO Locais VALUES('" + value.getNome()
                + "','" + value.getCodPostal() + "'," + value.getPreco()
                + "," + value.getLimite() + ",'" + value.getDesc() + "','')";
        int res = stm.executeUpdate(sql);
        putLEspecies(value.getNome(), value.getEspecies());

        this.setChanged();
        this.notifyObservers();
        return res;
    }

    public int update(Local value, String key) throws SQLException {
        Statement stm = conn.createStatement();
        String sql = "Update Locais set nl='" + value.getNome()
                + "', cp='" + value.getCodPostal() + "', pl=" + value.getPreco()
                + ", l=" + value.getLimite()
                + ", dl='" + value.getDesc()
                + "' where nl ='" + key + "'";
        int res = stm.executeUpdate(sql);
        removeLEspecies(value.getNome());
        putLEspecies(value.getNome(), value.getEspecies());
        this.setChanged();
        this.notifyObservers();
        return res;
    }

    public void putLEspecies(String nl, ArrayList<String> lNe) throws SQLException {
        Statement stm = conn.createStatement();
        for (String ne : lNe) {
            String sql = "INSERT INTO Loc_Especie VALUES('" + nl + "','" + ne + "')";
            stm.executeUpdate(sql);
        }
        this.setChanged();
        this.notifyObservers();
    }

    public int removeLEspecies(Object key) throws SQLException {
        Statement stm = conn.createStatement();
        int res = stm.executeUpdate("Delete from Loc_Especie where nl='" + (String) key + "'");
        this.setChanged();
        this.notifyObservers();
        return res;
    }

    public void putFoto(String key, String caminho) throws SQLException, FileNotFoundException {
        PreparedStatement pstmt = conn.prepareStatement("Update Locais set Foto=? where nl ='" + key + "'");
        File fBlob = new File(caminho);
        FileInputStream is = new FileInputStream(fBlob);
        pstmt.setBinaryStream(1, is, (int) fBlob.length());
        pstmt.execute();

    }

    public int remove(Object key) throws SQLException {
        Statement stm = conn.createStatement();
        int res = stm.executeUpdate("Delete from Locais where nl='" + (String) key + "'");
        this.setChanged();
        this.notifyObservers();
        return res;
    }

    public int removeFoto(String key) throws SQLException {
        Statement stm = conn.createStatement();
        return stm.executeUpdate(("Update Locais set Foto='' where nl ='" + key + "'"));
    }

    public Blob getFoto(String key) throws SQLException {
        ConnectBD.startBD();
        Blob blob = null;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Locais where nl='" + key + "'");
        if (rs.next()) {
            blob = rs.getBlob(6);
        }
        return blob;
    }

}
