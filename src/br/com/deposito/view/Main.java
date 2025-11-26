package br.com.deposito.view;

import br.com.deposito.config.DatabaseConnection;
import br.com.deposito.service.EstoqueService;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // --- TESTE DE CONEXÃO ---
        System.out.println("Inicializando sistema...");
        System.out.println("Testando conexão com o banco de dados...");

        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {
                System.out.println("✅ Conexão estabelecida com sucesso!");
                // Pequena pausa para o usuário ver a mensagem de sucesso antes do menu abrir
                try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
            }
        } catch (Exception e) {
            System.err.println("\n❌ ERRO CRÍTICO: Não foi possível conectar ao banco de dados.");
            System.err.println("Detalhes do erro: " + e.getMessage());
            System.out.println("\nDicas para solução:");
            System.out.println("1. Verifique se o XAMPP/MySQL está rodando.");
            System.out.println("2. Verifique se o banco de dados 'deposito_db' foi criado.");
            System.out.println("3. Verifique usuário e senha no arquivo DatabaseConnection.java.");
            System.out.println("\nO sistema será encerrado.");
            return; // Encerra o programa se não houver banco
        }
        EstoqueService estoqueService = new EstoqueService();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        System.out.println("--- SISTEMA DE DEPÓSITO ---");

        while (opcao != 9) {
            limparTela(); // Limpa a tela antes de mostrar o menu novamente

            // Menu
            System.out.println("\n----------------------------");
            System.out.println("-- PRODUTOS --");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Atualizar Produto");
            System.out.println("4. Buscar Produto");
            System.out.println("5. Remover Produto");
            System.out.println("\n-- FORNECEDORES --");
            System.out.println("6. Cadastrar Fornecedor");
            System.out.println("7. Listar Fornecedores");
            System.out.println("\n9. Sair");
            System.out.println("----------------------------");
            System.out.print("Escolha uma opção: ");

            if(scanner.hasNextInt()){
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar buffer do teclado
            } else {
                scanner.nextLine(); // descarta entrada inválida
                System.out.println("Por favor digite um número.");
                aguardarRetorno(scanner);
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.println("\n--- Cadastro de Produto ---");
                    System.out.println("Fornecedores disponíveis:");
                    estoqueService.listarFornecedores();

                    System.out.print("\nID do Fornecedor: ");
                    if (scanner.hasNextInt()) {
                        int idForn = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Nome do Produto: ");
                        String nome = scanner.nextLine();

                        System.out.print("Quantidade: ");
                        if (scanner.hasNextInt()) {
                            int qtd = scanner.nextInt();

                            System.out.print("Preço: ");
                            if (scanner.hasNextDouble()) {
                                double preco = scanner.nextDouble();
                                scanner.nextLine();
                                estoqueService.cadastrarProduto(nome, qtd, preco, idForn);
                            } else {
                                System.out.println("Erro: Preço inválido.");
                                scanner.next();
                            }
                        } else {
                            System.out.println("Erro: Quantidade inválida.");
                            scanner.next();
                        }
                    } else {
                        System.out.println("Erro: ID inválido.");
                        scanner.next();
                    }
                    aguardarRetorno(scanner);
                    break;
                case 2:
                    System.out.println("\n--- Lista de Estoque ---");
                    estoqueService.listarProdutos();
                    aguardarRetorno(scanner);
                    break;
                case 3:
                    System.out.println("\n--- Produtos Cadastrados ---");
                    estoqueService.listarProdutos();
                    System.out.println("\n--- Atualizar Produto ---");
                    System.out.print("ID do produto: ");
                    if (scanner.hasNextInt()) {
                        int idAt = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Novo Nome: ");
                        String nNome = scanner.nextLine();
                        System.out.print("Nova Qtd: ");
                        int nQtd = scanner.nextInt();
                        System.out.print("Novo Preço: ");
                        double nPreco = scanner.nextDouble();
                        scanner.nextLine();
                        estoqueService.atualizarProduto(idAt, nNome, nQtd, nPreco);
                    } else {
                        System.out.println("ID inválido.");
                        scanner.next();
                    }
                    aguardarRetorno(scanner);
                    break;
                case 4:
                    System.out.println("\n--- Buscar Produto ---");
                    System.out.print("Termo de busca: ");
                    String termo = scanner.nextLine();
                    estoqueService.buscarProduto(termo);
                    aguardarRetorno(scanner);
                    break;
                case 5:
                    System.out.println("\n--- Remover Produto ---");
                    System.out.print("ID para remover: ");
                    if (scanner.hasNextInt()) {
                        int idRem = scanner.nextInt();
                        scanner.nextLine();
                        estoqueService.removerProduto(idRem);
                    } else {
                        System.out.println("ID inválido.");
                        scanner.next();
                    }
                    aguardarRetorno(scanner);
                    break;
                case 6:
                    System.out.println("\n--- Cadastro de Fornecedor ---");
                    System.out.print("Nome da Empresa: ");
                    String nomeF = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telF = scanner.nextLine();
                    System.out.print("Email: ");
                    String emailF = scanner.nextLine();

                    estoqueService.cadastrarFornecedor(nomeF, telF, emailF);
                    aguardarRetorno(scanner);
                    break;
                case 7:
                    System.out.println("\n--- Lista de Fornecedores ---");
                    estoqueService.listarFornecedores();
                    aguardarRetorno(scanner);
                    break;
                case 9:
                    System.out.println("Encerrando sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    aguardarRetorno(scanner);
            }
        }
        scanner.close();
    }

    // Método auxiliar para pausar a tela
    private static void aguardarRetorno(Scanner scanner) {
        System.out.println("\nPressione ENTER para voltar ao menu...");
        scanner.nextLine(); // Aguarda o usuário apertar Enter
    }

    // Função para limpar o console
    private static void limparTela() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}