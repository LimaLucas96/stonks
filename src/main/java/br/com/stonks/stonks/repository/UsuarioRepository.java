package br.com.stonks.stonks.repository;

import br.com.stonks.stonks.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByEmail(String email);

    Usuario findByCpf(String cpf);
}
