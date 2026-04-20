package br.com.FolhaDePagamento.Interfaces;

public interface CalculoIrpf {
    double calcularBase(double salarioBruto, double valorINSS, int dependentes);

    double calcularIRRF(double salarioBruto, double valorINSS, int dependentes);
}
