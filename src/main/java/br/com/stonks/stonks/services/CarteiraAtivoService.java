package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Ativo;
import br.com.stonks.stonks.models.Carteira;
import br.com.stonks.stonks.models.CarteiraAtivo;
import br.ufrn.imd.stonks.framework.framework.model.DespesaAtivo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface CarteiraAtivoService {

    public void salvar(CarteiraAtivo carteiraAtivo);

    public boolean isAlreadyPresent(CarteiraAtivo carteiraAtivo);

    public List<CarteiraAtivo> findByAtivosCarteiraCompra(int id);

    public List<CarteiraAtivo> findByAtivosCarteira(int id, HashMap<String, String> params);

    Ativo[] listarAtivos(Carteira carteira);

    public Optional<CarteiraAtivo> findById(int id);

    public Double totalCarteira(Integer idCarteira);
}
