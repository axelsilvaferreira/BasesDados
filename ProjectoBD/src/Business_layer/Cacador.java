/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_layer;

import java.util.GregorianCalendar;

/**
 *
 * @author andreramos
 */
public class Cacador {

    private long numero;
    private String nome;
    private String BI;
    private MyDate dataNasc;
    private String codPostal;
    private int telefone;
    private String mail;
    private String pass;

    public Cacador() {
        this.numero = 0;
        this.nome = new String();
        this.BI = new String();
        this.dataNasc = new MyDate();
        this.codPostal = new String();
        this.telefone = 0;
        this.mail = new String();
        this.pass = new String();
    }

    public Cacador(long numero, String nome, String BI, MyDate dataNasc, String codPostal, int telefone, String mail, String pass) {
        this.numero = numero;
        this.nome = nome;
        this.BI = BI;
        this.dataNasc = dataNasc;
        this.codPostal = codPostal;
        this.telefone = telefone;
        this.mail = mail;
        this.pass = pass;
    }

    public long getNumero() {
        return this.numero;
    }

    public String getNome() {
        return this.nome;
    }

    public String getBI() {
        return this.BI;
    }

    public MyDate getDataNasc() {
        return this.dataNasc;
    }

    public String getCodPostal() {
        return this.codPostal;
    }

    public int getTelefone() {
        return this.telefone;
    }

    public String getMail() {
        return this.mail;
    }

    public String getPass() {
        return this.pass;
    }
}
