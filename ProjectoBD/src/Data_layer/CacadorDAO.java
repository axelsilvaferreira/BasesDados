/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_layer;

import Business_layer.Cacador;
import Business_layer.myDate;
import static Data_layer.ConnectBD.conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;

/**
 *
 * @author andreramos
 */
public class CacadorDAO extends Observable {

    public int size() throws SQLException {

        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select count(*) from Cacadores");
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
        ResultSet rs = stm.executeQuery("Select * from Cacador where nc='" + (long) key + "'");
        return rs.next();

    }

    public boolean containsValue(Object value) throws SQLException {
        Cacador cacador = (Cacador) value;
        return this.containsKey(cacador.getNumero());
    }

    public Cacador get(Object key) throws SQLException {
        Cacador cacador = null;

        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select c.*, EXTRACT(YEAR FROM dn), "
                + "EXTRACT(MONTH FROM dn), EXTRACT(DAY FROM dn) "
                + "from cacadores c where nc='" + (long) key + "' order by n");

        if (rs.next()) {
            cacador = new Cacador(rs.getLong(1), rs.getString(2), rs.getString(3),
                    new myDate(rs.getInt(9), rs.getInt(10), rs.getInt(11)),
                    rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8));
        }

        return cacador;
    }

    public Cacador[] getAll() throws SQLException {
        Cacador[] lista = new Cacador[this.size()];

        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select c.*, EXTRACT(YEAR FROM dn), "
                + "EXTRACT(MONTH FROM dn), EXTRACT(DAY FROM dn) from cacadores c");

        for (int i = 0; rs.next(); i++) {
            lista[i] = new Cacador(rs.getLong(1), rs.getString(2), rs.getString(3),
                    new myDate(rs.getInt(9), rs.getInt(10), rs.getInt(11)),
                    rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8));

        }

        return lista;
    }

    public int put(Cacador value) throws SQLException {
        //Se existir temos de fazer update, mas isso é na base de dados
        Statement stm = conn.createStatement();
        myDate data = value.getDataNasc();
        String sql = "INSERT INTO Cacadores VALUES('" + value.getNumero()
                + "','" + value.getNome() + "','" + value.getBI()
                + "',to_date('" + data.ano + "-" + data.mes + "-" + data.dia + "','yyyy-mm-dd')"
                + ",'" + value.getCodPostal() + "','" + value.getTelefone() + "','" + value.getMail()
                + "','" + value.getPass() + "')";

        int res = stm.executeUpdate(sql);
        this.setChanged();
        this.notifyObservers();

        return res;
    }

    public int remove(Object key) throws SQLException {
        Statement stm = conn.createStatement();
        int res = stm.executeUpdate("Delete from Cacadores where nc='" + (String) key + "'");
        this.setChanged();
        this.notifyObservers();

        return res;
    }

    public int clear() throws SQLException {
        Statement stm = conn.createStatement();
        int res = stm.executeUpdate("Delete from Cacadores");
        this.setChanged();
        this.notifyObservers();
        return res;
    }

}
