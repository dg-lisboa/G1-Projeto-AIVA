package controller;

import model.Cliente;
import java.util.HashMap;
import java.util.Map;

public class GestorDeClientes {
    private Map<String, Cliente> clientes;

    public GestorDeClientes() {
        clientes = new HashMap<>();
    }

    public boolean cadastrarCliente(String nome, String email, String senha, int idade) {
        if (clientes.containsKey(email)) {
            return false; // Cliente já existe
        }
        Cliente cliente = new Cliente(nome, email, senha, idade);
        clientes.put(email, cliente);
        return true;
    }

    public boolean autenticarCliente(String email, String senha) {
        Cliente cliente = clientes.get(email);
        return cliente != null && cliente.getSenha().equals(senha);
    }

    public Cliente getCliente(String email) {
        return clientes.get(email);
    }
}
