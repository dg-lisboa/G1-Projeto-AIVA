package model;

/**
 * Representa uma despesa no sistema.
 */
public class Despesa {
    private double valor;
    private String categoria;
    private String descricao;
    private String data;

    public Despesa(double valor, String categoria, String descricao, String data) {
        this.valor = valor;
        this.categoria = categoria;
        this.descricao = descricao;
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getData() {
        return data;
    }
}
