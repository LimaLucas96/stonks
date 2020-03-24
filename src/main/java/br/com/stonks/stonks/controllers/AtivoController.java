package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.services.AtivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AtivoController {
    @Autowired
    AtivoService ativoService;

    @GetMapping("ativos")
    public String index() {
        return "ativos/index";
    }
}
