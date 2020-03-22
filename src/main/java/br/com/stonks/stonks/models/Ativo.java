package br.com.stonks.stonks.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance
public abstract class Ativo {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Nome é obrigatório.")
    private String nome;

    @NotNull(message = "Código é obrigatório.")
    private String codigo;

    @NotNull(message = "Valor é obrigatório.")
    private double valor;

    // TODO: colocar a Empresa (relativo a outra issue)

    public Ativo(@NotNull(message = "Nome é obrigatorio.") String nome,
                 @NotNull(message = "Código é obrigatório.") String codigo,
                 @NotNull(message = "Valor é obrigatório.") double valor) {
        this.nome = nome;
        this.codigo = codigo;
        this.valor = valor;
    }

    public Ativo() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
