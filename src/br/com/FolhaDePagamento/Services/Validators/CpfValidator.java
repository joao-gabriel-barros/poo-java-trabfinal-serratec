package br.com.FolhaDePagamento.Services.Validators;

import br.com.FolhaDePagamento.Exceptions.CpfInvalidoException;

public class CpfValidator {
    public static void validar(String cpf) throws CpfInvalidoException {
        if (cpf == null || cpf.isBlank()) {
            throw new CpfInvalidoException("CPF nao pode estar vazio");
        }

        // remove caracteres especiais
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");

        // valida tamanho
        if (cpfLimpo.length() != 11) {
            throw new CpfInvalidoException("CPF deve conter 11 dígitos");
        }

        // verifica se todos os dígitos sao iguais (inválido)
        if (cpfLimpo.matches("(\\d)\\1{10}")) {
            throw new CpfInvalidoException("CPF com todos os dígitos iguais é inválido");
        }

        // calcula primeiro dígito verificador
        int primeiroVerificador = calcularDigitoVerificador(cpfLimpo, 10);
        if (Integer.parseInt(cpfLimpo.charAt(9) + "") != primeiroVerificador) {
            throw new CpfInvalidoException("Primeiro dígito verificador do CPF é inválido");
        }

        // calcula segundo dígito verificador
        int segundoVerificador = calcularDigitoVerificador(cpfLimpo, 11);
        if (Integer.parseInt(cpfLimpo.charAt(10) + "") != segundoVerificador) {
            throw new CpfInvalidoException("Segundo dígito verificador do CPF é inválido");
        }
    }

    private static int calcularDigitoVerificador(String cpf, int posicao) {
        int soma = 0;
        int multiplicador = 2;

        for (int i = posicao - 2; i >= 0; i--) {
            soma += Integer.parseInt(cpf.charAt(i) + "") * multiplicador;
            multiplicador++;
        }

        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }

    /**
     * Verifica se o CPF é válido sem lançar exceção.
     * Retorna true se válido, false se inválido.
     *
     * @param cpf string com CPF
     * @return true se válido, false se inválido
     */
    public static boolean isValido(String cpf) {
        try {
            validar(cpf);
            return true;
        } catch (CpfInvalidoException e) {
            return false;
        }
    }
}

