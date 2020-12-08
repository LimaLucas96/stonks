package br.com.stonks.stonks.models;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class Carteira {

    private int id;

    @NotNull(message = "É preciso definir um usuario.")
    private Usuario usuario;

    private Boolean status;

    private Set<CarteiraAtivo> carteiraAtivos;

    public Set<CarteiraAtivo> getCarteiraAtivos() {
        return carteiraAtivos;
    }

    public void setCarteiraAtivos(Set<CarteiraAtivo> carteiraAtivos) {
        this.carteiraAtivos = carteiraAtivos;
    }

    public Carteira(@NotNull(message = "Usuário é obrigatorio.") Usuario usuario) {
        this.status = true;
        this.usuario = usuario;
    }

    public Carteira() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
