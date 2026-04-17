package br.com.FolhaDePagamento.Model;

import br.com.FolhaDePagamento.Enum.Parentesco;

import java.time.LocalDate;

public final class Dependente extends Pessoa {
    private Parentesco parentesco;
    private Funcionario funcionario;

    public Dependente(String cpf, String nome, LocalDate nascimento, Parentesco parentesco, Funcionario funcionario) {
        super(cpf, nome, nascimento);
        this.parentesco = parentesco;
        this.funcionario = funcionario;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    @Override
    public String toString() {
        return "Dependente{" +
                "parentesco=" + parentesco +
                ", funcionario=" + funcionario +
                '}';
    }
}