package br.com.stonks.stonks.repository;

import br.com.stonks.stonks.models.Ativo;

public interface AtivoBaseRepository<T extends Ativo> {

    public Ativo findByCodigo(String codigo);

}