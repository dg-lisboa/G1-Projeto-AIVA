package view;

import controller.GestorDeDespesas;
import java.util.Scanner;

public class MenuDespesas {
    private GestorDeDespesas gestorDeDespesas;
    private Scanner scanner;

    public MenuDespesas(GestorDeDespesas gestorDeDespesas) {
        this.gestorDeDespesas = gestorDeDespesas;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        while (true) {
            // Exibe os saldos acima do menu, sempre atualizados
            System.out.println("Saldo de Despesas: R$ " + gestorDeDespesas.getTotalDespesas());
            System.out.println("Menu de Despesas:");
            System.out.println("1. Registrar Despesa");
            System.out.println("2. Voltar para o Menu Principal");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            if (opcao == 1) {
                registrarDespesa();
            } else if (opcao == 2) {
                break; // Volta para o Menu Principal
            } else {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private void registrarDespesa() {
        System.out.print("Digite o valor da despesa: ");
        double valorDespesa = scanner.nextDouble();
        scanner.nextLine(); // Limpa o buffer
        gestorDeDespesas.adicionarDespesa(valorDespesa); // Atualiza o saldo de despesas
        System.out.println("Despesa registrada com sucesso!");
    }
}
