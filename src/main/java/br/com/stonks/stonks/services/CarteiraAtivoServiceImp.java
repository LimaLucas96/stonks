package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Ativo;
import br.com.stonks.stonks.models.Carteira;
import br.com.stonks.stonks.models.CarteiraAtivo;
import br.com.stonks.stonks.dao.CarteiraAtivoDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarteiraAtivoServiceImp implements CarteiraAtivoService {

    private final CarteiraAtivoDAO carteiraAtivoDAO = new CarteiraAtivoDAO();

    @Override
    public void salvar(CarteiraAtivo carteiraAtivo) {
        carteiraAtivoDAO.save(carteiraAtivo);
    }

    @Override
    public boolean isAlreadyPresent(CarteiraAtivo carteiraAtivo) {
        return carteiraAtivoDAO.findById(carteiraAtivo.getId()).getId() != 0;
    }

    @Override
    public List<CarteiraAtivo> findByAtivosCarteiraCompra(int id) {
        return carteiraAtivoDAO.findByAtivosCarteiraCompra(id);
    }

    @Override
    public List<CarteiraAtivo> findByAtivosCarteira(int id) {
        return carteiraAtivoDAO.findByAtivosCarteira(id);
    }

    public Ativo[] listarAtivos(Carteira carteira) {
        List<CarteiraAtivo> carteiraAtivo = carteiraAtivoDAO.findByAtivosCarteira(carteira.getId());

        Ativo[] ativos = new Ativo[carteiraAtivo.size()];
        for (int i = 0; i < carteiraAtivo.size(); i++) {
            ativos[i] = carteiraAtivo.get(i).getAtivo();
        }
        return ativos;
    }

    @Override
    public CarteiraAtivo findById(int id) {
        return carteiraAtivoDAO.findById(id);
    }

    @Override
    public Double totalCarteira(Integer idCarteira) {
        return carteiraAtivoDAO.totalCarteira(idCarteira);
    }
}
