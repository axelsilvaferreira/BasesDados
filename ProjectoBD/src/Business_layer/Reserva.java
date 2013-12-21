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
public class Reserva {

    private long numero;
    private myDate data;
    private long numCacador;
    private String local;

    public Reserva(long numero, myDate data, long numCacador, String local) {
        this.numero = numero;
        this.data = data;
        this.numCacador = numCacador;
        this.local = local;
    }

    public long getNumero() {
        return this.numero;
    }

    public myDate getData() {
        return this.data;
    }

    public long getNumCacador() {
        return this.numCacador;
    }

    public String getLocal() {
        return this.local;
    }
}
