/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_layer;

import Business_layer.Local;
import static Data_layer.ConnectBD.conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;

/**
 *
 * @author andreramos
 */
public class LocalDAO extends Observable {
    
    public LocalDAO(){}

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
    
    public String[] getLEspecies(Object key) throws SQLException {
        String[] lista = new String[sizeLEspecies(key)];
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select * from Loc_Especie where nl='" + (String) key + "'");
        for (int i = 0; rs.next(); i++) {
            lista[i] = rs.getString(2);
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

    public int remove(Object key) throws SQLException {
        Statement stm = conn.createStatement();
        int res = stm.executeUpdate("Delete from Locais where nl='" + (String) key + "'");
        this.setChanged();
        this.notifyObservers();
        return res;
    }

}
