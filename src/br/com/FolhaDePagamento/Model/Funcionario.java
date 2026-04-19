package br.com.FolhaDePagamento.Model;

import br.com.FolhaDePagamento.Exceptions.CpfInvalidoException;

import java.time.LocalDate;

public final class Funcionario extends Pessoa {
    private double salarioBruto;
    private Departamento departamento;

    public Funcionario(String cpf, String nome, LocalDate nascimento, double salarioBruto, Departamento departamento) throws CpfInvalidoException {
        super(cpf, nome, nascimento);
        this.salarioBruto = salarioBruto;
        this.departamento = departamento;
    }

    public double getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(double salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "Nome = '" + nome + '\'' +
                ", CPF = '" + cpf + '\'' +
                ", Nascimento = " + nascimento +
                ", Departamento = {" + departamento +
                "}, Salário Bruto = " + salarioBruto +
                '}' + "\n";
    }
}