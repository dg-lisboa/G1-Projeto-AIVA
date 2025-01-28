package view;

import controller.GestorDeVendas;
import model.Venda;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                historicoVenda();
            } else if (opcao == 3) {
                System.out.println("Saindo do sistema...");
                break;
            } else {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private void registrarVenda() {
        String produto;
        while (true) {
            System.out.print("Digite o nome do produto: ");
            produto = scanner.nextLine();
            if (produto.matches("[a-zA-Z0-9\\s]+")) {
                break;
            } else {
                System.out.println("Nome inválido! Por favor, digite um nome válido (somente letras e números).");
            }
        }

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

        LocalDate dataVenda;
        while (true) {
            System.out.print("Digite a data da venda (dd/MM/yyyy): ");
            String dataVendaStr = scanner.nextLine();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                dataVenda = LocalDate.parse(dataVendaStr, formatter);
                break;
            } catch (Exception e) {
                System.out.println("Data inválida! Por favor, digite uma data no formato dd/MM/yyyy.");
            }
        }

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
            System.out.print("Escolha o número de parcelas (1x, 2x, 3x, ...): ");
            parcelas = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer
        }

        // Criar e registrar a venda
        Venda venda = new Venda(produto, valor, cliente, parcelas, dataVenda, fornecedor);
        gestorDeVendas.adicionarVenda(venda); // Atualiza o saldo de vendas
        System.out.println("Venda registrada com sucesso!");
    }

    private void historicoVenda() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("+--------------------- Histórico de Vendas ---------------------------+");
        System.out.println("|    Valor   | Qtd Parcelas  |    Data    |   Cliente   | Fornecedor  |");

        for (Venda venda : gestorDeVendas.getHistoricoVendas()) {
            System.out.printf("| %10s |      %2d       | %10s | %11s | %11s |%n",
                    String.format("%.2f", venda.getValor()),
                    venda.getParcelas(),
                    venda.getDataVenda().format(formatter),
                    venda.getCliente(),
                    venda.getFornecedor());
        }

        System.out.println("+---------------------------------------------------------------------+");
        System.out.println("Pressione Enter para voltar ao menu principal...");
        scanner.nextLine(); // Espera o usuário pressionar Enter para voltar ao menu
    }
}