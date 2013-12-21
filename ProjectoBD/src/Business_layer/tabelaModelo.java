/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business_layer;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andreramos
 */
public class tabelaModelo extends DefaultTableModel {
    
     @Override
    public boolean isCellEditable(int row, int column) {
       return false;
    }
    
}
