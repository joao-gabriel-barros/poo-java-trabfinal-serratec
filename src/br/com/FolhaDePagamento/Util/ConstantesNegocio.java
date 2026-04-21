package br.com.FolhaDePagamento.Util;

import java.time.format.DateTimeFormatter;

public class ConstantesNegocio {
    public static final double SALARIO_MINIMO = 1412.00;
    public static final double VALOR_DEPENDENTE_IR = 189.59;
    public static final double TETO_INSS = 951.62;
    public static final DateTimeFormatter FORMATTER_BR = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private ConstantesNegocio() {}
}
