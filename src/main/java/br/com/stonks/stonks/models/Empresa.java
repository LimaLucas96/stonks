package br.com.stonks.stonks.models;

import br.ufrn.imd.stonks.framework.framework.model.AtivoFramework;
import br.ufrn.imd.stonks.framework.framework.model.EmpresaFramework;

import javax.persistence.*;
import java.util.List;

@Entity
public class Empresa extends EmpresaFramework {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private Long cnpj;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "empresa")
    private List<Ativo> ativo;

    public Empresa() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCnpj() {
        return cnpj;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }

    public List<Ativo> getAtivo() {
        return ativo;
    }

    public void setAtivo(List<Ativo> ativo) {
        this.ativo = ativo;
    }
}
