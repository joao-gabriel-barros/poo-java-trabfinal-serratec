package br.com.FolhaDePagamento.Services.Inss;

import br.com.FolhaDePagamento.Interfaces.CalculoINSS;

import static br.com.FolhaDePagamento.Util.ConstantesNegocio.TETO_INSS;

public class CalculoInssService implements CalculoINSS {
    public static double calcularInss(double salarioBruto) {
        double aliquota;
        double parcelaDeduzir;


        if (salarioBruto <= 0) {
            return 0;
        }

        if (salarioBruto > 8157.41) {
            return TETO_INSS;
        }

        if (salarioBruto <= 1518.00) {
            aliquota = 0.075;
            parcelaDeduzir = 0.00;
        } else if (salarioBruto <= 2793.88) {
            aliquota = 0.09;
            parcelaDeduzir = 22.77;
        } else if (salarioBruto <= 4190.83) {
            aliquota = 0.12;
            parcelaDeduzir = 106.60;
        } else {
            aliquota = 0.14;
            parcelaDeduzir = 190.42;
        }

        return (salarioBruto * aliquota) - parcelaDeduzir;

    }
}
