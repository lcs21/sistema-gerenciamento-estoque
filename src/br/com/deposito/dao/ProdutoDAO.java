package br.com.deposito.dao;

import br.com.deposito.config.DatabaseConnection;
import br.com.deposito.entity.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void salvar(Produto produto) {
        String sql = "INSERT INTO produtos (nome, quantidade, preco, fornecedor_id) VALUES (?, ?, ?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getFornecedorId());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setQuantidade(rs.getInt("quantidade"));
                p.setPreco(rs.getDouble("preco"));
                p.setFornecedorId(rs.getInt("fornecedor_id"));
                produtos.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public void atualizar(Produto produto) {
        String sql = "UPDATE produtos SET nome = ?, quantidade = ?, preco = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deletar(int id) {
        String sqlDelete = "DELETE FROM produtos WHERE id = ?";
        String sqlUpdateIds = "UPDATE produtos SET id = id - 1 WHERE id > ?";
        String sqlResetAutoInc = "ALTER TABLE produtos AUTO_INCREMENT = 1";

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            // 1. Tenta remover o produto
            int linhasAfetadas = 0;
            try (PreparedStatement stmt = conn.prepareStatement(sqlDelete)) {
                stmt.setInt(1, id);
                linhasAfetadas = stmt.executeUpdate(); // Retorna quantas linhas foram apagadas
            }

            // Se nenhuma linha foi apagada (ID não existe), encerra aqui
            if (linhasAfetadas == 0) {
                conn.rollback();
                return false;
            }

            // 2. Se apagou, reorganiza os IDs dos produtos restantes
            try (PreparedStatement stmt = conn.prepareStatement(sqlUpdateIds)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }

            conn.commit(); // Salva as alterações

            // 3. Reseta o AUTO_INCREMENT
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(sqlResetAutoInc);
            }

            return true; // Sucesso

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false; // Erro técnico
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Produto> buscarPorNome(String termo) {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos WHERE nome LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + termo + "%"); // O % permite buscar parte do nome

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Produto p = new Produto();
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setQuantidade(rs.getInt("quantidade"));
                    p.setPreco(rs.getDouble("preco"));
                    p.setFornecedorId(rs.getInt("fornecedor_id"));
                    produtos.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }
}