/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface_layer;

import Data_layer.ConnectBD;
import static Data_layer.ConnectBD.conn;
import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import static oracle.jdbc.OracleTypes.BLOB;
import oracle.sql.BLOB;

/**
 *
 * @author andreramos
 */
public class ProjectoBD {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws SQLException, IOException {
        ConnectBD.startBD();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Especies where ne='Perdiz'");
        if (rs.next()) {

            Blob blob = rs.getBlob(4);

            System.out.println(rs.getString(1));

            int bBufLen = 4 * 8192;

            OutputStream fwriter = new FileOutputStream("cccc.jpg");
            InputStream in = blob.getBinaryStream();
            int length = -1;
            byte[] buf = new byte[bBufLen];
            while ((length = in.read(buf)) != -1) {
                fwriter.write(buf, 0, length);
            }
            in.close();
            fwriter.close();
            
            File file = new File("cccc.jpg");
            
            Desktop.getDesktop().open(file);
        }
        rs.close();
    }

}
