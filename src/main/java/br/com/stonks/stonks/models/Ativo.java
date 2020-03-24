package br.com.stonks.stonks.models;

import javax.persistence.*;

@Entity
@Inheritance
public abstract class Ativo {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String codigo;

    @Column(nullable = false)
    private double valor;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    public Ativo() { }

    public long getId() {
        return id;
    }

    public void setId(int id) {
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
