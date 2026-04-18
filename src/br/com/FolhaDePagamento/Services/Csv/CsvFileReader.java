package br.com.FolhaDePagamento.Services.Csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.FolhaDePagamento.Enum.Parentesco;
import br.com.FolhaDePagamento.Exceptions.CpfInvalidoException;
import br.com.FolhaDePagamento.Model.Dependente;
import br.com.FolhaDePagamento.Model.Funcionario;
import br.com.FolhaDePagamento.Model.Departamento;

public class CsvFileReader {
    public static List<Funcionario> lerArquivoCsv(String arquivo) {
        List<Funcionario> funcionarios = new ArrayList<>();
        List<Dependente> dependentes = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // TODO
        //  Lê o CSV de entrada e cria os objetos.
        //  Realiza os cálculos de INSS e IR.
        //  Salva tudo no banco.
        //  Gera o CSV de saída no local especificado pelo usuário.

        try {
            Scanner arquivoScanner = new Scanner(new File(arquivo));
            arquivoScanner.useDelimiter(";");

            while (arquivoScanner.hasNextLine()) {
                String linha = arquivoScanner.nextLine();
                String[] campos = linha.split(";");
                Departamento departamento = null;
                String cpfFuncionario = "";

                if (campos.length >= 5) {
                    String nome = campos[0].trim();
                    cpfFuncionario = campos[1].trim().replaceAll("\\s+", "").replaceAll("[^0-9]", "");
                    LocalDate dataNascimento = LocalDate.parse(campos[2].trim(), formatter);
                    double salario = Double.parseDouble(campos[3].trim());
                    departamento = new Departamento(campos[4].trim());
                    Funcionario funcionario = new Funcionario(cpfFuncionario, nome, dataNascimento, salario, departamento);
                    funcionarios.add(funcionario);
                } else if (campos.length >= 4) {
                    String nome = campos[0].trim();
                    String cpf = campos[1].trim().replaceAll("\\s+", "").replaceAll("[^0-9]", "");
                    LocalDate dataNascimento = LocalDate.parse(campos[2].trim(), formatter);
                    Parentesco parentesco = Parentesco.valueOf(campos[3].trim().toUpperCase());
                    Dependente dependente = new Dependente(cpf, nome, dataNascimento, parentesco, cpfFuncionario);
                    dependentes.add(dependente);
                }
            }

            arquivoScanner.close();
            System.out.println(funcionarios);
            System.out.println(dependentes);

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado !");
            System.out.println(e.getMessage());
        } catch (CpfInvalidoException e) {
            throw new RuntimeException(e);
        }

        return funcionarios;
    }
}
