package br.com.stonks.stonks.services;

import br.com.stonks.stonks.exception.CpfInvalidoException;
import br.com.stonks.stonks.exception.UsuarioExistenteException;
import br.com.stonks.stonks.models.Usuario;

public interface UsuarioService {

    public void salvarUsuario(Usuario usuario) throws UsuarioExistenteException, CpfInvalidoException;

    Boolean isUserAlreadyPresent(Usuario usuario);

    Usuario usuarioPorEmail(String email);
}
