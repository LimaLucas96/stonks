
package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.models.CarteiraUsuario;
import br.com.stonks.stonks.repository.CarteiraUsuarioRepository;
import br.com.stonks.stonks.services.CarteiraUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

import javax.validation.Valid;

@Controller
public class CarteiraUsuarioController {

    @Autowired
    CarteiraUsuarioService carteiraUsuarioService;

    @Autowired
    private CarteiraUsuarioRepository carteiraUsuarioRepository;

    @RequestMapping(value = "/carteira/register", method = RequestMethod.POST)
    public ModelAndView create(@Valid CarteiraUsuario carteira, BindingResult bindingResult, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()){
            modelAndView.addObject("successMessage", "Por favor corriga os erros.");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else if(carteiraUsuarioService.isAlreadyPresent(carteira)){
            modelAndView.addObject("successMessage", "Carteira ja existente");
        }
        else {
            carteiraUsuarioService.salvarUsuario(carteira);
            modelAndView.addObject("successMessage", "Carteira criada com sucesso.");
        }

        modelAndView.addObject("user", new Usuario());
        modelAndView.setViewName("register");
        return modelAndView;

    }

    @RequestMapping(value = "/carteira/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, CarteiraUsuario carteira, Boolean status) {
    	CarteiraUsuario carteiraInstance = carteiraUsuarioRepository.findById(id).get();

        carteiraInstance.setStatus(status);
        carteiraInstance.setUltimaAtualizacao(new Date());
        carteiraUsuarioRepository.save(carteiraInstance);
        return "redirect:/";
    }

    @RequestMapping(value = "/carteira/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") int id) {
        carteiraUsuarioRepository.deleteById(id);

        return "redirect:/login";
    }
}
