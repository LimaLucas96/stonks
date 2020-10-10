package br.com.stonks.stonks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.stonks.stonks.models.CarteiraUsuario;

@Repository
public interface CarteiraUsuarioRepository extends JpaRepository<CarteiraUsuario, Integer> {

    public CarteiraUsuario findByid(int id);
}

