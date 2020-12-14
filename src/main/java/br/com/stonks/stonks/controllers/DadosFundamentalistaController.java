package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.models.DadosFundamentalista;
import br.com.stonks.stonks.models.Usuario;
import br.com.stonks.stonks.services.AtivoService;
import br.com.stonks.stonks.services.DadosFundamentalistaService;
import br.com.stonks.stonks.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping
public class DadosFundamentalistaController {

    @Autowired
    private AtivoService ativoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private DadosFundamentalistaService dadosFundamentalistaService;

    @RequestMapping(value = "/dadosfundamentalista/vizualizar", method = RequestMethod.GET)
    public ModelAndView vizualizar() {
        ModelAndView modelAndView = new ModelAndView();
        Usuario usuarioLogado = usuarioService.usuarioLogado();

        modelAndView.addObject("ativos", ativoService.findAll());
        modelAndView.addObject("usuario", usuarioLogado);

        modelAndView.setViewName("/dashboard/dadosFundamentalistas");

        return modelAndView;
    }

    @RequestMapping(value = "/dadosfundamentalista/vizualizar", method = RequestMethod.POST)
    public ModelAndView getDados(@RequestParam("ativo") Integer ativo) {
        ModelAndView modelAndView = new ModelAndView();

        DadosFundamentalista dadosFundamentalista = dadosFundamentalistaService.findByAtivo(ativo);

        if (dadosFundamentalista != null) {
            modelAndView.addObject("errorFlash", "Dados desse ativo não foram encontrados.");
            return modelAndView;
        }

        modelAndView.addObject("dadosfundamentalista", dadosFundamentalista);

        Usuario usuarioLogado = usuarioService.usuarioLogado();

        modelAndView.addObject("ativos", ativoService.findAll());
        modelAndView.addObject("usuario", usuarioLogado);

        modelAndView.setViewName("/dashboard/dadosFundamentalistas");

        return modelAndView;
    }
}
