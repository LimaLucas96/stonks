package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Ativo;
import br.com.stonks.stonks.models.Carteira;
import br.com.stonks.stonks.models.CarteiraAtivo;
import br.com.stonks.stonks.repository.CarteiraAtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Boolean present;

        if (carteiraAtivoRepository.findById(carteiraAtivo.getId()) != null){
            present = true;
        }else{
            present = false;
        }
        return present;
    }

    @Override
    public List<CarteiraAtivo> findByCarteira(int id) {
        return carteiraAtivoRepository.findByCarteira(id);
    }

    public Ativo[] listarAtivos(Carteira carteira) {
        CarteiraAtivo[] carteiraAtivo = carteiraAtivoRepository.findAllByCarteira(carteira);

        Ativo[] ativos = new Ativo[carteiraAtivo.length];
        for (int i = 0; i < carteiraAtivo.length; i ++) {
            ativos[i] = carteiraAtivo[i].getAtivo();
        }
        return ativos;
    }
}
