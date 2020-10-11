package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.exception.UsuarioExistenteException;
import br.com.stonks.stonks.models.Usuario;
import br.com.stonks.stonks.repository.UsuarioRepository;
import br.com.stonks.stonks.exception.CpfInvalidoException;
import br.com.stonks.stonks.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping(value = "/usuario/register", method = RequestMethod.POST)
    public ModelAndView create(@Valid Usuario user, BindingResult bindingResult, ModelMap modelMap) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("errorFlash", "Por favor corriga os erros.");
            modelMap.addAttribute("bindingResult", bindingResult);
        } else {
            try {
                usuarioService.salvarUsuario(user);
                modelAndView.addObject("successFlash", "Usuario registrado com sucesso.");
            } catch (UsuarioExistenteException | CpfInvalidoException e){
                modelAndView.addObject("errorFlash", e.getMessage());
            }
        }

        modelAndView.addObject("user", new Usuario());
        modelAndView.setViewName("register");
        return modelAndView;

    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, Usuario usuario) {
        Usuario usuarioInstance = usuarioRepository.findById(id).get();

        usuarioInstance.setNome(usuario.getNome());

        usuarioRepository.save(usuarioInstance);
        return "redirect:/register";
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") int id) {
        usuarioRepository.deleteById(id);

        return "redirect:/login";
    }
}
