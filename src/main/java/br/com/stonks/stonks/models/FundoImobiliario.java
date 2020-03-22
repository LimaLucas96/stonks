package br.com.stonks.stonks.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class FundoImobiliario extends Ativo {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Tipo é obrigatório.")
    private String tipo;

    public FundoImobiliario(@NotNull(message = "Tipo é obrigatório") String tipo) {
        this.tipo = tipo;
    }

    public FundoImobiliario() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}