package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.CarteiraAtivo;
import org.springframework.stereotype.Service;

@Service
public interface CarteiraAtivoService {

	  public void salvar(CarteiraAtivo carteiraAtivo);

	  public boolean isAlreadyPresent(CarteiraAtivo carteiraAtivo);

}
