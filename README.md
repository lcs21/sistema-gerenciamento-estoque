# Sistema de Gerenciamento de Depósito 📦

Projeto desenvolvido em Java para a disciplina de Projeto A3, focado no gerenciamento de estoque e controle de fornecedores através de uma interface via console.

## 🚀 Funcionalidades

- **Gerenciamento de Produtos:**
  - Cadastro de produtos vinculados a fornecedores.
  - Listagem completa de estoque.
  - Atualização de dados (preço, quantidade, nome).
  - Remoção de produtos (com reajuste automático de IDs).
  - Busca de produtos por nome.
- **Gerenciamento de Fornecedores:**
  - Cadastro de empresas parceiras.
  - Listagem de contatos.
- **Interface:**
  - Menu interativo via console.
  - Limpeza de tela automática para melhor usabilidade.

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java (JDK 21)
- **Banco de Dados:** MySQL
- **Conexão:** JDBC (Java Database Connectivity)
- **Driver:** MySQL Connector/J

## ⚙️ Pré-requisitos

Antes de começar, você precisará ter instalado em sua máquina:
- [Java JDK 21](https://www.oracle.com/java/technologies/downloads/) ou superior.
- [XAMPP](https://www.apachefriends.org/pt_br/index.html) ou Servidor MySQL instalado separadamente.
- Uma IDE Java (IntelliJ IDEA, Eclipse ou NetBeans).

## 📝 Como Rodar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/SEU-USUARIO/sistema-gerenciamento-estoque.git
   ```

2. **Configure o Banco de Dados:**
   - Abra seu gerenciador de banco de dados (phpMyAdmin, MySQL Workbench, DBeaver).
   - Crie um novo banco de dados ou execute o script SQL incluído no projeto:
     - Arquivo: `deposito_db - SQL` (localizado na raiz do projeto).

3. **Configure a Conexão:**
   - Verifique o arquivo `src/br/com/deposito/config/DatabaseConnection.java`.
   - As credenciais padrão estão configuradas para o XAMPP (`root` sem senha). Caso seu banco tenha senha, altere a constante `PASSWORD`.

4. **Execute a Aplicação:**
   - Abra o projeto na sua IDE.
   - Localize a classe principal: `src/br/com/deposito/view/Main.java`.
   - Execute o método `main`.

## 📂 Estrutura do Projeto

O projeto segue o padrão de arquitetura em camadas:
- **view:** Camada de interação com o usuário (Console).
- **service:** Regras de negócio e validações.
- **dao (Data Access Object):** Comunicação direta com o banco de dados.
- **entity:** Classes de modelo (POJO).
- **config:** Configurações de conexão.

## 👨‍💻 Autores

Desenvolvido por:
**Lucas Fernandes Affonso - 12624210495**
**Gabriel Dias de Oliveira - 12622121759**
**Bruno Werther Marangoni Abreu - 12622113501**
**Fernando Barbosa Ferreira - 1262216504**
**Pedro Magno Martins Azevedo - 1262225669**
