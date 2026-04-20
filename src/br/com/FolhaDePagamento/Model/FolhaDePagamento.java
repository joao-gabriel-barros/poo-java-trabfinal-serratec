package br.com.FolhaDePagamento.Model;

import java.time.LocalDate;

public class FolhaDePagamento {
    private int id;
    private String cpf_funcionario;
    private LocalDate data;
    private double inss;
    private double ir;
    private double liquido;

    public FolhaDePagamento(String cpf_funcionario, LocalDate data, double inss, double ir) {
        this.cpf_funcionario = cpf_funcionario;
        this.data = data;
        this.inss = inss;
        this.ir = ir;
    }

    public FolhaDePagamento(int id, String cpf_funcionario, LocalDate data, double inss, double ir, double liquido) {
        this.id = id;
        this.cpf_funcionario = cpf_funcionario;
        this.data = data;
        this.inss = inss;
        this.ir = ir;
        setLiquido(liquido);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf_funcionario() {
        return cpf_funcionario;
    }

    public void setCpf_funcionario(String cpf_funcionario) {
        this.cpf_funcionario = cpf_funcionario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getInss() {
        return inss;
    }

    public void setInss(double inss) {
        this.inss = inss;
    }

    public double getIr() {
        return ir;
    }

    public void setIr(double ir) {
        this.ir = ir;
    }

    public double getLiquido() {
        return liquido;
    }

    public void setLiquido(double liquido) {
        if (liquido < 0) {
            throw new IllegalArgumentException("Salario liquido no pode ser menor do que zero");
        }
        this.liquido = liquido;
    }

    @Override
    public String toString() {
        return "FolhaDePagamento { " +
                "id=" + id +
                ", cpf_funcionario='" + cpf_funcionario + '\'' +
                ", data=" + data +
                ", inss=" + inss +
                ", ir=" + ir +
                ", liquido=" + liquido +
                '}';
    }
}
