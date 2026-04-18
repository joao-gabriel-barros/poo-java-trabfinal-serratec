package br.com.FolhaDePagamento.Exceptions;

public class CpfInvalidoException extends Exception {
    public CpfInvalidoException(String mensagem) {
        super(mensagem);
    }

    public CpfInvalidoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

