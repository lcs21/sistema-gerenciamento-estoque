package br.com.deposito.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Configurações padrão do XAMPP
    private static final String URL = "jdbc:mysql://localhost:3306/deposito_db";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // XAMPP geralmente vem sem senha

    public static Connection getConnection() {
        try {
            // Tenta carregar o driver moderno do MySQL explicitamente
            // Isso evita erros de "No suitable driver found" em algumas IDEs
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                // Fallback para driver antigo se o novo não existir
                Class.forName("com.mysql.jdbc.Driver");
            }

            return DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver do banco de dados não encontrado! Verifique se a biblioteca 'mysql-connector-java' foi adicionada ao projeto.", e);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }
}