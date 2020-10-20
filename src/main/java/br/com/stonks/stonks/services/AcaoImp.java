package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Acao;
import br.com.stonks.stonks.repository.AcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcaoImp implements AcaoService {
    @Autowired
    AcaoRepository acaoRepository;

    @Override
    public void salvarAcao(Acao acao) {
        acaoRepository.save(acao);
    }

    @Override
    public Optional<Acao> findById(int id) {
        return acaoRepository.findById(id);
    }

    @Override
    public void salvar(Acao acao) {
        acaoRepository.save(acao);
    }

    @Override
    public List<Acao> findAll() {
        return acaoRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        acaoRepository.deleteById(id);
    }
}
