package br.com.stonks.stonks.models;

public class FundoImobiliario extends Ativo {

    private int id;

    private String tipo;

    public FundoImobiliario() { }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}