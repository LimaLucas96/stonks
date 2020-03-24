package br.com.stonks.stonks.repository;
import br.com.stonks.stonks.models.FundoImobiliario;
import javax.transaction.Transactional;

@Transactional
public interface FundoImobiliarioRepository extends AtivoBaseRepository<FundoImobiliario> {}
