package view;

import controller.GestorDeClientes;
import java.util.Scanner;

/**
 * Menu para operações relacionadas a clientes.
 */
public class MenuCliente {
    private GestorDeClientes gestorDeClientes;
    private Scanner scanner;

    public MenuCliente(GestorDeClientes gestorDeClientes) {
        this.gestorDeClientes = gestorDeClientes;
        this.scanner = new Scanner(System.in);
    }

    public boolean exibirMenu() {
        while (true) {
            System.out.println("Menu de Clientes:");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Login");
            System.out.println("3. Sair");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                cadastrarCliente();
            } else if (opcao == 2) {
                return realizarLogin();
            } else if (opcao == 3) {
                return false; // Sair, não fazer login
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarCliente() {
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();
        System.out.print("Digite a idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        if (gestorDeClientes.cadastrarCliente(nome, email, senha, idade)) {
            System.out.println("Cliente cadastrado com sucesso!");
        } else {
            System.out.println("Erro: Cliente já existe!");
        }
    }

    private boolean realizarLogin() {
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        if (gestorDeClientes.autenticarCliente(email, senha)) {
            System.out.println("Login realizado com sucesso!");
            return true; // Login bem-sucedido
        } else {
            System.out.println("Erro: Email ou senha incorretos!");
        }
        return false; // Login falhou
    }
}
