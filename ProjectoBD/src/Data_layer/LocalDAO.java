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
import javax.swing.JOptionPane;

/**
 *
 * @author andreramos
 */
public class LocalDAO extends Observable {

    public int size() {
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select count(*) from Locais");
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public Local get(Object key) {
        Local local = null;
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select * from Locais where nl='" + (String) key + "'");
            if (rs.next()) {
                local = new Local(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getString(5));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return local;
    }

    public Local[] getAll() {
        Local[] lista = new Local[this.size()];
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select * from Locais order by nl");
            for (int i = 0; rs.next(); i++) {
                lista[i] = new Local(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getString(5));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

}
