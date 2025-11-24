package br.com.deposito.entity;

public class Fornecedor {
    private int id;
    private String nome;
    private String telefone;
    private String email;

    public Fornecedor() {}

    public Fornecedor(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "ID: " + id + " | Fornecedor: " + nome + " (" + telefone + ")";
    }
}