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
    private GregorianCalendar data;
    private long cacador;
    private String local;

    public Reserva(long numero, GregorianCalendar data, long cacador, String local) {
        this.numero = numero;
        this.data = data;
        this.cacador = cacador;
        this.local = local;
    }

    public long getNumero() {
        return this.numero;
    }

    public GregorianCalendar getData() {
        return this.data;
    }

    public long getCacador() {
        return this.cacador;
    }

    public String getLocal() {
        return this.local;
    }
}
