# Sistema de Folha de Pagamento - Grupo 5

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white" />
  <img src="https://img.shields.io/badge/IntelliJ_IDEA-000000?style=for-the-badge&logo=intellij-idea&logoColor=white" />
  <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white" />
</p>

## 📖 Sobre o Projeto
Projeto final desenvolvido para a disciplina de **Programação Orientada a Objetos (POO)** do Serratec. O sistema automatiza o processamento de folhas de pagamento, calculando impostos (INSS e IRRF) com base nas tabelas de 2025 e garantindo a persistência total via JDBC.

## 🛠️ Tecnologias e Conceitos
- **Java 17:** Uso de Streams, Lambdas e Herança Abstrata.
- **PostgreSQL:** Base de dados relacional para armazenamento persistente.
- **Padrão DAO:** Isolamento total da lógica de acesso a dados.
- **I/O de Arquivos:** Processamento de ficheiros CSV para entrada e saída de dados.

## 📂 Estrutura de Pacotes
A arquitetura foi desenhada para garantir a máxima organização e separação de responsabilidades:

```text
src/br/com/FolhaDePagamento
├── Dao/          # Interfaces e implementações de acesso ao banco (JDBC)
├── Enum/         # Constantes enumeradas (ex: Parentesco)
├── Exceptions/   # Exceções personalizadas (ex: CpfInvalido, SalarioInvalido)
├── Interfaces/   # Contratos para os motores de cálculo e serviços
├── Model/        # Classes de domínio (ex: Funcionario, Dependente, Pessoa)
├── Persistence/  # Configuração da conexão com o banco de dados
├── Services/     # Regras de negócio e processamento de arquivos CSV
└── Util/         # Ferramentas de apoio
````

## ✨ Funcionalidades

  - [x] **Cálculo em Lote:** Lê um arquivo CSV e gera outro com os resultados.
  - [x] **Cálculo Manual:** Cadastro via console com exibição do recibo.
  - [x] **Banco de Dados:** Salva funcionários e históricos de pagamentos.
  - [x] **Segurança:** Valida CPFs e salários mínimos automaticamente.
  - [x] **Busca Rápida:** Consulta de holerite por CPF direto do banco.

## 🚀 Como Executar

1.  Clone o repositório.
2.  Certifique-se de que o **PostgreSQL** está rodando na porta `5432`.
3.  Adicione o driver JDBC do PostgreSQL no seu projeto do IntelliJ.
4.  Execute a classe principal (`Main`).

-----
<p align="center">
Desenvolvido por:

<b>João Gabriel Silva Barros</b>, <b>Yuri dos Santos Martins</b>, <b>Marcelo Ribeiro</b>, <b>Nívea D'Avila</b> e <b>Pedro Pinto Martins de Souza.</b>

Residência em TIC - Serratec 2026
</p>
