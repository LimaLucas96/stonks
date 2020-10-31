package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.DadosFundamentalista;
import br.com.stonks.stonks.repository.DadosFundamentalistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DadosFundamentalistaServiceImp implements DadosFundamentalistaService {

    @Autowired
    private DadosFundamentalistaRepository dadosFundamentalistaRepository;

    @Override
    public Optional<DadosFundamentalista> findByAtivo(Integer idAtivo) {
        return dadosFundamentalistaRepository.findById(idAtivo);
    }
}
