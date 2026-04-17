package br.com.FolhaDePagamento;

import br.com.FolhaDePagamento.Persistence.ConnectionFactory;
import br.com.FolhaDePagamento.Persistence.DatabaseInitializer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try (Connection connection = new ConnectionFactory().getConnection()) {
            DatabaseInitializer.run(connection);
        } catch (SQLException e) {
            System.err.println("Falha ao inicializar banco: " + e.getMessage());
            return;
        }

        Scanner sc = new Scanner(System.in);
        String opcaoDeSaida = "";

        do{
            try{
                exibirMenu();
                String opcaoMenu = capturarOpcaoMenu(sc);

                switch (opcaoMenu) {
                    case "1":
                        System.out.println("Calcular folha em lote.");
                        break;
                    case "2":
                        System.out.println("Calcular folha avulsa.");
                        break;
                    case "3":
                        System.out.println("Listar Todos os Departamentos");
                        break;
                    case "4":
                        System.out.println("Listar Funcionários por Departamento");
                        break;
                    case "5":
                        System.out.println("Listar Histórico de Folhas de Pagamento");
                        break;
                    case "6":
                        testarConexaoComBancoDeDados();
                        break;
                    case "0":
                        opcaoDeSaida = "S";
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }

                if (opcaoDeSaida.equalsIgnoreCase("S")) {
                    break;
                }

            }catch(Exception e) {
                System.out.println("Erro: " + e.getMessage());
                e.printStackTrace();
            }

            System.out.println("Deseja sair do sistema(S/N):");
            opcaoDeSaida = sc.nextLine();
        }
        while(!opcaoDeSaida.equalsIgnoreCase("S"));

        sc.close();
        System.out.println("\n\n=================================");
        System.out.println("Programa finalizado com sucesso.");
        System.out.println("=================================");
    }

    public static void exibirMenu(){
        System.out.println("\n\n\n===============================================");
        System.out.println("------ Sistema de Pagamentos - Serratec ------- ");
        System.out.println("===============================================");
        System.out.println("\n --- PROCESSAMENTO DE DADOS ---");
        System.out.println("\t1. Calcular Folha em Lote (Via Arquivo CSV)");
        System.out.println("\t2. Calcular Folha Avulsa (Cadastro Manual)");
        System.out.println("\n --- RELATÓRIOS DO BANCO DE DADOS ---");
        System.out.println("\t3. Listar Todos os Departamentos");
        System.out.println("\t4. Listar Funcionários por Departamento");
        System.out.println("\t5. Listar Histórico de Folhas de Pagamento");
        System.out.println("\n --- TESTE DE CONEXÃO COM BANCO DE DADOS ---");
        System.out.println("\t6. Testar conexão com banco de dados");
        System.out.println("\n\t0. Sair");
        System.out.println("--------------------------------------- ");
    }

    private static String capturarOpcaoMenu(Scanner sc) {
        System.out.print("Digite a opção desejada: ");
        return sc.nextLine().trim();
    }

    private static void testarConexaoComBancoDeDados() {
        Connection coneection = new ConnectionFactory().getConnection();
    }
}