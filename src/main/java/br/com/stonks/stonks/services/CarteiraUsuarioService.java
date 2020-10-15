package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.CarteiraUsuario;
import org.springframework.stereotype.Service;

@Service
public interface CarteiraUsuarioService {

	  public void salvarCarteira(CarteiraUsuario carteira);

	  public boolean isAlreadyPresent(CarteiraUsuario carteira);

}
