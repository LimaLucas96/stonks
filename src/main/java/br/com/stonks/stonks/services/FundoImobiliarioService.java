package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.FundoImobiliario;

import java.util.List;
import java.util.Optional;

public interface FundoImobiliarioService {

    public void salvarFundoImobiliario(FundoImobiliario fundoImobiliario);

    public List<FundoImobiliario> findAll();

    public Optional<FundoImobiliario> findById(int id);

    public void salvar(FundoImobiliario fundoImobiliario);

    public void deleteById(int id);
}
