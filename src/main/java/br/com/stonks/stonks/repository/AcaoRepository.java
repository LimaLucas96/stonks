package br.com.stonks.stonks.repository;
import br.com.stonks.stonks.models.Acao;
import javax.transaction.Transactional;

@Transactional
public interface AcaoRepository extends AtivoBaseRepository<Acao> {}
