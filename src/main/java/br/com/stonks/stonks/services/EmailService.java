package br.com.stonks.stonks.services;

import br.com.stonks.stonks.models.CarteiraAtivo;
import br.com.stonks.stonks.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmailService {
    public String montarCorpoEmailRelatorio(List<CarteiraAtivo> ativos);

    public Boolean enviarEmail(String mensagemEmail, Usuario usuario);
}
