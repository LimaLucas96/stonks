
package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.models.*;
import br.com.stonks.stonks.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class CarteiraController {

    @Autowired
    private CarteiraService carteiraService;

    @Autowired
    private CarteiraAtivoService carteiraAtivoService;

    @Autowired
    private AtivoService ativoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ResponseService responseService;

    @RequestMapping(value = "/carteira/cadastrar", method = RequestMethod.POST)
    public ModelAndView create(@Valid CarteiraAtivo carteiraAtivo, BindingResult bindingResult, @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataTransacao, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();

        carteiraAtivo.setDataTransacao(dataTransacao);
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.usuarioPorEmail(principal.getUsername());
        Carteira carteira = carteiraService.carteiraByUsuario(usuario);

        if (carteira == null) {
            carteira = new Carteira();
            carteira.setUsuario(usuario);
            carteira.setStatus(true);

            carteiraService.salvarCarteira(carteira);
        }

        carteiraAtivo.setCarteira(carteira);
        carteiraAtivo.setOperacao(Operacao.COMPRA);

         if(carteiraAtivoService.isAlreadyPresent(carteiraAtivo)){
            modelAndView.addObject("failMessage", "CarteiraAtivo ja existente");
        }
        else {
            carteiraAtivoService.salvar(carteiraAtivo);
            modelAndView.addObject("successMessage", "Ativo registrado na carteira com sucesso.");
        }

        modelAndView.addObject("carteiraAtivo", carteiraAtivoService.findById(carteiraAtivo.getId()));
        modelAndView.addObject("ativos", ativoService.findAll());
        modelAndView.addObject("usuario", usuario);

        modelAndView.setViewName("/dashboard/cadastrarcarteira");

        return modelAndView;

    }

    @RequestMapping(value = "/carteira/cadastrar", method = RequestMethod.GET)
    public ModelAndView cadastrarCarteira() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dashboard/cadastrarcarteira");
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.usuarioPorEmail(principal.getUsername());
        modelAndView.addObject("ativos", ativoService.findAll());
        modelAndView.addObject("carteiraAtivo", new CarteiraAtivo());
        modelAndView.addObject("usuario", usuario);
        return modelAndView;
    }

    @RequestMapping(value = "/carteira/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, Carteira carteira) {
    	Carteira carteiraInstance = carteiraService.findById(id).get();
        carteiraService.salvarCarteira(carteiraInstance);

        return "redirect:/dashboard/home";
    }

    @RequestMapping(value = "/carteira/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") int id) {
        carteiraService.deleteById(id);

        return "redirect:/dashboard/home";
    }


    @RequestMapping(value = "/ativo/{symbol}", method = RequestMethod.GET)
    @ResponseBody
    public String dadosAtivo(@PathVariable("symbol") String symbol) {
        Response response = responseService.getDadosAtivo(symbol);
        return "{\"dados\":"+response.getTabelaDados()+"}";
    }
}
