package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.models.Carteira;
import br.com.stonks.stonks.models.CarteiraAtivo;
import br.com.stonks.stonks.models.Usuario;
import br.com.stonks.stonks.services.CarteiraAtivoService;
import br.com.stonks.stonks.services.CarteiraService;
import br.com.stonks.stonks.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class AuthenticationController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CarteiraService carteiraService;

    @Autowired
    private CarteiraAtivoService carteiraAtivoService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        Usuario usuario = new Usuario();
        modelAndView.addObject("user", usuario);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dashboard/home");
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.usuarioPorEmail(principal.getUsername());
        Carteira carteira = carteiraService.carteiraByUsuario(usuario);
        modelAndView.addObject("carteiraAtivos", carteiraAtivoService.findByCarteira(carteira.getId()));
        modelAndView.addObject("usuario", usuario);
        
        List<CarteiraAtivo> carteiraAtivos = carteiraAtivoService.findByCarteira(carteira.getId());
        double sum = 0;
        for (CarteiraAtivo ca : carteiraAtivos) {
        	sum += ca.getValor() * ca.getQuantidade();
        }

        modelAndView.addObject("total", sum);

        return modelAndView;
    }

    @RequestMapping(value = "/teste", method = RequestMethod.GET)
    @ResponseBody
    public String teste () {
        HashMap<String, Integer> test = new HashMap<String, Integer>();

        test.put("teste 1", 1);
        test.put("teste 2", 2);
        test.put("teste 3", 3);
        return "{\"dados\":[[\"teste\", \"conexao\"], [\"1\", 12], [\"2\", 12], [\"3\", 12]]}";
    }

}
