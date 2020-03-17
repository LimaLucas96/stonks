package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Role;
import br.com.stonks.stonks.models.Usuario;
import br.com.stonks.stonks.repository.RoleRepository;
import br.com.stonks.stonks.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void salvarUsuario(Usuario usuario) {
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        usuario.setStatus("VERIFIED");
        Role usuarioRole = roleRepository.findByRole("SITE_USER");
        usuario.setRoles(new HashSet<Role>(Arrays.asList(usuarioRole)));
        usuarioRepository.save(usuario);
    }

    @Override
    public boolean isUserAlreadyPresent(Usuario usuario) {
        return false;
    }
}
