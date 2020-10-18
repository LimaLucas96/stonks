package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.models.Carteira;
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
import java.util.Map;
import java.util.Set;

@Controller
public class AuthenticationController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    CarteiraService carteiraService;

    @Autowired
    CarteiraAtivoService carteiraAtivoService;

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
        modelAndView.addObject("ativos", carteiraAtivoService.listarAtivos(carteira));
//        return modelAndView;

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

//    @RequestMapping(value = "/usuario/register", method = RequestMethod.POST)
//    public ModelAndView create(@Valid Usuario user, BindingResult bindingResult, ModelMap modelMap){
//        ModelAndView modelAndView = new ModelAndView();
//
//        if (bindingResult.hasErrors()){
//            modelAndView.addObject("successMessage", "Por favor corriga os erros.");
//            modelMap.addAttribute("bindingResult", bindingResult);
//        }
//        else if(usuarioService.isUserAlreadyPresent(user)){
//            modelAndView.addObject("successMessage", "Usuario ja existe");
//        }
//        else {
//            usuarioService.salvarUsuario(user);
//            modelAndView.addObject("successMessage", "Usuario registrado com sucesso.");
//        }
//
//        modelAndView.addObject("user", new Usuario());
//        modelAndView.setViewName("register");
//        return modelAndView;
//
//    }
}
