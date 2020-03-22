package br.com.stonks.stonks.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Acao extends Ativo {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Setor é um campo obrigatório.")
    private String setor;

    public Acao(@NotNull(message = "Setor é um campo obrigatório") String setor){
        this.setor = setor;
    }

    public Acao() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
}