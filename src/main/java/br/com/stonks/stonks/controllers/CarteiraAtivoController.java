package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.models.Carteira;
import br.com.stonks.stonks.models.CarteiraAtivo;
import br.com.stonks.stonks.models.Usuario;
import br.com.stonks.stonks.services.CarteiraAtivoService;
import br.com.stonks.stonks.services.CarteiraService;
import br.com.stonks.stonks.services.UsuarioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping
public class CarteiraAtivoController {

    @Autowired
    private CarteiraAtivoService carteiraAtivoService;

    @Autowired
    private CarteiraService carteiraService;

    @Autowired
    private UsuarioServiceImp usuarioService;

    @GetMapping(value = "/relatorio")
    public String imprimirRelatorio(@RequestParam(value = "idCarteira") int idCarteira, Model model){

        List<CarteiraAtivo> ativos = carteiraAtivoService.findByCarteira(idCarteira);
        Carteira carteira = carteiraService.findById(idCarteira).get();
        Usuario usuario = usuarioService.findById(carteira.getUsuario().getId()).get();

        model.addAttribute("ativos", ativos);
        model.addAttribute("usuario", usuario.getNome());

        return "dashboard/imprimirRelatorio";
    }
}
