package br.com.stonks.stonks.repository;

import br.com.stonks.stonks.models.Carteira;
import br.com.stonks.stonks.models.CarteiraAtivo;
import br.ufrn.imd.stonks.framework.framework.model.DespesaAtivo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarteiraAtivoRepository extends JpaRepository<CarteiraAtivo, Integer> {

    @Query("SELECT ca, a.codigo FROM CarteiraAtivo ca JOIN Ativo a ON ca.ativoAbstract.id = a.id WHERE ca.despesa.id = :id AND ca.operacao = 'COMPRA'")
    public List<CarteiraAtivo> findByAtivosCarteiraCompra(@Param("id") int id);

    @Query("SELECT ca, a.codigo FROM CarteiraAtivo ca JOIN Ativo a ON ca.ativoAbstract.id = a.id WHERE ca.despesa.id = :id")
    public List<CarteiraAtivo> findByAtivosCarteira(@Param("id") int id, Sort sort);

    public CarteiraAtivo[] findAllByDespesa(Carteira carteira);

    @Query(value = "SELECT SUM(ca.quantidade * ca.valor) FROM CarteiraAtivo ca WHERE ca.despesa.id = :idCarteira")
    public Double totalCarteira(Integer idCarteira);
}
