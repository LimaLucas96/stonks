package br.com.stonks.stonks.repository;

import br.com.stonks.stonks.models.Carteira;
import br.com.stonks.stonks.models.CarteiraAtivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarteiraAtivoRepository extends JpaRepository<CarteiraAtivo, Integer> {

    @Query("SELECT ca, a.codigo FROM CarteiraAtivo ca JOIN Ativo a ON ca.ativo.id = a.id WHERE ca.carteira.id = :id")
    public List<CarteiraAtivo> findByCarteira(@Param("id") int id);

    CarteiraAtivo[] findAllByCarteira(Carteira carteira);
}

