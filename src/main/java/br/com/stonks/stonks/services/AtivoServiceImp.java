package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Ativo;
import br.com.stonks.stonks.repository.AtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtivoServiceImp implements AtivoService {
    @Autowired
    AtivoRepository ativoRepository;

    @Override
    public boolean isAtivoAlreadyPresent(Ativo ativo) {
        return ativoRepository.findByCodigo(ativo.getCodigo()) != null;
    }
}
