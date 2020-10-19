package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Empresa;

import java.util.List;
import java.util.Optional;

public interface EmpresaService {

    public void salvarEmpresa(Empresa empresa);

    public List<Empresa> findAll();

    public Optional<Empresa> findById(Long id);

    public void deleteById(Long id);
}
