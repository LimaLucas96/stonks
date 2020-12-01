package br.com.stonks.stonks.models;

import br.ufrn.imd.stonks.framework.framework.model.AtivoAbstract;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ativo")
public class Ativo extends AtivoAbstract {

    public Ativo(String codigo, Empresa empresa) {
        super(codigo, empresa);
    }

    public Ativo() { }

}
