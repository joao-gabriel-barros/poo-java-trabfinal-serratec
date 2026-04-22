package br.com.FolhaDePagamento.Exceptions;

public class DependenteSemTitularException extends RuntimeException {
    public DependenteSemTitularException(String message) {
        super(message);
    }
}
