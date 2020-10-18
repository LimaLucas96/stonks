package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Carteira;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CarteiraService {

    public void salvarCarteira(Carteira carteira);

    public boolean isAlreadyPresent(Carteira carteira);

    public Optional<Carteira> findById(int id);

}
