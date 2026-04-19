package br.com.FolhaDePagamento;

import br.com.FolhaDePagamento.Dao.DepartamentoDao;
import br.com.FolhaDePagamento.Model.Departamento;
import br.com.FolhaDePagamento.Model.Dependente;
import br.com.FolhaDePagamento.Model.Funcionario;
import br.com.FolhaDePagamento.Persistence.ConnectionFactory;
import br.com.FolhaDePagamento.Persistence.DatabaseInitializer;
import br.com.FolhaDePagamento.Services.Csv.CsvFileReader;
import br.com.FolhaDePagamento.Services.Csv.CsvResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static br.com.FolhaDePagamento.Services.Csv.CsvFileReader.lerArquivoCsv;

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
                        calcularLoteViaArquivo(sc);
                        break;
                    case "2":
                        System.out.println("Calcular folha avulsa.");
                        break;
                    case "3":
                        System.out.println("Listar Todos os Departamentos");
                        DepartamentoDao dep = new DepartamentoDao();
                        List<Departamento> departamentos = dep.listar();
                        for (Departamento departamento : departamentos){
                            System.out.println(departamento);
                        }
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
        try (Connection connection = new ConnectionFactory().getConnection()) {
            System.out.println("Conexao testada com sucesso.");
        } catch (SQLException e) {
            System.out.println("Falha ao testar conexao: " + e.getMessage());
        }
    }

    private static String lerString(Scanner sc, String mensagem) {
        System.out.print(mensagem);
        return sc.nextLine();
    }


    // TODO
    //  Realiza os cálculos de INSS e IR.
    //  Salva tudo no banco.
    //  Gera o CSV de saída no local especificado pelo usuário.

    private static void calcularLoteViaArquivo(Scanner sc) {
        String caminhoDeEntrada = lerString(sc, "Digite o caminho completo do arquivo de entrada: ");
        String caminhoDeSaida = lerString(sc, "Digite o caminho completo do arquivo de entrada: ");
        //Lê o CSV de entrada
        CsvResult resultado = CsvFileReader.lerArquivoCsv(caminhoDeEntrada);
        // cria objetos
        List<Funcionario> func = resultado.getFuncionarios();
        List<Dependente> dep = resultado.getDependentes();

        System.out.println(func.toString());
        //System.out.println("\n\n");
        //System.out.println("Dependentes: \n");
       //System.out.println(dep.toString());
    }
}