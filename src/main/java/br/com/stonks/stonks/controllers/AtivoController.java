package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.repository.AcaoRepository;
import br.com.stonks.stonks.repository.FundoImobiliarioRepository;
import br.com.stonks.stonks.services.AtivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AtivoController {
    @Autowired
    AtivoService ativoService;

    @Autowired
    FundoImobiliarioRepository fundoImobiliarioRepository;

    @Autowired
    AcaoRepository acaoRepository;

    @GetMapping("ativos")
    public String index(Model model) {
        model.addAttribute("fundoImobiliarios", fundoImobiliarioRepository.findAll());
        model.addAttribute("acoes", acaoRepository.findAll());
        return "ativos/index";
    }
}
