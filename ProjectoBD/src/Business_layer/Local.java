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
    private String[] especies;

    public Local(String nome, String codPostal, float preco, int limite, String desc, String[] especies) {
        this.nome = nome;
        this.codPostal = codPostal;
        this.preco = preco;
        this.limite = limite;
        this.desc = desc;
        this.especies = especies;
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

    public String[] getEspecies() {
        return this.especies;
    }

    public void setEspecies(String[] lista) {
        this.especies = lista;
    }

    public String especiesToString() {
        String res = "";
        int tam = especies.length;
        if (tam > 0) {
            res += especies[0];
        }
        for (int i = 1; i < especies.length; i++) {
            res += System.getProperty("line.separator") + especies[i];
        }
        return res;
    }

}
