package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Ativo;
import br.com.stonks.stonks.models.Carteira;
import br.com.stonks.stonks.models.CarteiraAtivo;
import br.com.stonks.stonks.repository.CarteiraAtivoRepository;
import br.ufrn.imd.stonks.framework.framework.model.DespesaAtivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CarteiraAtivoServiceImp implements CarteiraAtivoService {

    @Autowired
    CarteiraAtivoRepository carteiraAtivoRepository;

    @Override
    public void salvar(CarteiraAtivo carteiraAtivo) {
        carteiraAtivoRepository.save(carteiraAtivo);
    }

    @Override
    public boolean isAlreadyPresent(CarteiraAtivo carteiraAtivo) {
        return carteiraAtivoRepository.findById(carteiraAtivo.getId()).isPresent();
    }

    @Override
    public List<CarteiraAtivo> findByAtivosCarteiraCompra(int id) {
        return carteiraAtivoRepository.findByAtivosCarteiraCompra(id);
    }

    @Override
    public List<DespesaAtivo> findByAtivosCarteira(int id, HashMap<String, String> params) {
        Sort.Direction direction = Sort.Direction.ASC;
        String sortBy = "id";

        if (params != null) {
            if (params.get("order").equals("asc")) {
                direction = Sort.Direction.ASC;
            } else if (params.get("order").equals("desc")) {
                direction = Sort.Direction.DESC;
            }

            if (!params.get("order").isEmpty()) {
                sortBy = params.get("sort");
            }
        }

        Sort sort = Sort.by(direction, sortBy);

        return carteiraAtivoRepository.findByAtivosCarteira(id, sort);
    }

    public Ativo[] listarAtivos(Carteira carteira) {
        CarteiraAtivo[] carteiraAtivo = carteiraAtivoRepository.findAllByDespesa(carteira);

        Ativo[] ativos = new Ativo[carteiraAtivo.length];
        for (int i = 0; i < carteiraAtivo.length; i ++) {
            ativos[i] = (Ativo) carteiraAtivo[i].getAtivoAbstract();
        }
        return ativos;
    }

    @Override
    public Optional<CarteiraAtivo> findById(int id) {
        return carteiraAtivoRepository.findById(id);
    }

    @Override
    public Double totalCarteira(Integer idCarteira){
        return carteiraAtivoRepository.totalCarteira(idCarteira);
    }
}
