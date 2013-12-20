/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface_layer;

import java.util.Observable;
import java.util.Observer;
import Data_layer.*;

/**
 *
 * @author andreramos
 */
public class JProjectoBD extends javax.swing.JFrame implements Observer {

    /**
     * Creates new form JProjectoBD
     */
    ConnectBD connection;
    CodPostalDAO codPostalDAO;

    public JProjectoBD() {
        initComponents();
        connection.startBD();
        this.codPostalDAO = new CodPostalDAO();
        this.codPostalDAO.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPEspecie = new javax.swing.JPanel();
        jPLocal = new javax.swing.JPanel();
        jPReserva = new javax.swing.JPanel();
        jPCacador = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCacador = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListCacador = new javax.swing.JList();
        jPCodPostal = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListCodPostal = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTFCodPostal = new javax.swing.JTextField();
        jTFConcelho = new javax.swing.JTextField();
        jTFFreguesia = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPEspecieLayout = new javax.swing.GroupLayout(jPEspecie);
        jPEspecie.setLayout(jPEspecieLayout);
        jPEspecieLayout.setHorizontalGroup(
            jPEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 687, Short.MAX_VALUE)
        );
        jPEspecieLayout.setVerticalGroup(
            jPEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 434, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Espécies", jPEspecie);

        javax.swing.GroupLayout jPLocalLayout = new javax.swing.GroupLayout(jPLocal);
        jPLocal.setLayout(jPLocalLayout);
        jPLocalLayout.setHorizontalGroup(
            jPLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 687, Short.MAX_VALUE)
        );
        jPLocalLayout.setVerticalGroup(
            jPLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 434, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Locais", jPLocal);

        javax.swing.GroupLayout jPReservaLayout = new javax.swing.GroupLayout(jPReserva);
        jPReserva.setLayout(jPReservaLayout);
        jPReservaLayout.setHorizontalGroup(
            jPReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 687, Short.MAX_VALUE)
        );
        jPReservaLayout.setVerticalGroup(
            jPReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 434, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Reservas", jPReserva);

        jTableCacador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"2", "André"},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Numero", "Nome"
            }
        ));
        jScrollPane2.setViewportView(jTableCacador);

        jListCacador.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListCacador);

        javax.swing.GroupLayout jPCacadorLayout = new javax.swing.GroupLayout(jPCacador);
        jPCacador.setLayout(jPCacadorLayout);
        jPCacadorLayout.setHorizontalGroup(
            jPCacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCacadorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(245, Short.MAX_VALUE))
        );
        jPCacadorLayout.setVerticalGroup(
            jPCacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCacadorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPCacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Caçadores", jPCacador);

        jListCodPostal.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jListCodPostal);

        jButton1.setText("Editar");

        jButton2.setText("Gravar");

        jButton3.setText("Apagar");

        jLabel1.setText("Codigo Postal");

        jLabel2.setText("Concelho");

        jLabel3.setText("Freguesia");

        javax.swing.GroupLayout jPCodPostalLayout = new javax.swing.GroupLayout(jPCodPostal);
        jPCodPostal.setLayout(jPCodPostalLayout);
        jPCodPostalLayout.setHorizontalGroup(
            jPCodPostalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCodPostalLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addGroup(jPCodPostalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPCodPostalLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3))
                    .addComponent(jTFConcelho)
                    .addGroup(jPCodPostalLayout.createSequentialGroup()
                        .addGroup(jPCodPostalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(jPCodPostalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTFCodPostal, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTFFreguesia))
                .addGap(143, 143, 143))
        );
        jPCodPostalLayout.setVerticalGroup(
            jPCodPostalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCodPostalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPCodPostalLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFCodPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(1, 1, 1)
                .addComponent(jTFConcelho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(4, 4, 4)
                .addComponent(jTFFreguesia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                .addGroup(jPCodPostalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(25, 25, 25))
        );

        jTabbedPane1.addTab("Codigo Postal", jPCodPostal);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);
        jTabbedPane1.getAccessibleContext().setAccessibleName("");
        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JProjectoBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JProjectoBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JProjectoBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JProjectoBD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JProjectoBD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jListCacador;
    private javax.swing.JList jListCodPostal;
    private javax.swing.JPanel jPCacador;
    private javax.swing.JPanel jPCodPostal;
    private javax.swing.JPanel jPEspecie;
    private javax.swing.JPanel jPLocal;
    private javax.swing.JPanel jPReserva;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTFCodPostal;
    private javax.swing.JTextField jTFConcelho;
    private javax.swing.JTextField jTFFreguesia;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableCacador;
    // End of variables declaration//GEN-END:variables

}