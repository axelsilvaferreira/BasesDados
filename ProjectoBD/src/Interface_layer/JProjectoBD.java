/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface_layer;

import java.util.Observable;
import java.util.Observer;
import Data_layer.*;
import Business_layer.*;
import java.util.GregorianCalendar;

/**
 *
 * @author andreramos
 */
public class JProjectoBD extends javax.swing.JFrame implements Observer {

    /**
     * Creates new form JProjectoBD
     */
    EspecieDAO especieDAO;
    LocalDAO localDAO;
    ReservaDAO reservaDAO;
    CacadorDAO cacadorDAO;
    CodPostalDAO codPostalDAO;

    public JProjectoBD() {
        initComponents();
        ConnectBD.startBD();
        this.especieDAO = new EspecieDAO();
        this.localDAO = new LocalDAO();
        this.reservaDAO = new ReservaDAO();
        this.cacadorDAO = new CacadorDAO();
        this.codPostalDAO = new CodPostalDAO();

        this.especieDAO.addObserver(this);
        this.localDAO.addObserver(this);
        this.reservaDAO.addObserver(this);
        this.cacadorDAO.addObserver(this);
        this.codPostalDAO.addObserver(this);

        this.listEspecie();
        this.listLocal();
        this.listReserva();
        this.listCacador();
        this.listCodPostal();

    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void listEspecie() {

        tabelaModelo DTM = new tabelaModelo();
        DTM.addColumn("Nome");
        for (Especie especie : this.especieDAO.getAll()) {
            DTM.addRow(new Object[]{especie.getNome()});
        }
        jTableEspecie.setModel(DTM);

    }

    public void listLocal() {

        tabelaModelo DTM = new tabelaModelo();
        DTM.addColumn("Nome");
        DTM.addColumn("Codigo Postal");
        for (Local local : this.localDAO.getAll()) {
            DTM.addRow(new Object[]{local.getNome(), local.getCodPostal()});
        }
        jTableLocal.setModel(DTM);

    }

    public void listReserva() {

        tabelaModelo DTM = new tabelaModelo();
        DTM.addColumn("Numero");
        DTM.addColumn("Data");
        DTM.addColumn("Local");
        DTM.addColumn("Numero Cacador");
        for (Reserva reserva : this.reservaDAO.getAll()) {
           DTM.addRow(new Object[]{reserva.getNumero(), reserva.getData(), reserva.getLocal(), reserva.getNumCacador()});
        }
        jTableReserva.setModel(DTM);

    }

    public void listCacador() {

        tabelaModelo DTM = new tabelaModelo();
        DTM.addColumn("Numero");
        DTM.addColumn("Nome");
        for (Cacador cacador : this.cacadorDAO.getAll()) {
            DTM.addRow(new Object[]{cacador.getNumero(), cacador.getNome()});
        }
        jTableCacador.setModel(DTM);

    }

    public void listCodPostal() {

        tabelaModelo DTM = new tabelaModelo();
        DTM.addColumn("Codigo Postal");
        DTM.addColumn("Freguesia");
        for (CodPostal codPostal : this.codPostalDAO.getAll()) {
            DTM.addRow(new Object[]{codPostal.getCodigo(), codPostal.getFreguesia()});
        }
        jTableCodPostal.setModel(DTM);
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
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableEspecie = new javax.swing.JTable();
        jPLocal = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableLocal = new javax.swing.JTable();
        jPReserva = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableReserva = new javax.swing.JTable();
        jPCacador = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCacador = new javax.swing.JTable();
        jPCodPostal = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableCodPostal = new javax.swing.JTable();
        jPCPEdit = new javax.swing.JPanel();
        jTFFreguesia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTFConcelho = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTFCodPostal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableEspecie.setAutoCreateRowSorter(true);
        jTableEspecie.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Nome"
            }
        ));
        jScrollPane3.setViewportView(jTableEspecie);

        javax.swing.GroupLayout jPEspecieLayout = new javax.swing.GroupLayout(jPEspecie);
        jPEspecie.setLayout(jPEspecieLayout);
        jPEspecieLayout.setHorizontalGroup(
            jPEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEspecieLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(655, Short.MAX_VALUE))
        );
        jPEspecieLayout.setVerticalGroup(
            jPEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEspecieLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Espécies", jPEspecie);

        jTableLocal.setAutoCreateRowSorter(true);
        jTableLocal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nome", "Codigo Postal"
            }
        ));
        jScrollPane5.setViewportView(jTableLocal);

        javax.swing.GroupLayout jPLocalLayout = new javax.swing.GroupLayout(jPLocal);
        jPLocal.setLayout(jPLocalLayout);
        jPLocalLayout.setHorizontalGroup(
            jPLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPLocalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(582, Short.MAX_VALUE))
        );
        jPLocalLayout.setVerticalGroup(
            jPLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPLocalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Locais", jPLocal);

        jTableReserva.setAutoCreateRowSorter(true);
        jTableReserva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Numero", "Data", "Local", "Numero Cacador "
            }
        ));
        jScrollPane1.setViewportView(jTableReserva);

        javax.swing.GroupLayout jPReservaLayout = new javax.swing.GroupLayout(jPReserva);
        jPReserva.setLayout(jPReservaLayout);
        jPReservaLayout.setHorizontalGroup(
            jPReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPReservaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(694, Short.MAX_VALUE))
        );
        jPReservaLayout.setVerticalGroup(
            jPReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPReservaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Reservas", jPReserva);

        jTableCacador.setAutoCreateRowSorter(true);
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

        javax.swing.GroupLayout jPCacadorLayout = new javax.swing.GroupLayout(jPCacador);
        jPCacador.setLayout(jPCacadorLayout);
        jPCacadorLayout.setHorizontalGroup(
            jPCacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCacadorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(808, Short.MAX_VALUE))
        );
        jPCacadorLayout.setVerticalGroup(
            jPCacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCacadorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Caçadores", jPCacador);

        jButton1.setText("Editar");

        jButton2.setText("Gravar");

        jButton3.setText("Apagar");

        jButton4.setText("Novo");

        jTableCodPostal.setAutoCreateRowSorter(true);
        jTableCodPostal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(jTableCodPostal);

        jLabel3.setText("Freguesia");

        jLabel2.setText("Concelho");

        jLabel1.setText("Codigo Postal");

        javax.swing.GroupLayout jPCPEditLayout = new javax.swing.GroupLayout(jPCPEdit);
        jPCPEdit.setLayout(jPCPEditLayout);
        jPCPEditLayout.setHorizontalGroup(
            jPCPEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCPEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPCPEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTFConcelho)
                    .addComponent(jTFFreguesia)
                    .addGroup(jPCPEditLayout.createSequentialGroup()
                        .addGroup(jPCPEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(jPCPEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTFCodPostal, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPCPEditLayout.setVerticalGroup(
            jPCPEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCPEditLayout.createSequentialGroup()
                .addContainerGap()
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
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPCodPostalLayout = new javax.swing.GroupLayout(jPCodPostal);
        jPCodPostal.setLayout(jPCodPostalLayout);
        jPCodPostalLayout.setHorizontalGroup(
            jPCodPostalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCodPostalLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPCodPostalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPCodPostalLayout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addComponent(jPCPEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(266, Short.MAX_VALUE))
        );
        jPCodPostalLayout.setVerticalGroup(
            jPCodPostalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCodPostalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPCPEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPCodPostalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(20, 20, 20))
            .addGroup(jPCodPostalLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Codigo Postal", jPCodPostal);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.WEST);
        jTabbedPane1.getAccessibleContext().setAccessibleName("");
        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

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
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPCPEdit;
    private javax.swing.JPanel jPCacador;
    private javax.swing.JPanel jPCodPostal;
    private javax.swing.JPanel jPEspecie;
    private javax.swing.JPanel jPLocal;
    private javax.swing.JPanel jPReserva;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTFCodPostal;
    private javax.swing.JTextField jTFConcelho;
    private javax.swing.JTextField jTFFreguesia;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableCacador;
    private javax.swing.JTable jTableCodPostal;
    private javax.swing.JTable jTableEspecie;
    private javax.swing.JTable jTableLocal;
    private javax.swing.JTable jTableReserva;
    // End of variables declaration//GEN-END:variables

}
