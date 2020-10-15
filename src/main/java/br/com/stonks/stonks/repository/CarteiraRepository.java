package br.com.stonks.stonks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.stonks.stonks.models.Carteira;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Integer> {

    public Carteira findByid(int id);
}

