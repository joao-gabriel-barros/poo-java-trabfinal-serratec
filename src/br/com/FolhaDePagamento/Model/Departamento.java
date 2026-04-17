package br.com.FolhaDePagamento.Model;

public class Departamento {
    private Integer id;
    private String nome;

    public Departamento(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}