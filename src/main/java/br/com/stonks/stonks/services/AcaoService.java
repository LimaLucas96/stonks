package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Acao;

import java.util.List;
import java.util.Optional;

public interface AcaoService {

    public void salvarAcao(Acao acao);

    public Optional<Acao> findById(int id);

    public void salvar(Acao acao);

    public List<Acao> findAll();

    public void deleteById(int id);

}