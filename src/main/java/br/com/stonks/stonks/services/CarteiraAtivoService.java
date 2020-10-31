package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Ativo;
import br.com.stonks.stonks.models.Carteira;
import br.com.stonks.stonks.models.CarteiraAtivo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CarteiraAtivoService {

    public void salvar(CarteiraAtivo carteiraAtivo);

    public boolean isAlreadyPresent(CarteiraAtivo carteiraAtivo);

    public List<CarteiraAtivo> findByAtivosCarteiraCompra(int id);

    public List<CarteiraAtivo> findByAtivosCarteira(int id);

    Ativo[] listarAtivos(Carteira carteira);

    public Optional<CarteiraAtivo> findById(int id);
}
