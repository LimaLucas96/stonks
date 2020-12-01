package br.com.stonks.stonks.models;

import javax.persistence.*;
import java.util.List;
import br.ufrn.imd.stonks.framework.framework.model.EmpresaAbstract;

@Entity
public class Empresa extends EmpresaAbstract {

    public Empresa(String nome, Long cnpj, List<Ativo> ativos) {
        super(nome, cnpj, ativos);
    }

    public Empresa() { }

}
