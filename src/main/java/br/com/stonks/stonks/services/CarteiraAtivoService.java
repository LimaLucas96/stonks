package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Ativo;
import br.com.stonks.stonks.models.Carteira;
import org.springframework.stereotype.Service;

@Service
public interface CarteiraAtivoService {
    Ativo[] listarAtivos(Carteira carteira);
}
