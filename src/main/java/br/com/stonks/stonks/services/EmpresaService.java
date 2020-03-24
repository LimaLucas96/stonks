package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.Empresa;

public interface EmpresaService {

    public void salvarEmpresa(Empresa empresa);

    public boolean isEmpresaAlreadyPresent(Empresa empresa);

}
