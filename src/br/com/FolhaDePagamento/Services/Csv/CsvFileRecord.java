package br.com.FolhaDePagamento.Services.Csv;

import br.com.FolhaDePagamento.Model.FolhaDePagamento;
import br.com.FolhaDePagamento.Model.Funcionario;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class CsvFileRecord {
    public static void gravarArquivoCsv(String caminhoSaida, List<FolhaDePagamento> folhas) {
        DecimalFormat df = new DecimalFormat("0.00");

        try (FileWriter writer = new FileWriter(caminhoSaida)) {
            // Escrever cabeçalho
            writer.write("Nome;CPF;Desconto_INSS;Desconto_IR;SalarioLiquido\n");

            // Escrever cada linha de folha de pagamento
            for (FolhaDePagamento folha : folhas) {
                String nome = folha.getFuncionario().getNome();
                String cpf = folha.getFuncionario().getCpf();
                String inss = df.format(folha.getInss());
                String ir = df.format(folha.getIr());
                String liquido = df.format(folha.getLiquido());

                String linha = String.format("%s;%s;%s;%s;%s\n",
                    nome, cpf, inss, ir, liquido);

                writer.write(linha);
            }

            System.out.println("Arquivo gravado com sucesso em: " + caminhoSaida);

        } catch (IOException e) {
            System.out.println("Erro ao gravar arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
