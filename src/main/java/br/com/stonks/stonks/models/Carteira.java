package br.com.stonks.stonks.models;

import br.ufrn.imd.stonks.framework.framework.model.Despesa;
import br.ufrn.imd.stonks.framework.framework.model.DespesaAtivo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
public class Carteira extends Despesa {

    public Carteira(@NotNull(message = "Usuário é obrigatorio.") Usuario usuario) {
        super(usuario);
    }

    public Carteira() {
    }
    public Carteira(Despesa despesa) {
        super(despesa.getUsuario());
    }
}
