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
public class Especie {

    private String nome;
    private int numeroMax;
    private String desc;
    private String foto;

    public Especie(String nome, int numeroMax, String desc, String foto) {
        this.nome = nome;
        this.numeroMax = numeroMax;
        this.desc = desc;
        this.foto = foto;
    }

    public String getNome() {
        return this.nome;
    }

    public int getNUmeroMax() {
        return this.numeroMax;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getFoto() {
        return this.foto;
    }

}
