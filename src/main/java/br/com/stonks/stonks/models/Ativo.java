package br.com.stonks.stonks.models;

import br.ufrn.imd.stonks.framework.framework.model.AtivoFramework;
import br.ufrn.imd.stonks.framework.framework.model.EmpresaFramework;

import javax.persistence.*;

@Entity
@Table(name="ativo")
public class Ativo extends AtivoFramework {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private EmpresaFramework empresa;

    public Ativo() { }

    public EmpresaFramework getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaFramework empresa) {
        this.empresa = empresa;
    }
}
