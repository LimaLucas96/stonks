package br.com.stonks.stonks.repository;

import br.com.stonks.stonks.models.Carteira;
import br.com.stonks.stonks.models.CarteiraAtivo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Repository
public interface CarteiraAtivoRepository {

    @Query("SELECT ca, a.codigo FROM CarteiraAtivo ca JOIN Ativo a ON ca.ativo.id = a.id WHERE ca.carteira.id = :id AND ca.operacao = 'COMPRA'")
    public List<CarteiraAtivo> findByAtivosCarteiraCompra( int id) {
        StringBuilder queryBuilder = new StringBuilder();

        queryBuilder
                .append("SELECT ca, a.codigo FROM carteira_ativo ca JOIN ativo a ON ca.id_ativo = a.id " +
                        "WHERE ca.id_carteira = :id AND ca.operacao = 'COMPRA'");

    }

    @Query("SELECT ca, a.codigo FROM CarteiraAtivo ca JOIN Ativo a ON ca.ativo.id = a.id WHERE ca.carteira.id = :id")
    public List<CarteiraAtivo> findByAtivosCarteira(@Param("id") int id, Sort sort);

    public CarteiraAtivo[] findAllByCarteira(Carteira carteira);

    @Query(value = "SELECT SUM(ca.quantidade * ca.valor) FROM CarteiraAtivo ca WHERE ca.carteira.id = :idCarteira")
    public Double totalCarteira(Integer idCarteira);
}

