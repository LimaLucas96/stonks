package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.DadosFundamentalista;
import br.com.stonks.stonks.dao.DadosFundamentalistaDAO;
import org.springframework.stereotype.Service;

@Service
public class DadosFundamentalistaServiceImp implements DadosFundamentalistaService {

    private final DadosFundamentalistaDAO dadosFundamentalistaRepository = new DadosFundamentalistaDAO();

    @Override
    public DadosFundamentalista findByAtivo(Integer idAtivo) {
        return dadosFundamentalistaRepository.findByAtivo(idAtivo);
    }
}
