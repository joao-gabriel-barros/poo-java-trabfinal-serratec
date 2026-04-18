package br.com.FolhaDePagamento.Model;

public class Departamento {
    private int id;
    private String nome;


    public Departamento() {}
    public Departamento(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return " { " +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}