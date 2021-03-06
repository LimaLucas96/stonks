package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.models.FundoImobiliario;
import br.com.stonks.stonks.services.AtivoService;
import br.com.stonks.stonks.services.EmpresaService;
import br.com.stonks.stonks.services.FundoImobiliarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class FundoImobiliarioController {
    @Autowired
    private AtivoService ativoService;

    @Autowired
    private FundoImobiliarioService fundoImobiliarioService;

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("fundoimobiliario/cadastro")
    public ModelAndView paginaCadastro() {
        ModelAndView cadastro = new ModelAndView("/ativos/cadastrarFundoImobiliario");
        FundoImobiliario fundo = new FundoImobiliario();
        cadastro.addObject("fundoImobiliario", fundo);
        cadastro.addObject("empresas", empresaService.findAll());
        return cadastro;
    }

    @GetMapping("fundoimobiliario/{id}")
    public String paginaAtualizar(@PathVariable("id") int id, Model model) {
        FundoImobiliario fundoImobiliario = fundoImobiliarioService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID fornecido é inválido " + id));
        model.addAttribute("fundoImobiliario", fundoImobiliario);
        model.addAttribute("empresas", empresaService.findAll());
        return "ativos/atualizarFundoImobiliario";
    }

    @PostMapping("fundoimobiliario/cadastro")
    public ModelAndView create(@Valid FundoImobiliario fundo, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView fundoImobiliario = new ModelAndView("/ativos/cadastrarFundoImobiliario");

        if (bindingResult.hasErrors()){
            fundoImobiliario.addObject("errorFlash", "Corrija os erros.");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else if(ativoService.isAtivoAlreadyPresent(fundo)){
            fundoImobiliario.addObject("errorFlash", "Ativo já existente.");
        }
        else {
            fundoImobiliarioService.salvarFundoImobiliario(fundo);
            fundoImobiliario.addObject("successMessage", "Fundo imobiliário cadastrado com sucesso.");
        }

        fundoImobiliario.addObject("fundoImobiliario", new FundoImobiliario());
        return fundoImobiliario;
    }

    @PostMapping("fundoimobiliario/{id}")
    public String atualizar(@PathVariable("id") int id, @Valid FundoImobiliario fundoImobiliario,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            fundoImobiliario.setId(id);
            return "ativos/atualizarFundoImobiliario";
        }

        fundoImobiliarioService.salvar(fundoImobiliario);
        model.addAttribute("fundoImobiliarios", fundoImobiliarioService.findAll());
        return "ativos/index";
    }

    @DeleteMapping("fundoimobiliario/{id}")
    public String delete(@PathVariable("id") int id) {
        fundoImobiliarioService.deleteById(id);
        return "ativos/index";
    }
}
