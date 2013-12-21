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
import javax.swing.JOptionPane;

/**
 *
 * @author andreramos
 */
public class CodPostalDAO extends Observable {

    public int size() {
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select count(*) from Cod_Postal");
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

    public boolean containsKey(Object key) {
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select * from Cod_Postal where cp='" + (String) key + "'");
            return rs.next();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean containsValue(Object value) {
        CodPostal codPostal = (CodPostal) value;
        return this.containsKey(codPostal.getCodigo());
    }

    public CodPostal get(Object key) {
        CodPostal codPostal = null;
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select * from Cod_Postal where cp='" + (String) key + "'");
            if (rs.next()) {
                codPostal = new CodPostal(rs.getString(1), rs.getString(2), rs.getString(3));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return codPostal;
    }
    
        public CodPostal[] getAll() {
        CodPostal[] lista = new CodPostal[this.size()];
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select * from Cod_Postal order by cp");
            for (int i = 0; rs.next(); i++) {
                lista[i] = new CodPostal(rs.getString(1), rs.getString(2), rs.getString(3));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

    public int put(CodPostal value) {
        int res = -1;
        try {
            //Se existir temos de fazer update, mas isso é na base de dados
            Statement stm = conn.createStatement();
            String sql = "INSERT INTO cod_postal VALUES('" + value.getCodigo()
                    + "','" + value.getConcelho() + "','" + value.getFreguesia() + "')";
            res = stm.executeUpdate(sql);
            this.setChanged();
            this.notifyObservers();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return res;
    }

    public int remove(Object key) {
        int res = -1;
        try {
            Statement stm = conn.createStatement();
            res = stm.executeUpdate("Delete from Cod_Postal where cp='" + (String) key + "'");
            this.setChanged();
            this.notifyObservers();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return res;
    }

    public int clear() {
        int res = -1;
        try {
            Statement stm = conn.createStatement();
            res = stm.executeUpdate("Delete from Cod_Postal");
            this.setChanged();
            this.notifyObservers();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return res;
    }

}
