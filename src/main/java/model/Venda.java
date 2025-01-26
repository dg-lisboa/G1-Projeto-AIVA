package model;

/**
 * Representa uma venda no sistema.
 */
public class Venda {
    private String produto;
    private double valor;
    private String cliente;
    private int parcelas;

    public Venda(String produto, double valor, String cliente, int parcelas) {
        this.produto = produto;
        this.valor = valor;
        this.cliente = cliente;
        this.parcelas = parcelas;
    }

    public String getProduto() {
        return produto;
    }

    public double getValor() {
        return valor;
    }

    public String getCliente() {
        return cliente;
    }

    public int getParcelas() {
        return parcelas;
    }
}
