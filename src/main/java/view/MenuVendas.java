package view;

import controller.GestorDeVendas;
import java.util.Scanner;

public class MenuVendas {
    private GestorDeVendas gestorDeVendas;
    private Scanner scanner;

    public MenuVendas(GestorDeVendas gestorDeVendas) {
        this.gestorDeVendas = gestorDeVendas;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        while (true) {
            // Exibe os saldos acima do menu, sempre atualizados
            System.out.println("Saldo de Vendas: R$ " + gestorDeVendas.getTotalVendas());
            System.out.println("Menu de Vendas:");
            System.out.println("1. Registrar Venda");
            System.out.println("2. Voltar para o Menu Principal");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            if (opcao == 1) {
                registrarVenda();
            } else if (opcao == 2) {
                break; // Volta para o Menu Principal
            } else {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private void registrarVenda() {
        System.out.print("Digite o valor da venda: ");
        double valorVenda = scanner.nextDouble();
        scanner.nextLine(); // Limpa o buffer
        gestorDeVendas.adicionarVenda(valorVenda); // Atualiza o saldo de vendas
        System.out.println("Venda registrada com sucesso!");
    }
}
