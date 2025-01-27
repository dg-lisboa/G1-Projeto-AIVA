package view;

import controller.GestorDeVendas;
import java.util.Scanner;

public class MenuVendas {
    private final GestorDeVendas gestorDeVendas;
    private final Scanner scanner;

    public MenuVendas(GestorDeVendas gestorDeVendas) {
        this.gestorDeVendas = gestorDeVendas;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        while (true) {
            // Exibe os saldos acima do menu, sempre atualizados
            System.out.println("-------------------- Menu Vendas ---------------------");
            System.out.println("/// Total de vendas: R$ " + gestorDeVendas.getTotalVendas());
            System.out.println("1. Registrar Venda   2. Histórico de vendas   3. Sair");
            System.out.println("------------------------------------------------------");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            if (opcao == 1) {
                registrarVenda();
            } else if (opcao == 2) {
                historicoVenda(); // Volta para o Menu Principal
            } else {
                System.out.println("Opção inválida! Tente novamente.");
                break;
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

    private void historicoVenda() {
        System.out.print("Digite o valor da venda: ");
    }
}
