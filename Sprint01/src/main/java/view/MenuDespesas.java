package view;

import controller.GestorDeDespesas;
import model.Despesa;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MenuDespesas {
    private final GestorDeDespesas gestorDeDespesas;
    private final Scanner scanner;

    public MenuDespesas(GestorDeDespesas gestorDeDespesas) {
        this.gestorDeDespesas = gestorDeDespesas;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        while (true) {
            // Exibe os saldos acima do menu, sempre atualizados
            System.out.println("-------------------- Menu Despesas -------------------------+");
            System.out.println("| /// Total de despesas: R$ " + gestorDeDespesas.getTotalDespesas());
            System.out.println("| 1. Registrar Despesa   2. Histórico de despesas   3. Sair |");
            System.out.println("------------------------------------------------------------+");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            if (opcao == 1) {
                registrarDespesa();
            } else if (opcao == 2) {
                historicoDespesas();
            } else if (opcao == 3) {
                System.out.println("Saindo do sistema...");
                break;
            } else {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private void registrarDespesa() {
        String nome;
        while (true) {
            System.out.print("Digite o nome da despesa: ");
            nome = scanner.nextLine();
            if (nome.matches("[a-zA-Z\\s]+")) {
                break;
            } else {
                System.out.println("Nome inválido! Por favor, digite um nome válido.");
            }
        }

        double valor;
        while (true) {
            System.out.print("Digite o valor da despesa: ");
            String valorStr = scanner.nextLine();
            try {
                valor = Double.parseDouble(valorStr);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Por favor, digite um número.");
            }
        }

        LocalDate dataPagamento;
        while (true) {
            System.out.print("Digite a data de pagamento (dd/MM/yyyy): ");
            String dataPagamentoStr = scanner.nextLine();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                dataPagamento = LocalDate.parse(dataPagamentoStr, formatter);
                break;
            } catch (Exception e) {
                System.out.println("Data inválida! Por favor, digite uma data no formato dd/MM/yyyy.");
            }
        }

        System.out.print("Digite uma referência (curta frase): ");
        String referencia = scanner.nextLine();

        // Criar e registrar a despesa
        Despesa despesa = new Despesa(nome, valor, dataPagamento, referencia);
        gestorDeDespesas.adicionarDespesa(despesa); // Atualiza o saldo de despesas
        System.out.println("Despesa registrada com sucesso!");
    }

    private void historicoDespesas() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("+--------------------- Histórico de Despesas --------------------------+");
        System.out.println("|    Nome    |    Valor   |    Data    |   Referência   |");

        for (Despesa despesa : gestorDeDespesas.getHistoricoDespesas()) {
            System.out.printf("| %10s | %10s | %10s | %15s |%n",
                    despesa.getNome(),
                    String.format("%.2f", despesa.getValor()),
                    despesa.getDataPagamento().format(formatter),
                    despesa.getReferencia());
        }

        System.out.println("+--------------------------------------------------------------------+");
        System.out.println("Pressione Enter para voltar ao menu principal...");
        scanner.nextLine(); // Espera o usuário pressionar Enter para voltar ao menu
    }
}