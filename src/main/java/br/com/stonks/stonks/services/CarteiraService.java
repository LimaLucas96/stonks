package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Carteira;
import br.com.stonks.stonks.models.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface CarteiraService {

	  public void salvarCarteira(Carteira carteira);

	  public boolean isAlreadyPresent(Carteira carteira);

	Carteira carteiraByUsuario(Usuario usuario);
}
