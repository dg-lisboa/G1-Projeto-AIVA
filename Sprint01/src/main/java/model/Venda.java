package model;

import java.time.LocalDate;

/**
 * Representa uma venda no sistema.
 */
public class Venda {
    private String produto;
    private double valor;
    private String cliente;
    private int parcelas;
    private LocalDate dataVenda;
    private String fornecedor;
    private int pagamento;

    public Venda(String produto, double valor, String cliente, int parcelas, LocalDate dataVenda, String fornecedor) {
        this.produto = produto;
        this.valor = valor;
        this.cliente = cliente;
        this.parcelas = parcelas;
        this.dataVenda = dataVenda;
        this.fornecedor = fornecedor;
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

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public String getFornecedor() {
        return fornecedor;
    }
}
