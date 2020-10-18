
package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.models.Carteira;
import br.com.stonks.stonks.models.CarteiraAtivo;
import br.com.stonks.stonks.models.Usuario;
import br.com.stonks.stonks.repository.CarteiraRepository;
import br.com.stonks.stonks.services.CarteiraService;
import br.com.stonks.stonks.repository.AtivoRepository;
import br.com.stonks.stonks.repository.CarteiraAtivoRepository;
import br.com.stonks.stonks.services.CarteiraAtivoService;
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
public class CarteiraController {

    @Autowired
    CarteiraService carteiraService;
    
    @Autowired
    CarteiraAtivoService carteiraAtivoService;

    @Autowired
    private CarteiraRepository carteiraRepository;
    
    @Autowired
    private CarteiraAtivoRepository carteiraAtivoRepository;
    
    @Autowired
    private AtivoRepository ativoRepository;

    @RequestMapping(value = "/carteira/cadastrar", method = RequestMethod.POST)
    public ModelAndView create(@Valid CarteiraAtivo carteiraAtivo, BindingResult bindingResult, @DateTimeFormat(pattern = "yyyy-MM-dd") Date data_compra, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()){
            modelAndView.addObject("successMessage", "Por favor corriga os erros.");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else if(carteiraAtivoService.isAlreadyPresent(carteiraAtivo)){
            modelAndView.addObject("failMessage", "CarteiraAtivo ja existente");
        }
        else {
            carteiraAtivoService.salvar(carteiraAtivo);
            modelAndView.addObject("successMessage", "Ativo registrado na carteira com sucesso.");
        }
        
        modelAndView.addObject("carteiraAtivo", carteiraAtivoRepository.findById(carteiraAtivo.getId()));
        modelAndView.addObject("ativos", ativoRepository.findAll());

        modelAndView.setViewName("/dashboard/cadastrarcarteira");
       
        return modelAndView;

    }
    
    @RequestMapping(value = "/carteira/cadastrar", method = RequestMethod.GET)
    public ModelAndView cadastrarCarteira() {
    	ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/dashboard/cadastrarcarteira");
        modelAndView.addObject("ativos", ativoRepository.findAll());
        modelAndView.addObject("carteiraAtivo", new CarteiraAtivo());
        return modelAndView;
    }

    @RequestMapping(value = "/carteira/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, Carteira carteira) {
    	Carteira carteiraInstance = carteiraRepository.findById(id).get();
        carteiraRepository.save(carteiraInstance);
        
        return "redirect:/dashboard/home";
    }

    @RequestMapping(value = "/carteira/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") int id) {
        carteiraRepository.deleteById(id);

        return "redirect:/dashboard/home";
    }
    
}
