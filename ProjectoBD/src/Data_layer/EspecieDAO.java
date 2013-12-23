/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_layer;

import Business_layer.Especie;
import static Data_layer.ConnectBD.conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;

/**
 *
 * @author andreramos
 */
public class EspecieDAO extends Observable {
    
    public EspecieDAO(){}

    public int size() throws SQLException {

        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select count(ne) from Especies");
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public boolean isEmpty() throws SQLException {
        return this.size() == 0;
    }

    public Especie get(Object key) throws SQLException {
        Especie especie = null;
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select ne,nm,de from Especies where ne='" + (String) key + "'");
        if (rs.next()) {
            especie = new Especie(rs.getString(1), rs.getInt(2), rs.getString(3));
        }
        return especie;
    }

    public Especie[] getAll() throws SQLException {
        Especie[] lista = new Especie[this.size()];
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select ne,nm,de from Especies order by ne");
        for (int i = 0; rs.next(); i++) {
            lista[i] = new Especie(rs.getString(1), rs.getInt(2), rs.getString(3));
        }
        return lista;
    }

    public int remove(Object key) throws SQLException {
        Statement stm = conn.createStatement();
        int res = stm.executeUpdate("Delete from Especies where ne='" + (String) key + "'");
        this.setChanged();
        this.notifyObservers();
        return res;
    }

}
