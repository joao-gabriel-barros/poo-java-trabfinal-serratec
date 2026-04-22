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
import br.com.FolhaDePagamento.Services.Validators.CpfValidator;

public class CsvFileReader {
    public static CsvResult lerArquivoCsv(String arquivo) {
        List<Funcionario> funcionarios = new ArrayList<>();
        List<Dependente> dependentes = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            Scanner arquivoScanner = new Scanner(new File(arquivo));

            int numeroLinha = 0;
            String cpfFuncionario = "";

            while (arquivoScanner.hasNextLine()) {
                numeroLinha++;
                String linha = arquivoScanner.nextLine().trim();

                if (linha.isEmpty() || linha.startsWith("#")) {
                    continue;
                }

                String[] campos = linha.split(";");

                try {
                    if (campos.length >= 5) {
                        String nome = campos[0].trim();
                        cpfFuncionario = campos[1].trim();

                        if (!CpfValidator.isValido(cpfFuncionario)) {
                            System.err.println("Linha " + numeroLinha + " ignorada (CPF inválido): " + cpfFuncionario);
                            continue;
                        }

                        LocalDate dataNascimento = LocalDate.parse(campos[2].trim(), formatter);
                        double salario = Double.parseDouble(campos[3].trim());
                        int IdDepartamento = Integer.parseInt(campos[4].trim());
                        Funcionario funcionario = new Funcionario(cpfFuncionario, nome, dataNascimento, salario, IdDepartamento);
                        funcionarios.add(funcionario);
                    } else if (campos.length == 4) {
                        String nome = campos[0].trim();
                        String cpf = campos[1].trim();
                        if (!CpfValidator.isValido(cpf)) {
                            System.err.println("Linha " + numeroLinha + " ignorada (CPF inválido): " + cpf);
                            continue;
                        }
                        LocalDate dataNascimento = LocalDate.parse(campos[2].trim(), formatter);
                        Parentesco parentesco = Parentesco.valueOf(campos[3].trim().toUpperCase());
                        Dependente dependente = new Dependente(cpf, nome, dataNascimento, parentesco, cpfFuncionario);
                        dependentes.add(dependente);
                    } else {
                        System.err.println("Linha " + numeroLinha + " ignorada (formato inválido): " + linha);
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao processar linha " + numeroLinha + ": " + linha);
                    System.err.println("Motivo: " + e.getMessage());
                }
            }

            arquivoScanner.close();
            System.out.println("CSV lido com sucesso: " + funcionarios.size() + " funcionários, " + dependentes.size() + " dependentes\n");

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        }

        return new CsvResult(funcionarios, dependentes);
    }
}
