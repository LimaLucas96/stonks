<<<<<<< HEAD

package br.com.stonks.stonks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.stonks.stonks.models.CarteiraAtivo;

import java.util.List;
package br.com.stonks.stonks.repository;

import br.com.stonks.stonks.models.Ativo;
import br.com.stonks.stonks.models.Carteira;
import br.com.stonks.stonks.models.CarteiraAtivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraAtivoRepository extends JpaRepository<CarteiraAtivo, Integer> {

    @Query("SELECT ca.*, a.codigo FROM carteira_ativo ca JOIN ativo a ON ca.ativo_id = a.id WHERE ca.id = :id")
    public List<CarteiraAtivo> findByCarteira(@Param("id") int id);

    CarteiraAtivo[] findAllByCarteira(Carteira carteira);
}

