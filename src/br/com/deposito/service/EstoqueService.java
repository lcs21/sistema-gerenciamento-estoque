package br.com.deposito.service;

import br.com.deposito.dao.FornecedorDAO;
import br.com.deposito.dao.ProdutoDAO;
import br.com.deposito.entity.Fornecedor;
import br.com.deposito.entity.Produto;
import java.util.List;

public class EstoqueService {
    private ProdutoDAO produtoDAO;
    private FornecedorDAO fornecedorDAO;

    public EstoqueService() {
        this.produtoDAO = new ProdutoDAO();
        this.fornecedorDAO = new FornecedorDAO();
    }

    // --- MÉTODOS DE FORNECEDOR ---

    public void cadastrarFornecedor(String nome, String telefone, String email) {
        // Poderia adicionar validações aqui (ex: se nome é vazio)
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Erro: O nome do fornecedor é obrigatório.");
            return;
        }

        Fornecedor f = new Fornecedor(nome, telefone, email);
        fornecedorDAO.salvar(f);
        System.out.println("Fornecedor cadastrado com sucesso!");
    }

    public void listarFornecedores() {
        List<Fornecedor> lista = fornecedorDAO.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum fornecedor cadastrado.");
        } else {
            for (Fornecedor f : lista) {
                System.out.println(f);
            }
        }
    }

    // --- MÉTODOS DE PRODUTO ---

    public void cadastrarProduto(String nome, int quantidade, double preco, int fornecedorId) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Erro: O nome do produto é obrigatório.");
            return;
        }
        if (quantidade < 0) {
            System.out.println("Erro: A quantidade não pode ser negativa.");
            return;
        }
        if (preco <= 0) {
            System.out.println("Erro: O preço deve ser maior que zero.");
            return;
        }

        Produto p = new Produto(nome, quantidade, preco, fornecedorId);
        produtoDAO.salvar(p);
        System.out.println("Produto cadastrado com sucesso!");
    }

    public boolean produtoExiste(int id) {
        for (Produto p : produtoDAO.listarTodos()) {
            if (p.getId() == id) return true;
        }
        return false;
    }

    public void listarProdutos() {
        List<Produto> lista = produtoDAO.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto p : lista) {
                System.out.println(p);
            }
        }
    }

    public void atualizarProduto(int id, String novoNome, int novaQtd, double novoPreco) {
        // Validação simples
        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        }
        if (novoNome == null || novoNome.trim().isEmpty()) {
            System.out.println("Erro: O novo nome não pode ser vazio.");
            return;
        }
        if (novaQtd < 0) {
            System.out.println("Erro: Quantidade negativa.");
            return;
        }
        if (novoPreco <= 0) {
            System.out.println("Erro: Preço deve ser positivo.");
            return;
        }

        // Cria objeto temporário apenas para passar os dados.
        // Passamos 0 no fornecedorId pois o método atualizar do DAO não altera o fornecedor.
        Produto p = new Produto(novoNome, novaQtd, novoPreco, 0);
        p.setId(id); // Importante setar o ID para o WHERE do SQL

        produtoDAO.atualizar(p);
        System.out.println("Produto atualizado com sucesso!");
    }

    public void removerProduto(int id) {
        if (id <= 0) {
            System.out.println("ID inválido.");
            return;
        }

        boolean sucesso = produtoDAO.deletar(id);

        if (sucesso) {
            System.out.println("Produto removido e IDs reajustados com sucesso!");
        } else {
            System.out.println("Erro: Produto não encontrado com o ID " + id + ".");
        }
    }

    public void buscarProduto(String termo) {
        if (termo == null || termo.trim().isEmpty()) {
            System.out.println("Erro: Digite um termo para busca.");
            return;
        }

        List<Produto> resultados = produtoDAO.buscarPorNome(termo);

        if (resultados.isEmpty()) {
            System.out.println("Nenhum produto encontrado com o termo: " + termo);
        } else {
            System.out.println("--- Resultados da Busca ---");
            for (Produto p : resultados) {
                System.out.println(p);
            }
        }
    }
}