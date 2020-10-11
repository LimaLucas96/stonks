package br.com.stonks.stonks.models;

import javax.persistence.*;

@Entity
public class FundoImobiliario extends Ativo {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
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