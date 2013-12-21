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
public class Local {

    private String nome;
    private String codPostal;
    private float preco;
    private int limite;
    private String desc;

    public Local(String nome, String codPostal, float preco, int limite, String desc) {
        this.nome = nome;
        this.codPostal = codPostal;
        this.preco = preco;
        this.limite = limite;
        this.desc = desc;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCodPostal() {
        return this.codPostal;
    }

    public float getPreco() {
        return this.preco;
    }

    public int getLimite() {
        return this.limite;
    }

    public String getDesc() {
        return this.desc;
    }

}
