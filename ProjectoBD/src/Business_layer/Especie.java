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

    public Especie(String nome, int numeroMax, String desc) {
        this.nome = nome;
        this.numeroMax = numeroMax;
        this.desc = desc;
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

}
