package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Role;
import br.com.stonks.stonks.dao.RoleDAO;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleDAO roleRepository = new RoleDAO();

    public Role findByRole(String role) {
        return roleRepository.findByRole(role);
    }

    public void save(Role role) {
        roleRepository.save(role);
    }
}
