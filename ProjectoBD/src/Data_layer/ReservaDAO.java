/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_layer;

import Business_layer.Reserva;
import Business_layer.myDate;
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
public class ReservaDAO extends Observable {

    public int size() {
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select count(*) from Reservas");
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

    public Reserva get(Object key) {
        Reserva reserva = null;
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select r.*, EXTRACT(YEAR FROM dn), "
                    + "EXTRACT(MONTH FROM dn), EXTRACT(DAY FROM dn) "
                    + "from Reservas r where nr='" + (long) key + "' order by nr");
            if (rs.next()) {
                reserva = new Reserva(rs.getLong(1),
                        new myDate(rs.getInt(5), rs.getInt(6), rs.getInt(7)), rs.getLong(3), rs.getString(4));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return reserva;
    }

    public Reserva[] getAll() {
        Reserva[] lista = new Reserva[this.size()];
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("Select r.*, EXTRACT(YEAR FROM data), "
                    + "EXTRACT(MONTH FROM data), EXTRACT(DAY FROM data) from Reservas r order by nr");
            for (int i = 0; rs.next(); i++) {
                lista[i] = new Reserva(rs.getLong(1),
                        new myDate(rs.getInt(5), rs.getInt(6), rs.getInt(7)), rs.getLong(3), rs.getString(4));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return lista;
    }

}
