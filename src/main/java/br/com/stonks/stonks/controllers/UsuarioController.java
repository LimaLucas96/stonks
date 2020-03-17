package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.models.Usuario;
import br.com.stonks.stonks.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(value = "/usuario/register", method = RequestMethod.POST)
    public ModelAndView create(@Valid Usuario user, BindingResult bindingResult, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()){
            modelAndView.addObject("successMessage", "Por favor corriga os erros.");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else if(usuarioService.isUserAlreadyPresent(user)){
            modelAndView.addObject("successMessage", "Usuario ja existe");
        }
        else {
            usuarioService.salvarUsuario(user);
            modelAndView.addObject("successMessage", "Usuario registrado com sucesso.");
        }

        modelAndView.addObject("user", new Usuario());
        modelAndView.setViewName("register");
        return modelAndView;

    }

    public void update() {

    }

    public void delete() {

    }
}
