package controller;

public class GestorDeVendas {
    private double totalVendas;

    public GestorDeVendas() {
        this.totalVendas = 0;
    }

    public void adicionarVenda(double valor) {
        totalVendas += valor;
    }

    public double getTotalVendas() {
        return totalVendas;
    }
}
