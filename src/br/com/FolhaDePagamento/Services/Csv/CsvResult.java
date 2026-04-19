package br.com.FolhaDePagamento.Services.Csv;

import br.com.FolhaDePagamento.Model.Dependente;
import br.com.FolhaDePagamento.Model.Funcionario;

import java.util.List;

public class CsvResult {
    private List<Funcionario> funcionarios;
    private List<Dependente> dependentes;

    public CsvResult(List<Funcionario> funcionarios, List<Dependente> dependentes) {
        this.funcionarios = funcionarios;
        this.dependentes = dependentes;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public List<Dependente> getDependentes() {
        return dependentes;
    }
}