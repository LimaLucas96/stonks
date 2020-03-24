package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Empresa;
import br.com.stonks.stonks.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaImp implements EmpresaService {
    @Autowired
    EmpresaRepository empresaRepository;


    @Override
    public void salvarEmpresa(Empresa empresa) {
        empresaRepository.save(empresa);
    }

    @Override
    public boolean isEmpresaAlreadyPresent(Empresa empresa) {
        return empresaRepository.findByCnpj(empresa.getCnpj()) != null;
    }
}
