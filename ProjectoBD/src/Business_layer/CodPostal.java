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
public class CodPostal {

    private String codigo;
    private String concelho;
    private String freguesia;

    public CodPostal() {
        this.codigo = new String();
        this.concelho = new String();
        this.freguesia = new String();
    }

    public CodPostal(String codigo, String concelho, String freguesia) {
        this.codigo = codigo;
        this.concelho = concelho;
        this.freguesia = freguesia;
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String getConcelho() {
        return this.concelho;
    }

    public String getFreguesia() {
        return this.freguesia;
    }

}
