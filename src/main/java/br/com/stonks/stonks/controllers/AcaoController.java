package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.models.Acao;
import br.com.stonks.stonks.repository.AcaoRepository;
import br.com.stonks.stonks.repository.EmpresaRepository;
import br.com.stonks.stonks.services.AcaoService;
import br.com.stonks.stonks.services.AtivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping("acao/cadastro")
    public ModelAndView paginaCadastro() {
        ModelAndView cadastro = new ModelAndView("/ativos/cadastrarAcao");
        Acao acao = new Acao();
        cadastro.addObject("acao", acao);
        cadastro.addObject("empresas", empresaRepository.findAll());
        return cadastro;
    }

    @GetMapping("acao/{id}")
    public String paginaAtualizar(@PathVariable("id") int id, Model model) {
        Acao acao = acaoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID fornecido é inválido " + id)
        );
        model.addAttribute("acao", acao);
        model.addAttribute("empresas", empresaRepository.findAll());
        return "ativos/atualizarAcao";
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

    @PostMapping("acao/{id}")
    public String atualizar(@PathVariable("id") int id, @Valid Acao acao, BindingResult result, Model model) {

        if (result.hasErrors()) {
            acao.setId(id);
            return "ativos/atualizarAcao";
        }

        acaoRepository.save(acao);
        model.addAttribute("acoes", acaoRepository.findAll());
        return "ativos/index";
    }

    @DeleteMapping("acao/{id}")
    public String delete(@PathVariable("id") int id) {
        acaoRepository.deleteById(id);
        return "ativos/index";
    }
}