package br.com.deposito.entity;

public class Produto {
    private int id;
    private String nome;
    private int quantidade;
    private double preco;
    private int fornecedorId;

    public Produto() {}

    public Produto(String nome, int quantidade, double preco, int fornecedorId) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.fornecedorId = fornecedorId;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public int getFornecedorId() { return fornecedorId; }
    public void setFornecedorId(int fornecedorId) { this.fornecedorId = fornecedorId; }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: " + nome + " | Qtd: " + quantidade +
                " | Preço: R$" + preco + " | ID Fornecedor: " + fornecedorId;
    }
}