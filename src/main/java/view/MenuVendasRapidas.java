package view;

import controller.GestorDeVendas;
import model.Venda;
import java.time.LocalDate;
import java.util.Scanner;

public class MenuVendasRapidas {
    private final GestorDeVendas gestorDeVendas;
    private final Scanner scanner;

    public MenuVendasRapidas(GestorDeVendas gestorDeVendas) {
        this.gestorDeVendas = gestorDeVendas;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        while (true) {
            // Exibe os saldos acima do menu, sempre atualizados
            System.out.println("+------------------- Menu Vendas Rápidas --------------------+");
            System.out.println("|  1. Registrar Venda   2. Sair                              |");
            System.out.println("+------------------------------------------------------------+");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            if (opcao == 1) {
                registrarVenda();
            } else if (opcao == 2) {
                System.out.println("Saindo do sistema...");
                break;
            } else {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private void registrarVenda() {
        double valor;
        while (true) {
            System.out.print("Digite o valor da venda: ");
            String valorStr = scanner.nextLine();
            try {
                valor = Double.parseDouble(valorStr);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Por favor, digite um número.");
            }
        }

        LocalDate dataVenda = LocalDate.now(); // Assume a data atual do programa

        String cliente;
        while (true) {
            System.out.print("Digite o nome do cliente: ");
            cliente = scanner.nextLine();
            if (cliente.matches("[a-zA-Z\\s]+")) {
                break;
            } else {
                System.out.println("Nome inválido! Por favor, digite um nome válido.");
            }
        }

        String fornecedor;
        while (true) {
            System.out.print("Digite o nome do fornecedor: ");
            fornecedor = scanner.nextLine();
            if (fornecedor.matches("[a-zA-Z\\s]+")) {
                break;
            } else {
                System.out.println("Nome inválido! Por favor, digite um nome válido.");
            }
        }

        System.out.println("Escolha a forma de pagamento: ");
        System.out.println("1. À Vista  2. Parcelado");
        System.out.print("Opção: ");
        int pagamento = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        int parcelas = 0;
        if (pagamento == 2) {
            // Para pagamento parcelado, solicita o número de parcelas
            System.out.print("Escolha o número de parcelas (1x, 2x, 3x, ...): ");
            parcelas = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
        }

        // Criar e registrar a venda
        Venda venda = new Venda(null, valor, cliente, parcelas, dataVenda, fornecedor); // Produto é null
        gestorDeVendas.adicionarVenda(venda); // Atualiza o saldo de vendas
        System.out.println("Venda registrada com sucesso!");
    }
}