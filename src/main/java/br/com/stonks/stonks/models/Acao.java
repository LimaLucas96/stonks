package br.com.stonks.stonks.models;

public class Acao extends Ativo {

    private int id;

    private String setor;

    public Acao() { }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
}