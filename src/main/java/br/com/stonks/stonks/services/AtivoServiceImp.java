package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Ativo;
import br.com.stonks.stonks.dao.AtivoDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtivoServiceImp implements AtivoService {

    AtivoDAO ativoRepository = new AtivoDAO();

    @Override
    public boolean isAtivoAlreadyPresent(Ativo ativo) {
        return ativoRepository.findByCodigo(ativo.getCodigo()) != null;
    }

    @Override
    public List<Ativo> findAll() {
        return ativoRepository.findAll();
    }
}
