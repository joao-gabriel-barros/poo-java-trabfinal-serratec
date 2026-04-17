package br.com.FolhaDePagamento.Enum;

public enum Parentesco {
    FILHO("filho"),
    SOBRINHO("sobrinho"),
    OUTROS("outros");

    private String descricao;
    private Parentesco(String descricao) {
        this.descricao = descricao;
    }

    // getters e setters
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
