package br.com.FolhaDePagamento.Model;

import br.com.FolhaDePagamento.Exceptions.CpfInvalidoException;

import java.time.LocalDate;

public final class Funcionario extends Pessoa {
    private double salarioBruto;
    private int idDepartamento;

    public Funcionario(String cpf, String nome, LocalDate nascimento, double salarioBruto, int idDepartamento) throws CpfInvalidoException {
        super(cpf, nome, nascimento);
        this.salarioBruto = salarioBruto;
        this.idDepartamento = idDepartamento;
    }

    public double getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(double salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    @Override
    public String toString() {
        return "Nome = " + nome +
                "| CPF = " + cpf +
                ", data de nascimento = " + nascimento +
                ", ID Departamento = " + idDepartamento +
                ", Salário Bruto = " + salarioBruto +
                "\n";
    }
}