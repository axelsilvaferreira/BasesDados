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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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

    private boolean novoFlag = false;
    private int editaTab = 0;
    private String editaKey = "";

    public JProjectoBD() {
        initComponents();
        estadoVista();
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

        this.setResizable(false);

    }

    @Override
    public void update(Observable o, Object arg) {
        this.listEspecie();
        this.listLocal();
        this.listReserva();
        this.listCacador();
        this.listCodPostal();
    }

    public void limpar() {

        jTFENome.setText("");
        jTFEMax.setText("");
        jTAEDesc.setText("");

        jTFLNome.setText("");
        jTFLCodPostal.setText("");
        jTFLPreco.setText("");
        jTFLMax.setText("");
        jTALDesc.setText("");
        jTALEspecies.setText("");

        jTFRNum.setText("");
        jTFRData.setText("");
        jTFRCacador.setText("");
        jTFRLocal.setText("");

        jTFCNum.setText("");
        jTFCNome.setText("");
        jTFCBI.setText("");
        jTFCData.setText("");
        jTFCCodPostal.setText("");
        jTFCTelef.setText("");
        jTFCMail.setText("");
        jTFCPass.setText("");

        jTFCPCodPostal.setText("");
        jTFCPConcelho.setText("");
        jTFCPFreguesia.setText("");

    }

    public void listEspecie() {
        try {
            tabelaModelo DTM = new tabelaModelo();
            DTM.addColumn("Nome");
            for (Especie especie : this.especieDAO.getAll()) {
                DTM.addRow(new Object[]{especie.getNome()});
            }
            jTableEspecie.setModel(DTM);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void listLocal() {
        try {
            tabelaModelo DTM = new tabelaModelo();
            DTM.addColumn("Nome");
            DTM.addColumn("Codigo Postal");
            for (Local local : this.localDAO.getAll()) {
                DTM.addRow(new Object[]{local.getNome(), local.getCodPostal()});
            }
            jTableLocal.setModel(DTM);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void listReserva() {
        try {
            tabelaModelo DTM = new tabelaModelo();
            DTM.addColumn("Numero");
            DTM.addColumn("Data");
            DTM.addColumn("Local");
            DTM.addColumn("Numero Cacador");
            for (Reserva reserva : this.reservaDAO.getAll()) {
                DTM.addRow(new Object[]{reserva.getNumero(), reserva.getData(), reserva.getLocal(), reserva.getNumCacador()});
            }
            jTableReserva.setModel(DTM);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void listCacador() {
        try {
            tabelaModelo DTM = new tabelaModelo();
            DTM.addColumn("Numero");
            DTM.addColumn("Nome");
            for (Cacador cacador : this.cacadorDAO.getAll()) {
                DTM.addRow(new Object[]{cacador.getNumero(), cacador.getNome()});
            }
            jTableCacador.setModel(DTM);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void listCodPostal() {
        try {
            tabelaModelo DTM = new tabelaModelo();
            DTM.addColumn("Codigo Postal");
            DTM.addColumn("Freguesia");
            for (CodPostal codPostal : this.codPostalDAO.getAll()) {
                DTM.addRow(new Object[]{codPostal.getCodigo(), codPostal.getFreguesia()});
            }
            jTableCodPostal.setModel(DTM);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Especie makeEspecie() {
        return new Especie(jTFENome.getText(), Integer.parseInt(jTFEMax.getText()), jTAEDesc.getText());
    }

    private Local makeLocal() {
        ArrayList<String> lEspecies = new ArrayList<>();
        Scanner scan = new Scanner(jTALEspecies.getText());
        scan.useDelimiter(System.getProperty("line.separator"));
        while (scan.hasNext()) {
            lEspecies.add(scan.next());
        }
        return new Local(jTFLNome.getText(), jTFLCodPostal.getText(),
                Float.parseFloat(jTFLPreco.getText()), Integer.parseInt(jTFLMax.getText()), jTALDesc.getText(), lEspecies);
    }

    private Reserva makeReserva() {
        return new Reserva(0, new MyDate(jTFRData.getText()), Long.parseLong(jTFRCacador.getText()),
                jTFRLocal.getText());
    }

    private Cacador makeCacador() {
        return new Cacador(Long.parseLong(jTFCNum.getText()), jTFCNome.getText(),
                jTFCBI.getText(), new MyDate(jTFCData.getText()), jTFCCodPostal.getText(),
                Integer.parseInt(jTFCTelef.getText()), jTFCMail.getText(), jTFCPass.getText());
    }

    private CodPostal makeCodPostal() {
        return new CodPostal(jTFCPCodPostal.getText(), jTFCPConcelho.getText(), jTFCPFreguesia.getText());
    }

    private void rmEspecie() throws SQLException {
        String key = jTableEspecie.getValueAt(jTableEspecie.getSelectedRow(), 0).toString();
        this.especieDAO.remove(key);
    }

    private void rmLocal() throws SQLException {
        String key = jTableLocal.getValueAt(jTableLocal.getSelectedRow(), 0).toString();
        this.localDAO.remove(key);
    }

    private void rmReserva() throws SQLException {
        long key = Long.parseLong(jTableReserva.getValueAt(jTableReserva.getSelectedRow(), 0).toString());
        this.reservaDAO.remove(key);
    }

    private void rmCacador() throws SQLException {
        long key = Long.parseLong(jTableCacador.getValueAt(jTableCacador.getSelectedRow(), 0).toString());
        this.cacadorDAO.remove(key);
    }

    private void rmCodPostal() throws SQLException {
        String key = jTableCodPostal.getValueAt(jTableCodPostal.getSelectedRow(), 0).toString();
        this.codPostalDAO.remove(key);
    }

    private void estadoEdicao() {
        jBtnNovo.setEnabled(false);
        jBtnEditar.setEnabled(false);
        jBtnGravar.setEnabled(true);
        jBtnCancelar.setEnabled(true);
        jBtnApagar.setEnabled(false);

        jBtnIEspecie.setEnabled(false);
        jBtnIMEspecie.setEnabled(false);
        jBtnRMEspecie.setEnabled(false);

        jBtnILocal.setEnabled(false);
        jBtnIMLocal.setEnabled(false);
        jBtnRMLocal.setEnabled(false);
    }

    private void estadoVista() {
        jBtnNovo.setEnabled(true);
        jBtnEditar.setEnabled(true);
        jBtnGravar.setEnabled(false);
        jBtnCancelar.setEnabled(false);
        jBtnApagar.setEnabled(true);

        jBtnIEspecie.setEnabled(true);
        jBtnIMEspecie.setEnabled(true);
        jBtnRMEspecie.setEnabled(true);

        jBtnILocal.setEnabled(true);
        jBtnIMLocal.setEnabled(true);
        jBtnRMLocal.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTPPainel = new javax.swing.JTabbedPane();
        jPEspecie = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableEspecie = new javax.swing.JTable();
        jTFENome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTFEMax = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTAEDesc = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jBtnIEspecie = new javax.swing.JButton();
        jBtnIMEspecie = new javax.swing.JButton();
        jBtnRMEspecie = new javax.swing.JButton();
        jPLocal = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableLocal = new javax.swing.JTable();
        jTFLNome = new javax.swing.JTextField();
        jTFLCodPostal = new javax.swing.JTextField();
        jTFLPreco = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTFLMax = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTALDesc = new javax.swing.JTextArea();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTALEspecies = new javax.swing.JTextArea();
        jLabel26 = new javax.swing.JLabel();
        jBtnILocal = new javax.swing.JButton();
        jBtnIMLocal = new javax.swing.JButton();
        jBtnRMLocal = new javax.swing.JButton();
        jPReserva = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableReserva = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jTFRNum = new javax.swing.JTextField();
        jTFRLocal = new javax.swing.JTextField();
        jTFRCacador = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTFRData = new javax.swing.JTextField();
        jPCacador = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCacador = new javax.swing.JTable();
        jTFCNum = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTFCNome = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTFCBI = new javax.swing.JTextField();
        jTFCData = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTFCCodPostal = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTFCTelef = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTFCMail = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTFCPass = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jPCodPostal = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableCodPostal = new javax.swing.JTable();
        jPCPEdit = new javax.swing.JPanel();
        jTFCPFreguesia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTFCPConcelho = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTFCPCodPostal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jBtnNovo = new javax.swing.JButton();
        jBtnEditar = new javax.swing.JButton();
        jBtnGravar = new javax.swing.JButton();
        jBtnApagar = new javax.swing.JButton();
        jBtnCancelar = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CarninhaBravia");

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
        jTableEspecie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eClica(evt);
            }
        });
        jScrollPane3.setViewportView(jTableEspecie);

        jLabel4.setText("Nome");

        jTFEMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFEMaxActionPerformed(evt);
            }
        });

        jLabel5.setText("Número máximo");

        jTAEDesc.setColumns(20);
        jTAEDesc.setRows(5);
        jScrollPane6.setViewportView(jTAEDesc);

        jLabel6.setText("Descrição");

        jBtnIEspecie.setText("Imagem");
        jBtnIEspecie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnIEspecieActionPerformed(evt);
            }
        });

        jBtnIMEspecie.setText("Inserir Imagem");
        jBtnIMEspecie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnIMEspecieActionPerformed(evt);
            }
        });

        jBtnRMEspecie.setText("Remover Imagem");
        jBtnRMEspecie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRMEspecieActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPEspecieLayout = new javax.swing.GroupLayout(jPEspecie);
        jPEspecie.setLayout(jPEspecieLayout);
        jPEspecieLayout.setHorizontalGroup(
            jPEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEspecieLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6)
                        .addComponent(jTFEMax, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                        .addComponent(jTFENome))
                    .addGroup(jPEspecieLayout.createSequentialGroup()
                        .addComponent(jBtnIEspecie)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnIMEspecie)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnRMEspecie)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPEspecieLayout.setVerticalGroup(
            jPEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEspecieLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                    .addGroup(jPEspecieLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFENome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFEMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6)
                        .addGap(18, 18, 18)
                        .addGroup(jPEspecieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnIEspecie)
                            .addComponent(jBtnIMEspecie)
                            .addComponent(jBtnRMEspecie))))
                .addContainerGap())
        );

        jTPPainel.addTab("Espécies", jPEspecie);

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
        jTableLocal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lClica(evt);
            }
        });
        jScrollPane5.setViewportView(jTableLocal);

        jLabel7.setText("Nome");

        jLabel8.setText("Codigo Postal");

        jLabel9.setText("Preço(€)");

        jLabel10.setText("Limite de caçadores");

        jLabel11.setText("Descrição");

        jTALDesc.setColumns(20);
        jTALDesc.setRows(5);
        jScrollPane7.setViewportView(jTALDesc);

        jTALEspecies.setColumns(10);
        jTALEspecies.setRows(5);
        jScrollPane9.setViewportView(jTALEspecies);

        jLabel26.setText("Espécies");

        jBtnILocal.setText("Imagem");
        jBtnILocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnILocalActionPerformed(evt);
            }
        });

        jBtnIMLocal.setText("Inserir Imagem");
        jBtnIMLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnIMLocalActionPerformed(evt);
            }
        });

        jBtnRMLocal.setText("Remover Imagem");
        jBtnRMLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRMLocalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPLocalLayout = new javax.swing.GroupLayout(jPLocal);
        jPLocal.setLayout(jPLocalLayout);
        jPLocalLayout.setHorizontalGroup(
            jPLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPLocalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPLocalLayout.createSequentialGroup()
                        .addGroup(jPLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPLocalLayout.createSequentialGroup()
                                    .addGroup(jPLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jTFLMax, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10))
                                    .addGap(96, 96, 96))
                                .addComponent(jLabel8)
                                .addComponent(jLabel9)
                                .addComponent(jTFLCodPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTFLPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTFLNome))
                            .addGroup(jPLocalLayout.createSequentialGroup()
                                .addGroup(jPLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel11))
                                .addGap(159, 159, 159)))
                        .addGap(14, 14, 14)
                        .addGroup(jPLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24))
                    .addGroup(jPLocalLayout.createSequentialGroup()
                        .addGroup(jPLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPLocalLayout.createSequentialGroup()
                                .addComponent(jBtnILocal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jBtnIMLocal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnRMLocal)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPLocalLayout.setVerticalGroup(
            jPLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPLocalLayout.createSequentialGroup()
                .addGroup(jPLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPLocalLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPLocalLayout.createSequentialGroup()
                                .addComponent(jTFLNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFLCodPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFLPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTFLMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPLocalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnILocal)
                            .addComponent(jBtnIMLocal)
                            .addComponent(jBtnRMLocal)))
                    .addGroup(jPLocalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTPPainel.addTab("Locais", jPLocal);

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
        jTableReserva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rClica(evt);
            }
        });
        jScrollPane1.setViewportView(jTableReserva);

        jLabel12.setText("Numero");

        jTFRNum.setEnabled(false);

        jLabel13.setText("Data");

        jLabel14.setText("Numero do cacador");

        jLabel15.setText("Local");

        jTFRData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFRDataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPReservaLayout = new javax.swing.GroupLayout(jPReserva);
        jPReserva.setLayout(jPReservaLayout);
        jPReservaLayout.setHorizontalGroup(
            jPReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPReservaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jTFRNum, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFRData, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jTFRCacador, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jTFRLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPReservaLayout.setVerticalGroup(
            jPReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPReservaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPReservaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                    .addGroup(jPReservaLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFRNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFRData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFRCacador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFRLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTPPainel.addTab("Reservas", jPReserva);

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
        jTableCacador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cClica(evt);
            }
        });
        jScrollPane2.setViewportView(jTableCacador);

        jLabel16.setText("Numero");

        jLabel17.setText("Nome");

        jLabel18.setText("BI/CC");

        jLabel19.setText("Data Nascimento");

        jLabel20.setText("Codigo Postal");

        jLabel21.setText("Telefone");

        jLabel22.setText("E-Mail");

        jLabel23.setText("Password");

        javax.swing.GroupLayout jPCacadorLayout = new javax.swing.GroupLayout(jPCacador);
        jPCacador.setLayout(jPCacadorLayout);
        jPCacadorLayout.setHorizontalGroup(
            jPCacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCacadorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPCacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTFCNum, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jTFCNome, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jTFCBI, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addGroup(jPCacadorLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(161, 161, 161)
                        .addComponent(jLabel21))
                    .addGroup(jPCacadorLayout.createSequentialGroup()
                        .addGroup(jPCacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTFCCodPostal)
                            .addComponent(jTFCData, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(jLabel22)
                            .addComponent(jTFCMail))
                        .addGap(68, 68, 68)
                        .addGroup(jPCacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel23)
                            .addComponent(jTFCTelef, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                            .addComponent(jTFCPass))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPCacadorLayout.setVerticalGroup(
            jPCacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCacadorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPCacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                    .addGroup(jPCacadorLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFCNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFCNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFCBI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFCData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPCacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addGap(4, 4, 4)
                        .addGroup(jPCacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFCCodPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFCTelef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPCacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPCacadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTFCMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFCPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTPPainel.addTab("Caçadores", jPCacador);

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
        jTableCodPostal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cpClica(evt);
            }
        });
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
                    .addComponent(jTFCPConcelho)
                    .addComponent(jTFCPFreguesia)
                    .addGroup(jPCPEditLayout.createSequentialGroup()
                        .addGroup(jPCPEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(jPCPEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTFCPCodPostal, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(0, 349, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPCPEditLayout.setVerticalGroup(
            jPCPEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCPEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFCPCodPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(1, 1, 1)
                .addComponent(jTFCPConcelho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(4, 4, 4)
                .addComponent(jTFCPFreguesia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPCodPostalLayout = new javax.swing.GroupLayout(jPCodPostal);
        jPCodPostal.setLayout(jPCodPostalLayout);
        jPCodPostalLayout.setHorizontalGroup(
            jPCodPostalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCodPostalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jPCPEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPCodPostalLayout.setVerticalGroup(
            jPCodPostalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPCodPostalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPCodPostalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPCPEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTPPainel.addTab("Moradas", jPCodPostal);

        getContentPane().add(jTPPainel, java.awt.BorderLayout.WEST);
        jTPPainel.getAccessibleContext().setAccessibleName("");
        jTPPainel.getAccessibleContext().setAccessibleDescription("");

        jBtnNovo.setText("Novo");
        jBtnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnNovoActionPerformed(evt);
            }
        });

        jBtnEditar.setText("Editar");
        jBtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEditarActionPerformed(evt);
            }
        });

        jBtnGravar.setText("Gravar");
        jBtnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGravarActionPerformed(evt);
            }
        });

        jBtnApagar.setText("Apagar");
        jBtnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnApagarActionPerformed(evt);
            }
        });

        jBtnCancelar.setText("Cancelar");
        jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarActionPerformed(evt);
            }
        });

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Interface_layer/Icon.png"))); // NOI18N
        jLabel24.setText("jLabel24");

        jLabel25.setText("Administração");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jBtnNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnGravar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnApagar, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                        .addComponent(jBtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jBtnNovo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnGravar)
                .addGap(7, 7, 7)
                .addComponent(jBtnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnApagar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addGap(14, 14, 14))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.EAST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cpClica(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cpClica

        String key = jTableCodPostal.getValueAt(jTableCodPostal.getSelectedRow(), 0).toString();
        try {
            CodPostal value = this.codPostalDAO.get(key);
            jTFCPCodPostal.setText(key);
            jTFCPConcelho.setText(value.getConcelho());
            jTFCPFreguesia.setText(value.getFreguesia());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_cpClica

    private void jTFEMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFEMaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFEMaxActionPerformed

    private void cClica(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cClica
        String key = jTableCacador.getValueAt(jTableCacador.getSelectedRow(), 0).toString();
        try {
            Cacador value = this.cacadorDAO.get(Long.parseLong(key));
            jTFCNum.setText(key);
            jTFCNome.setText(value.getNome());
            jTFCBI.setText(value.getBI());
            jTFCData.setText(value.getDataNasc().toString());
            jTFCCodPostal.setText(value.getCodPostal());
            jTFCTelef.setText(Integer.toString(value.getTelefone()));
            jTFCMail.setText(value.getMail());
            jTFCPass.setText(value.getPass());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_cClica

    private void rClica(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rClica
        String key = jTableReserva.getValueAt(jTableReserva.getSelectedRow(), 0).toString();
        try {
            Reserva value = this.reservaDAO.get(Long.parseLong(key));
            jTFRNum.setText(key);
            jTFRData.setText(value.getData().toString());
            jTFRCacador.setText(Long.toString(value.getNumCacador()));
            jTFRLocal.setText(value.getLocal());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_rClica

    private void lClica(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lClica
        String key = jTableLocal.getValueAt(jTableLocal.getSelectedRow(), 0).toString();
        try {
            Local value = this.localDAO.get(key);
            jTFLNome.setText(key);
            jTFLCodPostal.setText(value.getCodPostal());
            jTFLPreco.setText(Float.toString(value.getPreco()));
            jTFLMax.setText(Integer.toString(value.getLimite()));
            jTALDesc.setText(value.getDesc());
            jTALEspecies.setText(value.especiesToString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lClica

    private void eClica(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eClica
        String key = jTableEspecie.getValueAt(jTableEspecie.getSelectedRow(), 0).toString();
        try {
            Especie value = this.especieDAO.get(key);
            jTFENome.setText(key);
            jTFEMax.setText(Integer.toString(value.getNumeroMax()));
            jTAEDesc.setText(value.getDesc());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_eClica

    private void jBtnApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnApagarActionPerformed
        try {
            try {
                switch (jTPPainel.getSelectedIndex()) {
                    case 0:
                        rmEspecie();
                        break;
                    case 1:
                        rmLocal();
                        break;
                    case 2:
                        rmReserva();
                        break;
                    case 3:
                        rmCacador();
                        break;
                    case 4:
                        rmCodPostal();
                        break;
                }
                limpar();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            estadoVista();
        }
    }//GEN-LAST:event_jBtnApagarActionPerformed

    private void jBtnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNovoActionPerformed
        editaTab = jTPPainel.getSelectedIndex();
        novoFlag = true;
        limpar();
        estadoEdicao();
        try {
            switch (editaTab) {
                case 0:
                    // editaKey = jTableEspecie.getValueAt(jTableEspecie.getSelectedRow(), 0).toString();
                    break;
                case 1:
                    // editaKey = jTableLocal.getValueAt(jTableLocal.getSelectedRow(), 0).toString();
                    break;
                case 2:
                    jTFRNum.setText(Long.toString(reservaDAO.gerarKey()));
                    // editaKey = jTableReserva.getValueAt(jTableReserva.getSelectedRow(), 0).toString();
                    break;
                case 3:
                    //editaKey = jTableCacador.getValueAt(jTableCacador.getSelectedRow(), 0).toString();
                    break;
                case 4:
                    //editaKey = jTableCodPostal.getValueAt(jTableCodPostal.getSelectedRow(), 0).toString();
                    break;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBtnNovoActionPerformed

    private void jBtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditarActionPerformed
        try {
            editaTab = jTPPainel.getSelectedIndex();
            novoFlag = false;
            estadoEdicao();
            switch (editaTab) {
                case 0:
                    editaKey = jTableEspecie.getValueAt(jTableEspecie.getSelectedRow(), 0).toString();
                    break;
                case 1:
                    editaKey = jTableLocal.getValueAt(jTableLocal.getSelectedRow(), 0).toString();
                    break;
                case 2:
                    editaKey = jTableReserva.getValueAt(jTableReserva.getSelectedRow(), 0).toString();
                    break;
                case 3:
                    editaKey = jTableCacador.getValueAt(jTableCacador.getSelectedRow(), 0).toString();
                    break;
                case 4:
                    editaKey = jTableCodPostal.getValueAt(jTableCodPostal.getSelectedRow(), 0).toString();
                    break;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            estadoVista();
        }
    }//GEN-LAST:event_jBtnEditarActionPerformed

    private void jBtnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGravarActionPerformed
        estadoVista();
        try {
            if (novoFlag == true) {
                switch (editaTab) {
                    case 0:
                        this.especieDAO.put(makeEspecie());
                        break;
                    case 1:
                        this.localDAO.put(makeLocal());
                        break;
                    case 2:
                        this.reservaDAO.put(makeReserva());
                        break;
                    case 3:
                        this.cacadorDAO.put(makeCacador());
                        break;
                    case 4:
                        this.codPostalDAO.put(makeCodPostal());
                        break;
                }
            } else {
                switch (editaTab) {
                    case 0:
                        this.especieDAO.update(makeEspecie(), editaKey);
                        break;
                    case 1:
                        this.localDAO.update(makeLocal(), editaKey);
                        break;
                    case 2:
                        this.reservaDAO.update(makeReserva(), Long.parseLong(editaKey));
                        break;
                    case 3:
                        this.cacadorDAO.update(makeCacador(), Long.parseLong(editaKey));
                        break;
                    case 4:
                        this.codPostalDAO.update(makeCodPostal(), editaKey);
                        break;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        novoFlag = false;
    }//GEN-LAST:event_jBtnGravarActionPerformed

    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
        estadoVista();
        novoFlag = false;
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    private void jTFRDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFRDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFRDataActionPerformed

    private void jBtnIEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnIEspecieActionPerformed
        try {
            try {
                String chave = jTableEspecie.getValueAt(jTableEspecie.getSelectedRow(), 0).toString();
                Blob blob = especieDAO.getFoto(chave);
                if (blob != null) {
                    Fotos.abrirFoto(chave, blob);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Imagem inexistente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBtnIEspecieActionPerformed

    private void jBtnILocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnILocalActionPerformed
        try {
            try {
                String chave = jTableLocal.getValueAt(jTableLocal.getSelectedRow(), 0).toString();
                Blob blob = localDAO.getFoto(chave);
                if (blob != null) {
                    Fotos.abrirFoto(chave, blob);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Imagem inexistente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBtnILocalActionPerformed

    private void jBtnIMLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnIMLocalActionPerformed
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filename = chooser.getSelectedFile().getPath();
            try {
                localDAO.putFoto(jTableLocal.getValueAt(jTableLocal.getSelectedRow(), 0).toString(), filename);
            } catch (SQLException | FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jBtnIMLocalActionPerformed

    private void jBtnIMEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnIMEspecieActionPerformed
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filename = chooser.getSelectedFile().getPath();
            try {
                especieDAO.putFoto(jTableEspecie.getValueAt(jTableEspecie.getSelectedRow(), 0).toString(), filename);
            } catch (SQLException | FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }


    }//GEN-LAST:event_jBtnIMEspecieActionPerformed

    private void jBtnRMEspecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRMEspecieActionPerformed
        try {
            try {
                especieDAO.removeFoto(jTableEspecie.getValueAt(jTableEspecie.getSelectedRow(), 0).toString());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBtnRMEspecieActionPerformed

    private void jBtnRMLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRMLocalActionPerformed
        try {
            try {
                localDAO.removeFoto(jTableLocal.getValueAt(jTableLocal.getSelectedRow(), 0).toString());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBtnRMLocalActionPerformed

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
                if ("Mac OS X".equals(info.getName())) {
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
    private javax.swing.JButton jBtnApagar;
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnGravar;
    private javax.swing.JButton jBtnIEspecie;
    private javax.swing.JButton jBtnILocal;
    private javax.swing.JButton jBtnIMEspecie;
    private javax.swing.JButton jBtnIMLocal;
    private javax.swing.JButton jBtnNovo;
    private javax.swing.JButton jBtnRMEspecie;
    private javax.swing.JButton jBtnRMLocal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea jTAEDesc;
    private javax.swing.JTextArea jTALDesc;
    private javax.swing.JTextArea jTALEspecies;
    private javax.swing.JTextField jTFCBI;
    private javax.swing.JTextField jTFCCodPostal;
    private javax.swing.JTextField jTFCData;
    private javax.swing.JTextField jTFCMail;
    private javax.swing.JTextField jTFCNome;
    private javax.swing.JTextField jTFCNum;
    private javax.swing.JTextField jTFCPCodPostal;
    private javax.swing.JTextField jTFCPConcelho;
    private javax.swing.JTextField jTFCPFreguesia;
    private javax.swing.JTextField jTFCPass;
    private javax.swing.JTextField jTFCTelef;
    private javax.swing.JTextField jTFEMax;
    private javax.swing.JTextField jTFENome;
    private javax.swing.JTextField jTFLCodPostal;
    private javax.swing.JTextField jTFLMax;
    private javax.swing.JTextField jTFLNome;
    private javax.swing.JTextField jTFLPreco;
    private javax.swing.JTextField jTFRCacador;
    private javax.swing.JTextField jTFRData;
    private javax.swing.JTextField jTFRLocal;
    private javax.swing.JTextField jTFRNum;
    private javax.swing.JTabbedPane jTPPainel;
    private javax.swing.JTable jTableCacador;
    private javax.swing.JTable jTableCodPostal;
    private javax.swing.JTable jTableEspecie;
    private javax.swing.JTable jTableLocal;
    private javax.swing.JTable jTableReserva;
    // End of variables declaration//GEN-END:variables

}
