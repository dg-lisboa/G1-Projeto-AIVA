package view;

import controller.GestorDeClientes;
import controller.GestorDeVendas;
import controller.GestorDeDespesas;
import java.util.Scanner;

/**
 * Menu principal do sistema.
 */
public class MenuPrincipal {
    private final GestorDeClientes gestorDeClientes;
    private final GestorDeVendas gestorDeVendas;
    private final GestorDeDespesas gestorDeDespesas;
    private final Scanner scanner;
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
                MenuCliente menuCliente = new MenuCliente(gestorDeClientes);
                loginBemSucedido = menuCliente.exibirMenu(); // Realiza login
                continue; // Retorna para o topo do loop até que o login seja bem-sucedido
            }

            // Menu principal, exibido após login bem-sucedido
            System.out.println("\n///////////  Bem-vindo ao sistema Aiva!");

            // Exibe o saldo de vendas, despesas e o saldo total
            System.out.println("------------------- Saldo Atual ----------------------");
            System.out.println("Total de Vendas  -  Despesas  =   Lucro Bruto  ");
            System.out.println("      "+ gestorDeVendas.getTotalVendas() + "        -     "+ gestorDeDespesas.getTotalDespesas() + "    =       " + (gestorDeVendas.getTotalVendas() - gestorDeDespesas.getTotalDespesas()));
            System.out.println("------------------ Menu Principal --------------------");
            System.out.println(" 1. Vendas   2. Despesas   3. Venda Rápida   4. Sair");
            System.out.println("------------------------------------------------------");
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
