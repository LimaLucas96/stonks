package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.models.FundoImobiliario;
import br.com.stonks.stonks.repository.FundoImobiliarioRepository;
import br.com.stonks.stonks.services.AtivoService;
import br.com.stonks.stonks.services.FundoImobiliarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class FundoImobiliarioController {
    @Autowired
    AtivoService ativoService;

    @Autowired
    FundoImobiliarioService fundoImobiliarioService;

    @Autowired
    private FundoImobiliarioRepository fundoImobiliarioRepository;

    @GetMapping("fundo-imobiliario/cadastro")
    public ModelAndView paginaCadastro() {
        ModelAndView cadastro = new ModelAndView("/ativos/cadastrarFundoImobiliario");
        FundoImobiliario fundo = new FundoImobiliario();
        cadastro.addObject("fundoImobiliario", fundo);
        return cadastro;
    }

    @GetMapping("fundo-imobiliario/{id}")
    public String paginaAtualizar(@PathVariable("id") int id, Model model) {
        FundoImobiliario fundoImobiliario = fundoImobiliarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID fornecido é inválido " + id));
        model.addAttribute("fundoImobiliario", fundoImobiliario);
        return "ativos/atualizarFundoImobiliario";
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

    @PostMapping("fundo-imobiliario/{id}")
    public String atualizar(@PathVariable("id") int id, @Valid FundoImobiliario fundoImobiliario,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            fundoImobiliario.setId(id);
            return "ativos/atualizarFundoImobiliario";
        }

        fundoImobiliarioRepository.save(fundoImobiliario);
        model.addAttribute("fundoImobiliarios", fundoImobiliarioRepository.findAll());
        return "ativos/index";
    }

    @DeleteMapping("fundo-imobiliario/{id}")
    public String delete(@PathVariable("id") int id) {
        fundoImobiliarioRepository.deleteById(id);
        return "ativos/index";
    }
}
