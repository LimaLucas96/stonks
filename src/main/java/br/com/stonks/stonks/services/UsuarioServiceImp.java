package br.com.stonks.stonks.services;

import br.com.stonks.stonks.exception.CpfInvalidoException;
import br.com.stonks.stonks.exception.UsuarioExistenteException;
import br.com.stonks.stonks.models.Role;
import br.com.stonks.stonks.models.Usuario;
import br.com.stonks.stonks.dao.RoleDAO;
import br.com.stonks.stonks.dao.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    private final RoleDAO roleRepository = new RoleDAO();

    private final UsuarioDAO usuarioRepository = new UsuarioDAO();

    @Override
    public void salvarUsuario(Usuario usuario) throws UsuarioExistenteException, CpfInvalidoException {
        if (isUserAlreadyPresent(usuario)) {
            throw new UsuarioExistenteException("Usuário já existe");
        }
        if (!validarCpf(usuario.getCpf())){
            throw new CpfInvalidoException("CPF inválido");
        }
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        usuario.setStatus(true);
        Role usuarioRole = roleRepository.findByRole("SITE_USER");
        usuario.setRoles(new HashSet<Role>(Arrays.asList(usuarioRole)));
        usuarioRepository.save(usuario);
    }

    @Override
    public Boolean isUserAlreadyPresent(Usuario usuario) {
        return usuarioRepository.findByCpf(usuario.getCpf()).getId() != 0;
    }

    private Boolean validarCpf(String cpf) {
        cpf = cpf.replace(".","").replace("-","");
        if (cpf.length() != 11) { return false; }

        int soma = 0;
        double resto;
        int i;

        for (i = 1; i <= 9; i++) { soma += Math.floor(Integer.parseInt(String.valueOf(cpf.charAt(i - 1)))) * (11 - i); }

        resto = 11 - (soma - (Math.floor(soma / 11) * 11));

        if ((resto == 10) || (resto == 11)) { resto = 0; }

        if (resto != Math.floor(Integer.parseInt(String.valueOf(cpf.charAt(9))))) { return false; }

        soma = 0;

        for (i = 1; i <= 10; i++) { soma += Integer.parseInt(String.valueOf(cpf.charAt(i - 1))) * (12 - i); }

        resto = 11 - (soma - (Math.floor(soma / 11) * 11));

        if ((resto == 10) || (resto == 11)) { resto = 0; }

        if (resto != Math.floor(Integer.parseInt(String.valueOf(cpf.charAt(10))))) { return false; }

        return true;
    }

    @Override
    public Usuario usuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Usuario findById(int id){
        return usuarioRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        usuarioRepository.remove(id);
    }

    @Override
    public Usuario usuarioLogado(){
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usuarioRepository.findByEmail(principal.getUsername());
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
}
