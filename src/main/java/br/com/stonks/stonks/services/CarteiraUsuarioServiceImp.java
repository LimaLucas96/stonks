package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.CarteiraUsuario;
import br.com.stonks.stonks.repository.CarteiraUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class CarteiraUsuarioServiceImp implements CarteiraUsuarioService{

    @Autowired
    CarteiraUsuarioRepository carteiraRepository;

    @Override
    public void salvarCarteira(CarteiraUsuario carteira) {
        
        carteira.setUltimaAtualizacao(new Date());
        carteiraRepository.save(carteira);
    }

    @Override
    public boolean isAlreadyPresent(CarteiraUsuario carteira) {
        Boolean present;

        if (carteiraRepository.findById(carteira.getId()) != null){
            present = true;
        }else{
            present = false;
        }
        return present;
    }
}
