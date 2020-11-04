package br.com.stonks.stonks.repository;

import br.com.stonks.stonks.models.DadosFundamentalista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DadosFundamentalistaRepository extends JpaRepository<DadosFundamentalista, Integer> {

}
