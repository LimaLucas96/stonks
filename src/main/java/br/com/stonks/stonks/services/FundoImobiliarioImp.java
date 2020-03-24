package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.FundoImobiliario;
import br.com.stonks.stonks.repository.FundoImobiliarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FundoImobiliarioImp implements FundoImobiliarioService {
    @Autowired
    FundoImobiliarioRepository fundoImobiliarioRepository;

    @Override
    public void salvarFundoImobiliario(FundoImobiliario fundoImobiliario) {
        fundoImobiliarioRepository.save(fundoImobiliario);
    }

}
