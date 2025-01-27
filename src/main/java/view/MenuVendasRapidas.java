package view;

import controller.GestorDeVendas;
import model.Venda;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MenuVendasRapidas {
    private GestorDeVendas gestorDeVendas;
    private Scanner scanner;

    public MenuVendasRapidas(GestorDeVendas gestorDeVendas) {
        this.gestorDeVendas = gestorDeVendas;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        System.out.println("Vendas Rápidas");

        // Coletar dados da venda
        System.out.print("Digite o nome do produto (ex: Avon, Natura): ");
        String produto = scanner.nextLine();

        System.out.print("Digite o valor da venda: ");
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Limpa o buffer

        System.out.println("Escolha a forma de pagamento: ");
        System.out.println("1. À Vista");
        System.out.println("2. Parcelado");
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

        // Registrar a venda
        registrarVenda(produto, valor, pagamento, parcelas);

        // Compartilhar comprovante
        compartilharComprovante(produto, valor, pagamento, parcelas);
    }

    private void registrarVenda(String produto, double valor, int pagamento, int parcelas) {
        // Data de vencimento começa com a data atual
        LocalDate dataVencimento = LocalDate.now();

        // Se for parcelado, calculamos a data de vencimento para cada parcela
        if (pagamento == 2 && parcelas > 0) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Exibe as parcelas com as datas de vencimento
            for (int i = 1; i <= parcelas; i++) {
                // Adiciona "i" meses à data de vencimento
                LocalDate dataParcela = dataVencimento.plusMonths(i);

                // Exibe a data de vencimento de cada parcela
                System.out.println("Parcela " + i + ": " + dataParcela.format(formatter));
            }
        } else {
            // Se for à vista, apenas exibe a data atual
            System.out.println("Venda à vista! Data de vencimento: " + dataVencimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }

        // Adiciona a venda ao sistema
        gestorDeVendas.adicionarVenda(valor);
        System.out.println("\nVenda registrada com sucesso!");
        System.out.println("Produto: " + produto);
        System.out.println("Valor: R$ " + valor);
        if (pagamento == 1) {
            System.out.println("Forma de Pagamento: À Vista");
        } else {
            System.out.println("Forma de Pagamento: Parcelado em " + parcelas + "x");
        }
    }

    private void compartilharComprovante(String produto, double valor, int pagamento, int parcelas) {
        // Simulação do compartilhamento de um comprovante
        System.out.println("\nCompartilhando o comprovante...");
        System.out.println("Comprovante de Venda:");
        System.out.println("Produto: " + produto);
        System.out.println("Valor: R$ " + valor);
        if (pagamento == 1) {
            System.out.println("Forma de Pagamento: À Vista");
        } else {
            System.out.println("Forma de Pagamento: Parcelado em " + parcelas + "x");
        }
        System.out.println("Venda cadastrada com sucesso!");
    }
}
