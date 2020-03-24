package br.com.stonks.stonks.repository;

import br.com.stonks.stonks.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    public Empresa findByCnpj(String cnpj);

}