package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Empresa;
import br.com.stonks.stonks.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaImp implements EmpresaService {
    @Autowired
    EmpresaRepository empresaRepository;

    @Override
    public void salvarEmpresa(Empresa empresa) {
        empresaRepository.save(empresa);
    }

    @Override
    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    @Override
    public Optional<Empresa> findById(Long id) {
        return empresaRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        empresaRepository.deleteById(id);
    }
}
