package br.com.FolhaDePagamento.Model;

import br.com.FolhaDePagamento.Enum.Parentesco;

import java.time.LocalDate;

public final class Dependente extends Pessoa {
    private Parentesco parentesco;
    private String cpfFuncionario;

    public Dependente(String cpf, String nome, LocalDate nascimento, Parentesco parentesco, String cpfFuncionario) {
        super(cpf, nome, nascimento);
        this.parentesco = parentesco;
        this.cpfFuncionario = cpfFuncionario;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public String getCpfFuncionario() {
        return cpfFuncionario;
    }

    @Override
    public String toString() {
        return "Dependente{" +
                "parentesco=" + parentesco +
                ", cpf funcionario=" + cpfFuncionario +
                '}';
    }
}