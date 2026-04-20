package br.com.FolhaDePagamento.Services.Irpf;

import br.com.FolhaDePagamento.Interfaces.CalculoIrpf;

public class CalculoIrpfService implements CalculoIrpf {
    @Override
    public double calcularBase(double salarioBruto, double valorINSS, int dependentes) {
        return salarioBruto - valorINSS - (dependentes * 189.59);
    }

    @Override
    public double calcularIRRF(double salarioBruto, double valorINSS, int dependentes) {
        double base = calcularBase(salarioBruto, valorINSS, dependentes);
        double irpf;

        if(base <= 2259.20) {
            irpf = 0;
        } else if(base <= 2826.65) {
            irpf = (base * 0.075) - 169.44;
        } else if(base <= 3751.05) {
            irpf = (base * 0.15) - 381.44;
        } else if (base <= 4664.68) {
            irpf = (base * 0.225) - 662.77;
        } else {
            irpf = (base * 0.275) - 896.00;
        }

        return irpf;
    }
}
