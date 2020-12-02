package br.com.stonks.stonks.models;

import javax.persistence.*;
import java.util.List;

import br.com.stonks.stonks.services.EmpresaService;
import br.ufrn.imd.stonks.framework.framework.model.AtivoAbstract;
import br.ufrn.imd.stonks.framework.framework.model.EmpresaAbstract;

@Entity
public class Empresa extends EmpresaAbstract {
    public Empresa(String nome, Long cnpj, List<AtivoAbstract> ativoAbstracts) {
        super(nome, cnpj, ativoAbstracts);
    }

    public Empresa() { }

}
