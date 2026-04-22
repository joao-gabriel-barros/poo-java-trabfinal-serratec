package br.com.FolhaDePagamento.Services.Validators;

import br.com.FolhaDePagamento.Exceptions.SalarioInvalidoException;

import static br.com.FolhaDePagamento.Util.ConstantesNegocio.SALARIO_MINIMO;

public class SalarioValidator {
    public static boolean validaSalario(double salario) throws SalarioInvalidoException {
        if (salario < SALARIO_MINIMO) {
            throw new SalarioInvalidoException("salário bruto não pode ser menor que salário mínimo");
        }
        return true;
    }
}
