package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.models.FundoImobiliario;
import br.com.stonks.stonks.services.AtivoService;
import br.com.stonks.stonks.services.FundoImobiliarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class FundoImobiliarioController {
    @Autowired
    AtivoService ativoService;

    @Autowired
    FundoImobiliarioService fundoImobiliarioService;

    @GetMapping("fundo-imobiliario/cadastro")
    public ModelAndView paginaCadastro() {
        ModelAndView cadastro = new ModelAndView("/ativos/cadastrarFundoImobiliario");
        FundoImobiliario fundo = new FundoImobiliario();
        cadastro.addObject("fundoImobiliario", fundo);
        return cadastro;
    }

    @PostMapping("fundo-imobiliario/cadastro")
    public ModelAndView create(@Valid FundoImobiliario fundo, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView fundoImobiliario = new ModelAndView("/ativos/cadastrarFundoImobiliario");

        if (bindingResult.hasErrors()){
            fundoImobiliario.addObject("successMessage", "Corrija os erros.");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else if(ativoService.isAtivoAlreadyPresent(fundo)){
            fundoImobiliario.addObject("successMessage", "Ativo já existente.");
        }
        else {
            fundoImobiliarioService.salvarFundoImobiliario(fundo);
            fundoImobiliario.addObject("successMessage", "Fundo imobiliário cadastrado com sucesso.");
        }

        fundoImobiliario.addObject("fundoImobiliario", new FundoImobiliario());
        return fundoImobiliario;
    }
}
