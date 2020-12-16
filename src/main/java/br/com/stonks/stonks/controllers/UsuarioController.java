package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.exception.UsuarioExistenteException;
import br.com.stonks.stonks.models.Role;
import br.com.stonks.stonks.models.Usuario;
import br.com.stonks.stonks.exception.CpfInvalidoException;
import br.com.stonks.stonks.services.RoleService;
import br.com.stonks.stonks.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/usuario/register", method = RequestMethod.POST)
    public ModelAndView create(@Valid Usuario user, BindingResult bindingResult, ModelMap modelMap) throws Exception {
         ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("failMessage", "Por favor corriga os erros.");
            modelMap.addAttribute("bindingResult", bindingResult);
        } else {
            try {
                usuarioService.salvarUsuario(user);
                Usuario usuario = usuarioService.findByEmail(user.getEmail());
                Role siteUser = roleService.findByRole("SITE_USER");
                roleService.saveRoleUsuario(siteUser, usuario);
                modelAndView.addObject("successMessage", "Usuario registrado com sucesso.");
            } catch (UsuarioExistenteException | CpfInvalidoException e){
                modelAndView.addObject("failMessage", e.getMessage());
                modelAndView.addObject("user", user);
                modelAndView.setViewName("register");
                return modelAndView;
            }
        }

        modelAndView.setViewName("login");
        return modelAndView;

    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, Usuario usuario) throws CpfInvalidoException, UsuarioExistenteException {
        Usuario usuarioOptional = usuarioService.findById(id);

        if (usuarioOptional == null){
            return "Usuário não encontrado.";
        }

        Usuario usuarioInstance = usuarioOptional;
        usuarioInstance.setNome(usuario.getNome());

        usuarioService.salvarUsuario(usuarioInstance);
        return "redirect:/register";
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") int id) {
        usuarioService.deleteById(id);

        return "redirect:/login";
    }
}
