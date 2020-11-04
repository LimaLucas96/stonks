package br.com.stonks.stonks.services;

import br.com.stonks.stonks.exception.CpfInvalidoException;
import br.com.stonks.stonks.exception.UsuarioExistenteException;
import br.com.stonks.stonks.models.CarteiraAtivo;
import br.com.stonks.stonks.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    public void salvarUsuario(Usuario usuario) throws UsuarioExistenteException, CpfInvalidoException;

    public Boolean isUserAlreadyPresent(Usuario usuario);

    public Usuario usuarioPorEmail(String email);

    public Optional<Usuario> findById(int id);

    public void deleteById(int id);

    public Usuario usuarioLogado();
}
