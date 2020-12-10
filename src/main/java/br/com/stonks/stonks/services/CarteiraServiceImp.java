package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Carteira;
import br.com.stonks.stonks.models.Usuario;
import br.com.stonks.stonks.dao.CarteiraDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarteiraServiceImp implements CarteiraService{

    private final CarteiraDAO carteiraDAO = new CarteiraDAO();

    @Override
    public void salvarCarteira(Carteira carteira) {
        carteiraDAO.save(carteira);
    }

    @Override
    public boolean isAlreadyPresent(Carteira carteira) {
        Boolean present;

        if (carteiraDAO.findById(carteira.getId()) != null){
            present = true;
        }else{
            present = false;
        }
        return present;
    }

    @Override
    public Carteira findById(int id){
        return carteiraDAO.findById(id);
    }

    @Override
    public Carteira carteiraByUsuario(Usuario usuario) {
        return carteiraDAO.findByUsuario(usuario);
    }

    @Override
    public void deleteById(int id) {
        carteiraDAO.remove(id);
    }
}
