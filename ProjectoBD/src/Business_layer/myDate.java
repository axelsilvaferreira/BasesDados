/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_layer;

/**
 *
 * @author andreramos
 */
public class myDate {

    public int ano;
    public int mes;
    public int dia;

    public myDate() {
        this.ano = 1;
        this.mes = 1;
        this.dia = 1;
    }

    public myDate(int ano, int mes, int dia) {
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
    }

    @Override
    public String toString() {
        return Integer.toString(ano) + "-" + Integer.toString(mes) + "-" + Integer.toString(dia);
    }
}
