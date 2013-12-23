/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_layer;

import Business_layer.Reserva;
import Business_layer.MyDate;
import static Data_layer.ConnectBD.conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;

/**
 *
 * @author andreramos
 */
public class ReservaDAO extends Observable {

    public ReservaDAO() {
    }

    public int size() throws SQLException {
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select count(*) from Reservas");
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public boolean isEmpty() throws SQLException {
        return this.size() == 0;
    }

    public long gerarKey() throws SQLException {
        Statement stm = conn.createStatement();
        long numero = 1;
        ResultSet rs = stm.executeQuery("Select gerarNumReserva from reservas");
        if (rs.next()) {
            numero = rs.getLong(1);
        }
        return numero;
    }

    public int put(Reserva value) throws SQLException {
        Statement stm = conn.createStatement();
        String sql = "INSERT INTO Reservas VALUES('" + this.gerarKey()
                + "',to_date('" + value.getData().toString() + "','yyyy-mm-dd'),'" + value.getNumCacador() + "','" + value.getLocal() + "')";
        int res = stm.executeUpdate(sql);
        this.setChanged();
        this.notifyObservers();
        return res;
    }

    public int update(Reserva value, Long key) throws SQLException {
        Statement stm = conn.createStatement();
        String sql = "Update Reservas set data = to_date('" + value.getData().toString() + "','yyyy-mm-dd'), nc = "
                + value.getNumCacador() + ", nl = '" + value.getLocal() + "' where nr ='" + key + "'";
        int res = stm.executeUpdate(sql);
        this.setChanged();
        this.notifyObservers();
        return res;
    }

    public Reserva get(Object key) throws SQLException {
        Reserva reserva = null;
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select Reservas.*, EXTRACT(YEAR FROM data), "
                + "EXTRACT(MONTH FROM data), EXTRACT(DAY FROM data) from Reservas "
                + "where nr=" + (long) key + "");
        if (rs.next()) {
            reserva = new Reserva(rs.getLong(1),
                    new MyDate(rs.getInt(5), rs.getInt(6), rs.getInt(7)), rs.getLong(3), rs.getString(4));
        }
        return reserva;
    }

    public Reserva[] getAll() throws SQLException {
        Reserva[] lista = new Reserva[this.size()];
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Select r.*, EXTRACT(YEAR FROM data), "
                + "EXTRACT(MONTH FROM data), EXTRACT(DAY FROM data) from Reservas r order by nr");
        for (int i = 0; rs.next(); i++) {
            lista[i] = new Reserva(rs.getLong(1),
                    new MyDate(rs.getInt(5), rs.getInt(6), rs.getInt(7)), rs.getLong(3), rs.getString(4));
        }
        return lista;
    }

    public int remove(Object key) throws SQLException {
        Statement stm = conn.createStatement();
        int res = stm.executeUpdate("Delete from Reservas where nr='" + (long) key + "'");
        this.setChanged();
        this.notifyObservers();
        return res;
    }

}
