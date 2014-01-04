package Interface_layer;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;


public class Fotos {

    public static void abrirFoto(String nome, Blob blob) throws FileNotFoundException, SQLException, IOException {
        int bBufLen = 4 * 8192;
        OutputStream fwriter = new FileOutputStream(nome + ".jpg");
        InputStream in = blob.getBinaryStream();
        int length = -1;
        byte[] buf = new byte[bBufLen];
        while ((length = in.read(buf)) != -1) {
            fwriter.write(buf, 0, length);
        }
        in.close();
        fwriter.close();
        File file = new File( nome + ".jpg");
        Desktop.getDesktop().open(file);
    }
}
