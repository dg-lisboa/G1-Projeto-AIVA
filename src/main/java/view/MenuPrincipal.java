package view;

import controller.GestorDeClientes;
import controller.GestorDeVendas;
import controller.GestorDeDespesas;
import java.util.Scanner;

/**
 * Menu principal do sistema.
 */
public class MenuPrincipal {
    private GestorDeClientes gestorDeClientes;
    private GestorDeVendas gestorDeVendas;
    private GestorDeDespesas gestorDeDespesas;
    private Scanner scanner;
    private boolean loginBemSucedido;

    public MenuPrincipal() {
        this.gestorDeClientes = new GestorDeClientes();
        this.gestorDeVendas = new GestorDeVendas();
        this.gestorDeDespesas = new GestorDeDespesas();
        this.scanner = new Scanner(System.in);
        this.loginBemSucedido = false;
    }

    public void exibirMenu() {
        while (true) {
            if (!loginBemSucedido) {
                // Exibe menu de login se o login não foi realizado
                System.out.println("Bem-vindo ao sistema Aiva! Faça o login para continuar.");
                MenuCliente menuCliente = new MenuCliente(gestorDeClientes);
                loginBemSucedido = menuCliente.exibirMenu(); // Realiza login
                continue; // Retorna para o topo do loop até que o login seja bem-sucedido
            }

            // Menu principal, exibido após login bem-sucedido
            System.out.println("Bem-vindo ao sistema Aiva! Escolha uma opção:");

            // Exibe o saldo de vendas, despesas e o saldo total
            System.out.println("Saldo de Vendas: R$ " + gestorDeVendas.getTotalVendas());
            System.out.println("Saldo de Despesas: R$ " + gestorDeDespesas.getTotalDespesas());
            System.out.println("Saldo Total: R$ " + (gestorDeVendas.getTotalVendas() - gestorDeDespesas.getTotalDespesas()));

            System.out.println("1. Menu de Vendas");
            System.out.println("2. Menu de Despesas");
            System.out.println("3. Vendas Rápidas");  // Nova opção
            System.out.println("4. Sair");
            System.out.print("Opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            if (opcao == 1) {
                new MenuVendas(gestorDeVendas).exibirMenu();
            } else if (opcao == 2) {
                new MenuDespesas(gestorDeDespesas).exibirMenu();
            } else if (opcao == 3) {
                new MenuVendasRapidas(gestorDeVendas).exibirMenu(); // Chama o Menu de Vendas Rápidas
            } else if (opcao == 4) {
                // Volta para o MenuCliente (login)
                loginBemSucedido = false; // Usuário será deslogado
                System.out.println("Você foi deslogado. Retornando para a tela de login...");
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
