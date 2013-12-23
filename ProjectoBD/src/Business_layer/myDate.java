/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_layer;

import java.util.Scanner;

/**
 *
 * @author andreramos
 */
public class MyDate {

    public int ano;
    public int mes;
    public int dia;

    public MyDate() {
        this.ano = 1;
        this.mes = 1;
        this.dia = 1;
    }

    public MyDate(int ano, int mes, int dia) {
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
    }
    
    public MyDate(String data){
        this.ano = 1;
        this.mes = 1;
        this.dia = 1;
        Scanner scan = new Scanner(data);
        scan.useDelimiter("-");
        if (scan.hasNext()) {
            ano = Integer.parseInt(scan.next().trim());
        }
        if (scan.hasNext()) {
            mes = Integer.parseInt(scan.next().trim());
        }
        if (scan.hasNext()) {
            dia = Integer.parseInt(scan.next().trim());
        }
    }

    @Override
    public String toString() {
        return Integer.toString(ano) + "-" + Integer.toString(mes) + "-" + Integer.toString(dia);
    }
}
