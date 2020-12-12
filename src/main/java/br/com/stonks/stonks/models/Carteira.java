package br.com.stonks.stonks.models;

import br.ufrn.imd.stonks.framework.framework.model.DespesaFramework;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Carteira extends DespesaFramework {

    public Carteira(@NotNull(message = "Usuário é obrigatorio.") Usuario usuario) {
        super(usuario);
    }

    public Carteira() {
    }
    public Carteira(DespesaFramework despesa) {
        super(despesa.getUsuario());
    }
}
