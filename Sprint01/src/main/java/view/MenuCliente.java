package view;

import controller.GestorDeClientes;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Menu para operações relacionadas a clientes.
 */
public class MenuCliente {
    private final GestorDeClientes gestorDeClientes;
    private final Scanner scanner;

    public MenuCliente(GestorDeClientes gestorDeClientes) {
        this.gestorDeClientes = gestorDeClientes;
        this.scanner = new Scanner(System.in);
    }

    public boolean exibirMenu(){

        while (true) {
            System.out.println("------------ Acesse sua conta -----------");
            System.out.println(" 1. Login   2. Cadastre-se   3. Sair");
            System.out.println("-----------------------------------------");
            System.out.print("Opção: ");

            try {
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                return realizarLogin();
            } else if (opcao == 2) {
                cadastrarCliente();
            } else if (opcao == 3) {
                 sairPrograma();
                 return false; // Para sair do loop
            } else {
                System.out.println("Opção inválida!");
            }
        } catch (InputMismatchException e) {
                System.out.println("Opção inválida! Insira um número.");
                scanner.nextLine();
            }
        }
    }

    private void cadastrarCliente() {
        System.out.println("\n--------------- Cadastre-se -------------");

        System.out.print("Digite o seu nome: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o seu email: ");
        String email = scanner.nextLine();

        System.out.print("Digite a sua senha: ");
        String senha = scanner.nextLine();

        System.out.print("Digite a sua idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        if (gestorDeClientes.cadastrarCliente(nome, email, senha, idade)) {
            System.out.println("Cliente cadastrado com sucesso!");
        } else {
            System.out.println("Erro: Cliente já existe!");
        }
    }

    private boolean realizarLogin() {
        System.out.println("------------------ Login ----------------");
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        if (gestorDeClientes.autenticarCliente(email, senha)) {
            System.out.println("Login realizado com sucesso!");
            return true; // Login bem-sucedido
        } else {
            System.out.println("Erro: Email ou senha incorretos!");
            return false;
        }
    }

    private void sairPrograma(){
       System.out.println("Saindo do programa...");
       System.exit(0); // Termina a execução do programa
    }
}

