package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Empresa;
import br.com.stonks.stonks.dao.EmpresaDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaImp implements EmpresaService {

    private final EmpresaDAO empresaRepository = new EmpresaDAO();

    @Override
    public void salvarEmpresa(Empresa empresa) {
        empresaRepository.save(empresa);
    }

    @Override
    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    @Override
    public Empresa findById(Long id) {
        return empresaRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        empresaRepository.remove(id);
    }
}
