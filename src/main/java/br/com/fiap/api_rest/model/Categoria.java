package br.com.fiap.api_rest.model;

public enum Categoria {
    ELETRONICOS("Eletrônicos"),
    LIVROS("Livros"),
    OUTROS("Outros");

    private String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
