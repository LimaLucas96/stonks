package br.com.stonks.stonks.repository;

import br.com.stonks.stonks.models.Ativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AtivoBaseRepository<T extends Ativo> extends JpaRepository<T, Integer> {

    public Ativo findByCodigo(String codigo);

}