
package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.models.CarteiraUsuario;
import br.com.stonks.stonks.repository.AtivoRepository;
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
    
    @Autowired
    private AtivoRepository ativoRepository;

    @RequestMapping(value = "/carteira/cadastrar", method = RequestMethod.POST)
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
            carteiraUsuarioService.salvarCarteira(carteira);
            modelAndView.addObject("successMessage", "Carteira criada com sucesso.");
        }

        modelAndView.addObject("carteira", new CarteiraUsuario());
        modelAndView.setViewName("cadastrarCarteira");
        return modelAndView;

    }
    
    @RequestMapping(value = "/carteira/cadastrar", method = RequestMethod.GET)
    public String cadastrarCarteira() {
    	ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cadastrarCarteira");
        modelAndView.addObject("ativos", ativoRepository.findAll());
        return "dashboard/cadastrarcarteira";
    }

    @RequestMapping(value = "/carteira/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, CarteiraUsuario carteira) {
    	CarteiraUsuario carteiraInstance = carteiraUsuarioRepository.findById(id).get();
    	
        carteiraInstance.setUltimaAtualizacao(new Date());
        carteiraUsuarioRepository.save(carteiraInstance);
        return "redirect:/dashboard/home";
    }

    @RequestMapping(value = "/carteira/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") int id) {
        carteiraUsuarioRepository.deleteById(id);

        return "redirect:/dashboard/home";
    }
    
}
