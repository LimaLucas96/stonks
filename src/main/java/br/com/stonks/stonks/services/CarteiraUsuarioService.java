package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.CarteiraUsuario;
import br.com.stonks.stonks.models.Usuario;
import br.com.stonks.stonks.repository.CarteiraUsuarioRepository;
import br.com.stonks.stonks.repository.RoleRepository;
import br.com.stonks.stonks.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

@Service
public interface CarteiraUsuarioService {

	  public void salvarCarteira(CarteiraUsuario carteira);

	  public boolean isAlreadyPresent(CarteiraUsuario carteira);
    
}
