package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.models.Empresa;
import br.com.stonks.stonks.repository.EmpresaRepository;
import br.com.stonks.stonks.services.EmpresaService;
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
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    @GetMapping("empresas")
    public String index(Model model) {
        model.addAttribute("empresas", empresaService.findAll());
        return "empresas/index";
    }

    @GetMapping("empresa/cadastro")
    public ModelAndView paginaCadastro() {
        ModelAndView cadastro = new ModelAndView("empresas/cadastrarEmpresa");
        Empresa empresa = new Empresa();
        cadastro.addObject("empresa", empresa);
        return cadastro;
    }

    @GetMapping("empresa/{id}")
    public String paginaAtualizar(@PathVariable("id") long id, Model model) {
        Empresa empresa = empresaService.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID fornecido é inválido " + id)
        );
        model.addAttribute("empresa", empresa);
        return "empresas/atualizarEmpresa";
    }

    @PostMapping("empresa/cadastro")
    public ModelAndView create(@Valid Empresa empresa, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView empresaModel = new ModelAndView("/empresas/cadastrarEmpresa");

        if (bindingResult.hasErrors()){
            empresaModel.addObject("successMessage", "Corrija os erros.");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else {
            empresaService.salvarEmpresa(empresa);
            empresaModel.addObject("successMessage", "Empresa cadastrada com sucesso.");
        }

        empresaModel.addObject("empresa", new Empresa());
        return empresaModel;
    }

    @PostMapping("empresa/{id}")
    public String atualizar(@PathVariable("id") long id, @Valid Empresa empresa,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            empresa.setId(id);
            return "empresas/atualizarEmpresa";
        }

        empresaService.salvarEmpresa(empresa);
        model.addAttribute("empresas", empresaService.findAll());
        return "empresas/index";
    }

    @DeleteMapping("empresa/{id}")
    public String delete(@PathVariable("id") long id) {
        empresaService.deleteById(id);
        return "empresas/index";
    }
}
