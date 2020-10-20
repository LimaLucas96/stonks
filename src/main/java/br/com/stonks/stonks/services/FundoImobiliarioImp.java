package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.FundoImobiliario;
import br.com.stonks.stonks.repository.FundoImobiliarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FundoImobiliarioImp implements FundoImobiliarioService {
    @Autowired
    FundoImobiliarioRepository fundoImobiliarioRepository;

    @Override
    public void salvarFundoImobiliario(FundoImobiliario fundoImobiliario) {
        fundoImobiliarioRepository.save(fundoImobiliario);
    }

    @Override
    public List<FundoImobiliario> findAll() {
        return fundoImobiliarioRepository.findAll();
    }

    @Override
    public Optional<FundoImobiliario> findById(int id) {
        return fundoImobiliarioRepository.findById(id);
    }

    @Override
    public void salvar(FundoImobiliario fundoImobiliario) {
        fundoImobiliarioRepository.save(fundoImobiliario);
    }

    @Override
    public void deleteById(int id) {
        fundoImobiliarioRepository.deleteById(id);
    }
}
