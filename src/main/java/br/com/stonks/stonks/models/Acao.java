package br.com.stonks.stonks.models;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value = "acao")
public class Acao extends Ativo {

    private String setor;

    public Acao() { }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
}