package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Role;
import br.com.stonks.stonks.dao.RoleDAO;
import br.com.stonks.stonks.models.Usuario;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleDAO roleDAO = new RoleDAO();

    public Role findByRole(String role) {
        return roleDAO.findByRole(role);
    }

    public void save(Role role) {
        roleDAO.save(role);
    }

    public void saveRoleUsuario(Role role, Usuario usuario) {
        roleDAO.saveRoleUsuario(role, usuario);
    }
}
