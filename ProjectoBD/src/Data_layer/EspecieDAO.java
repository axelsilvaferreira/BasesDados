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
import javax.swing.JOptionPane;

/**
 *
 * @author andreramos
 */
public class EspecieDAO extends Observable {
    
    public int size() {
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select count(*) from Especies");
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
        
            public Especie get(Object key) {
        Especie especie = null;
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select * from Especies where ne='" + (String) key + "'");
            if (rs.next()) {
                especie = new Especie(rs.getString(1), rs.getInt(2), rs.getString(3));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return especie;
    }
    
        public Especie[] getAll() {
        Especie[] lista = new Especie[this.size()];
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select * from Especies order by ne");
            for (int i = 0; rs.next(); i++) {
                lista[i] = new Especie(rs.getString(1), rs.getInt(2), rs.getString(3));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }
    
}
