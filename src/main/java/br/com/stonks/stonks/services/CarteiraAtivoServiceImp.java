
package br.com.stonks.stonks.services;
import br.com.stonks.stonks.models.CarteiraAtivo;
import br.com.stonks.stonks.repository.CarteiraAtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarteiraAtivoServiceImp implements CarteiraAtivoService{

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
}
