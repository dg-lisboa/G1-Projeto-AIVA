package controller;

public class GestorDeDespesas {
    private double totalDespesas;

    public GestorDeDespesas() {
        this.totalDespesas = 0;
    }

    public void adicionarDespesa(double valor) {
        totalDespesas += valor;
    }

    public double getTotalDespesas() {
        return totalDespesas;
    }
}
