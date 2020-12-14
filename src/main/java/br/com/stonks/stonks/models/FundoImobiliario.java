package br.com.stonks.stonks.models;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "fundo")
public class FundoImobiliario extends Ativo {
    private String tipo;

    public FundoImobiliario() { }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}