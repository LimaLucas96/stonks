package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Ativo;
import br.com.stonks.stonks.repository.AtivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtivoServiceImp implements AtivoService {
    @Autowired
    AtivoRepository ativoRepository;

    @Override
    public boolean isAtivoAlreadyPresent(Ativo ativo) {
        return ativoRepository.findByCodigo(ativo.getCodigo()) != null;
    }

    @Override
    public List<Ativo> findAll() {
        return ativoRepository.findAll();
    }
}
