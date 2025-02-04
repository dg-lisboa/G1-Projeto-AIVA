package controller;

import model.Venda;
import java.util.ArrayList;
import java.util.List;

public class GestorDeVendas {
    private double totalVendas;
    private int totalParcelas;
    private List<Venda> historicoVendas; // Lista para armazenar o histórico de vendas

    public GestorDeVendas() {
        this.totalVendas = 0;
        this.totalParcelas = 0;
        this.historicoVendas = new ArrayList<>(); // Inicializa a lista de histórico de vendas
    }

    public void adicionarVenda(Venda venda) {
        totalVendas += venda.getValor();
        totalParcelas += venda.getParcelas();
        historicoVendas.add(venda); // Adiciona a venda ao histórico
    }

    public double getTotalVendas() {
        return totalVendas;
    }

    public void adicionarParcela(int parcelas) {
        totalParcelas += parcelas;
    }

    public int getTotalParcelas() {
        return totalParcelas;
    }

    public List<Venda> getHistoricoVendas() {
        return historicoVendas; // Retorna o histórico de vendas
    }
}
