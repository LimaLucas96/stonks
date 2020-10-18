
package br.com.stonks.stonks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.stonks.stonks.models.CarteiraAtivo;

@Repository
public interface CarteiraAtivoRepository extends JpaRepository<CarteiraAtivo, Integer> {

    public CarteiraAtivo findByid(int id);
}

