package com.seuprojeto.aiva.servicos;

import java.util.Stack;

public class ServicoLogin {

    private Stack<String> pilhaLogin = new Stack<>();

    // Realiza o login e adiciona o usuário à pilha
    public void realizarLogin(String usuario) {
        pilhaLogin.push(usuario);  // Usuário logado
        System.out.println("Usuário " + usuario + " logado.");
    }

    // Desfaz o login (remove o usuário da pilha)
    public void logout() {
        if (!pilhaLogin.isEmpty()) {
            String usuario = pilhaLogin.pop();
            System.out.println("Usuário " + usuario + " deslogado.");
        } else {
            System.out.println("Nenhum usuário logado.");
        }
    }
}
