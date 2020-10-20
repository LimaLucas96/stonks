package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.services.AcaoService;
import br.com.stonks.stonks.services.FundoImobiliarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AtivoController {
    @Autowired
    private FundoImobiliarioService fundoImobiliarioService;

    @Autowired
    private AcaoService acaoService;

    @GetMapping("ativos")
    public String index(Model model) {
        model.addAttribute("fundoImobiliarios", fundoImobiliarioService.findAll());
        model.addAttribute("acoes", acaoService.findAll());
        return "ativos/index";
    }
}
