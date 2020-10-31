package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.DadosFundamentalista;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface DadosFundamentalistaService {
    Optional<DadosFundamentalista> findByAtivo (Integer idAtivo);
}
