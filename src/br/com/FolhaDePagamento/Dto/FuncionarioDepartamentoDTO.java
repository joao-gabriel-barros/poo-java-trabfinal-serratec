package br.com.FolhaDePagamento.Dto;

public class FuncionarioDepartamentoDTO {
    private String nomeFuncionario;
    private String nomeDepartamento;

    public FuncionarioDepartamentoDTO(String nomeFuncionario, String nomeDepartamento) {
        this.nomeFuncionario = nomeFuncionario;
        this.nomeDepartamento = nomeDepartamento;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    @Override
    public String toString() {
        return "Funcionário: " + nomeFuncionario + " | Departamento: " + nomeDepartamento;
    }
}
