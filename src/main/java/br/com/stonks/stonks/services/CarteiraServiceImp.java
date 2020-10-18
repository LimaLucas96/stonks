package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Carteira;
import br.com.stonks.stonks.models.Usuario;
import br.com.stonks.stonks.repository.CarteiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class CarteiraServiceImp implements CarteiraService{

    @Autowired
    CarteiraRepository carteiraRepository;

    @Override
    public void salvarCarteira(Carteira carteira) {

        carteiraRepository.save(carteira);
    }

    @Override
    public boolean isAlreadyPresent(Carteira carteira) {
        Boolean present;

        if (carteiraRepository.findById(carteira.getId()) != null){
            present = true;
        }else{
            present = false;
        }
        return present;
    }

    @Override
    public Carteira carteiraByUsuario(Usuario usuario) {
        return carteiraRepository.findByUsuario(usuario);
    }
}
