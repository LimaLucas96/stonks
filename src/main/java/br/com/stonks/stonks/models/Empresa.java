package br.com.stonks.stonks.models;

import br.ufrn.imd.stonks.framework.framework.model.EmpresaAbstract;

import javax.persistence.*;
import java.util.List;

@Entity
public class Empresa extends EmpresaAbstract {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private Long cnpj;

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
}
