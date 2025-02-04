package model;

public class Cliente {
    private String nome;
    private String email;
    private String senha;
    private int idade;

    public Cliente(String nome, String email, String senha, int idade) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public int getIdade() {
        return idade;
    }
}
