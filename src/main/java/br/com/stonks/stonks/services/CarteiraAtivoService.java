package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.CarteiraAtivo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarteiraAtivoService {

    public void salvar(CarteiraAtivo carteiraAtivo);

    public boolean isAlreadyPresent(CarteiraAtivo carteiraAtivo);

    public List<CarteiraAtivo> findByCarteira(int id);
}
