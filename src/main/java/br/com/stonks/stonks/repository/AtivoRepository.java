package br.com.stonks.stonks.repository;
import br.com.stonks.stonks.models.Ativo;
import javax.transaction.Transactional;

@Transactional
public interface AtivoRepository extends AtivoBaseRepository<Ativo>{}
