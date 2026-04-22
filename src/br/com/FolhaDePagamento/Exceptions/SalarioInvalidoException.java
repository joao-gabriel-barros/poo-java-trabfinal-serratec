package br.com.FolhaDePagamento.Exceptions;

public class SalarioInvalidoException extends RuntimeException {
    public SalarioInvalidoException(String message) {
        super(message);
    }
}
