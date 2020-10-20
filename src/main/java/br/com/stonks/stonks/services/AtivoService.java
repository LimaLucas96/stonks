package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Ativo;

import java.util.List;

public interface AtivoService {
    public boolean isAtivoAlreadyPresent(Ativo ativo);

    public List<Ativo> findAll();
}