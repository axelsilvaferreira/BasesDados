/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author andreramos
 */
public class ConnectBD {

    public static Connection conn;

    public static void startBD() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            ConnectBD.conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "projectobd", "projectobd");

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public static void closeBD() {
        try {
            ConnectBD.conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
