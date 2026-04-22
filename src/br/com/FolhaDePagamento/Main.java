package br.com.FolhaDePagamento;

import br.com.FolhaDePagamento.Dao.DepartamentoDao;
import br.com.FolhaDePagamento.Dao.FuncionarioDao;
import br.com.FolhaDePagamento.Enum.Parentesco;
import br.com.FolhaDePagamento.Exceptions.CpfInvalidoException;
import br.com.FolhaDePagamento.Model.Departamento;
import br.com.FolhaDePagamento.Model.Dependente;
import br.com.FolhaDePagamento.Model.FolhaDePagamento;
import br.com.FolhaDePagamento.Model.Funcionario;
import br.com.FolhaDePagamento.Persistence.ConnectionFactory;
import br.com.FolhaDePagamento.Persistence.DatabaseInitializer;
import br.com.FolhaDePagamento.Services.Csv.CsvFileReader;
import br.com.FolhaDePagamento.Services.Csv.CsvResult;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static br.com.FolhaDePagamento.Services.Csv.CsvFileRecord.gravarArquivoCsv;
import static br.com.FolhaDePagamento.Services.Inss.CalculoInssService.calcularInss;
import static br.com.FolhaDePagamento.Services.Irrf.CalculoIrpfService.calcularIRRF;
import static br.com.FolhaDePagamento.Services.SalarioLiquidoFinal.CalculoSalarioLiquidoService.salarioLiquido;
import static br.com.FolhaDePagamento.Services.Validators.CpfValidator.isValido;
import static br.com.FolhaDePagamento.Util.ConstantesNegocio.FORMATTER_BR;

public class Main {
    public static void main(String[] args) {
        ConnectionFactory.setVerbose(true);
        try (Connection connection = new ConnectionFactory().getConnection()) {
            DatabaseInitializer.run(connection);
        } catch (SQLException e) {
            System.err.println("Falha ao inicializar banco: " + e.getMessage());
            return;
        }

        Scanner sc = new Scanner(System.in);
        String opcaoDeSaida = "";

        do {
            try {
                exibirMenu();
                String opcaoMenu = capturarOpcaoMenu(sc);

                switch (opcaoMenu) {
                    case "1":
                        calcularLoteViaArquivo(sc);
                        break;
                    case "2":
                        calcularFolhaAvulsa(sc);
                        break;
                    case "3":
                        listarDepartamentos();
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

            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

            System.out.println("Deseja sair do sistema(S/N):");
            opcaoDeSaida = sc.nextLine();
        }
        while (!opcaoDeSaida.equalsIgnoreCase("S"));

        sc.close();
        System.out.println("\n\n=================================");
        System.out.println("Programa finalizado com sucesso.");
        System.out.println("=================================");
    }

    public static void exibirMenu() {
        System.out.println("\n\n===============================================");
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

    private static double lerDouble(Scanner sc, String mensagem) {
        System.out.print(mensagem);
        return sc.nextDouble();
    }

    private static void calcularLoteViaArquivo(Scanner sc) {
        String caminhoDeEntrada = lerString(sc, "Digite o caminho completo do arquivo de entrada: ");
        String caminhoDeSaida = lerString(sc, "Digite o caminho completo do arquivo de entrada: ");

        CsvResult resultado = CsvFileReader.lerArquivoCsv(caminhoDeEntrada);

        List<Funcionario> func = resultado.getFuncionarios();
        List<Dependente> dep = resultado.getDependentes();
        List<FolhaDePagamento> fp = new ArrayList<>();

        fp = calcularImpostos(func, dep);

        salvarNoBancoFolhaDePagamento(fp);

        gravarArquivoCsv(caminhoDeSaida, fp, func);
    }

    private static void calcularFolhaAvulsa(Scanner sc) throws CpfInvalidoException {
        List<Funcionario> func = new ArrayList<>();
        List<Dependente> dep = new ArrayList<>();
        List<FolhaDePagamento> fp = new ArrayList<>();

        String cpf = lerString(sc, "Digite o cpf do funcionário: ");
        while (!isValido(cpf)) {
            System.out.println("Cpf inválido. Tente novamente. ");
            cpf = lerString(sc, "Digite o cpf do funcionário novamente: ");
        }

        String nome = lerString(sc, "Digite o nome: ");
        LocalDate nascimento = LocalDate.parse(lerString(sc, "Digite a data nascimento(dd-MM-yyyy): "), FORMATTER_BR);
        double salario_bruto = lerDouble(sc, "Digite o salário bruto: ");
        sc.nextLine();

        listarDepartamentos();
        int id = lerIdDepartamentoValidado(sc);

        Funcionario funcionario = new Funcionario(cpf, nome, nascimento, salario_bruto, id);
        func.add(funcionario);

        FuncionarioDao funcionarioDao = new FuncionarioDao();
        funcionarioDao.inserir(funcionario);

        fp = calcularImpostos(func, lerDependentes(sc, dep, cpf));
        FolhaDePagamento folhaDePagamento = new FolhaDePagamento(cpf, LocalDate.now(), );


        System.out.print(fp.toString());
    }

    private static void listarDepartamentos() {
        System.out.println("\n=================================");
        System.out.println("=== Listagem de Departamentos ===");
        System.out.println("=================================");
        ConnectionFactory.setVerbose(false);
        DepartamentoDao dep = new DepartamentoDao();
        List<Departamento> departamentos = dep.listar();
        for (Departamento departamento : departamentos) {
            System.out.println(departamento);
        }
    }

    private static boolean verificarIdDepartamentoExiste(int id) {
        DepartamentoDao dep = new DepartamentoDao();
        List<Departamento> departamentos = dep.listar();

        return departamentos.stream().anyMatch(departamento -> departamento.getId() == id);
    }

    private static int lerIdDepartamentoValidado(Scanner sc) {
        int id = -1;
        boolean valido = false;

        while (!valido) {
            try {
                id = Integer.parseInt(lerString(sc, "Digite o id do departamento: "));

                if (verificarIdDepartamentoExiste(id)) {
                    valido = true;
                } else {
                    System.out.println("ID inválido! Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número.");
            }
        }

        return id;
    }

    private static List<Dependente> lerDependentes(Scanner sc, List<Dependente> dep, String cpf_funcionario) throws CpfInvalidoException {
        String opcaoDeSaida = "";

        do {
            String cpf = lerString(sc, "Digite o cpf do dependente: ");
            while (!isValido(cpf)) {
                System.out.println("Cpf inválido. Tente novamente. ");
                cpf = lerString(sc, "Digite o cpf do dependente novamente: ");
            }

            String nome = lerString(sc, "Digite o nome: ");
            LocalDate nascimento = LocalDate.parse(lerString(sc, "Digite a data nascimento(dd-MM-yyyy): "), FORMATTER_BR);
            Parentesco parentesco = Parentesco.valueOf(lerString(sc, "Digite seu parentesco: "));

            Dependente dependente = new Dependente(cpf, nome, nascimento, parentesco, cpf_funcionario);
            dep.add(dependente);

            System.out.println("Deseja sair do sistema(S/N):");
            opcaoDeSaida = sc.nextLine();
        } while (opcaoDeSaida.equalsIgnoreCase("S"));

        return dep;
    }

    private static double arredondar(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }

    private static List<FolhaDePagamento> calcularImpostos(List<Funcionario> func, List<Dependente> dep) {
        List<FolhaDePagamento> fp = new ArrayList<>();

        for (Funcionario f : func) {
            String cpf_funcionario = f.getCpf();
            double salario_bruto = f.getSalarioBruto();
            double inss = arredondar(calcularInss(salario_bruto));
            int quantidadeDependentes = (int) dep.stream()
                    .filter(d -> d.getCpfFuncionario().equals(cpf_funcionario))
                    .count();
            LocalDate data = LocalDate.now();
            double ir = arredondar(calcularIRRF(salario_bruto, inss, quantidadeDependentes));
            FolhaDePagamento folhaDePagamento = new FolhaDePagamento(cpf_funcionario, data, inss, ir);
            folhaDePagamento.setLiquido(arredondar(salarioLiquido(salario_bruto, inss, ir)));
            fp.add(folhaDePagamento);
        }

        return fp;
    }

    private static void salvarNoBancoFolhaDePagamento(List<FolhaDePagamento> fp) {
        System.out.println(fp.toString());
        System.out.println("salvo com sucesso no banco.");
    }
}