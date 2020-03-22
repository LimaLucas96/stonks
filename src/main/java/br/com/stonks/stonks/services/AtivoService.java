package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Ativo;
import org.springframework.stereotype.Service;

@Service
public interface AtivoService {
    public void salvarAtivo(Ativo ativo);
}