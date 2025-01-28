package model;

import java.time.LocalDate;

public class Despesa {
    private String nome;
    private double valor;
    private LocalDate dataPagamento;
    private String referencia;

    public Despesa(String nome, double valor, LocalDate dataPagamento, String referencia) {
        this.nome = nome;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.referencia = referencia;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public String getReferencia() {
        return referencia;
    }
}