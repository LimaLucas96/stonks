package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Usuario;

public interface UsuarioService {

    public void salvarUsuario(Usuario usuario);

    public boolean isUserAlreadyPresent(Usuario usuario);
}
