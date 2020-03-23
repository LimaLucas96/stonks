package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Ativo;

public interface AtivoService {
    public boolean isAtivoAlreadyPresent(Ativo ativo);
}