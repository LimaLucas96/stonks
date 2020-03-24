package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Acao;
import br.com.stonks.stonks.repository.AcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcaoImp implements AcaoService {
    @Autowired
    AcaoRepository acaoRepository;

    @Override
    public void salvarAcao(Acao acao) {
        acaoRepository.save(acao);
    }
}
