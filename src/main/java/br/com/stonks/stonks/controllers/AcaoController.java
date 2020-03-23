package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.models.Acao;
import br.com.stonks.stonks.repository.AcaoRepository;
import br.com.stonks.stonks.services.AcaoService;
import br.com.stonks.stonks.services.AtivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AcaoController {
    @Autowired
    AtivoService ativoService;

    @Autowired
    AcaoService acaoService;

    @Autowired
    private AcaoRepository acaoRepository;


    @GetMapping("acao/cadastro")
    public ModelAndView paginaCadastro() {
        ModelAndView cadastro = new ModelAndView("/ativos/cadastrarAcao");
        Acao acao = new Acao();
        cadastro.addObject("acao", acao);
        return cadastro;
    }

    @PostMapping("acao/cadastro")
    public ModelAndView create(@Valid Acao acao, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView acaoModelView = new ModelAndView("/ativos/cadastrarAcao");

        if (bindingResult.hasErrors()){
            acaoModelView.addObject("successMessage", "Corrija os erros.");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else if(ativoService.isAtivoAlreadyPresent(acao)){
            acaoModelView.addObject("successMessage", "Ativo já existente.");
        }
        else {
            acaoService.salvarAcao(acao);
            acaoModelView.addObject("successMessage", "Ação cadastrada com sucesso.");
        }

        acaoModelView.addObject("acao", new Acao());
        return acaoModelView;
    }

    @DeleteMapping("acao/{id}")
    public String delete(@PathVariable("id") int id) {
        acaoRepository.deleteById(id);
        return "redirect:/home";
    }
}