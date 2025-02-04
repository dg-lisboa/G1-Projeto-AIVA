package controller;

import model.Despesa;
import java.util.ArrayList;
import java.util.List;

public class GestorDeDespesas {
    private double totalDespesas;
    private List<Despesa> historicoDespesas;

    public GestorDeDespesas() {
        this.totalDespesas = 0;
        this.historicoDespesas = new ArrayList<>();
    }

    public void adicionarDespesa(Despesa despesa) {
        totalDespesas += despesa.getValor();
        historicoDespesas.add(despesa);
    }

    public double getTotalDespesas() {
        return totalDespesas;
    }

    public List<Despesa> getHistoricoDespesas() {
        return historicoDespesas;
    }
}