/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_layer;

import static Data_layer.ConnectBD.conn;
import Business_layer.CodPostal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;

/**
 *
 * @author andreramos
 */
public class CodPostalDAO extends Observable {
    
    public CodPostalDAO(){}

    public int size() throws SQLException {

        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select count(*) from Cod_Postal");
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public boolean isEmpty() throws SQLException {
        return this.size() == 0;
    }

    public boolean containsKey(Object key) throws SQLException {
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from Cod_Postal where cp='" + (String) key + "'");
        return rs.next();
    }

    public boolean containsValue(Object value) throws SQLException {
        CodPostal codPostal = (CodPostal) value;
        return this.containsKey(codPostal.getCodigo());
    }

    public CodPostal get(Object key) throws SQLException {
        CodPostal codPostal = null;
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from Cod_Postal where cp='" + (String) key + "'");
        if (rs.next()) {
            codPostal = new CodPostal(rs.getString(1), rs.getString(2), rs.getString(3));
        }
        return codPostal;
    }

    public CodPostal[] getAll() throws SQLException {
        CodPostal[] lista = new CodPostal[this.size()];
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from Cod_Postal order by cp");
        for (int i = 0; rs.next(); i++) {
            lista[i] = new CodPostal(rs.getString(1), rs.getString(2), rs.getString(3));
        }
        return lista;
    }

    public int put(CodPostal value) throws SQLException {
        Statement stm = conn.createStatement();
        String sql = "INSERT INTO cod_postal VALUES('" + value.getCodigo()
                + "','" + value.getConcelho() + "','" + value.getFreguesia() + "')";
        int res = stm.executeUpdate(sql);
        this.setChanged();
        this.notifyObservers();
        return res;
    }

    public int update(CodPostal value, String key) throws SQLException {
        Statement stm = conn.createStatement();
        String sql = "Update cod_postal set cp='" + value.getCodigo()
                + "', c='" + value.getConcelho() + "', f='" + value.getFreguesia()
                + "' where cp ='" + key + "'";
        int res = stm.executeUpdate(sql);
        this.setChanged();
        this.notifyObservers();
        return res;
    }

    public int remove(Object key) throws SQLException {
        Statement stm = conn.createStatement();
        int res = stm.executeUpdate("Delete from Cod_Postal where cp='" + (String) key + "'");
        this.setChanged();
        this.notifyObservers();
        return res;
    }

    public int clear() throws SQLException {
        Statement stm = conn.createStatement();
        int res = stm.executeUpdate("Delete from Cod_Postal");
        return res;
    }

}
