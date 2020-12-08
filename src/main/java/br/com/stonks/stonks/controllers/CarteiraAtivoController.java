package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.exception.ResponseException;
import br.com.stonks.stonks.models.*;
import br.com.stonks.stonks.services.*;
import br.ufrn.imd.stonks.framework.framework.model.Despesa;
import br.ufrn.imd.stonks.framework.framework.model.DespesaAtivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class CarteiraAtivoController {

    @Autowired
    private CarteiraAtivoService carteiraAtivoService;

    @Autowired
    private CarteiraService carteiraService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private EmailService emailService;

    @GetMapping(value = "/carteiraativo/relatorio")
    public String imprimirRelatorio(Model model) {

        Usuario usuario = usuarioService.usuarioLogado();
        Carteira carteira = carteiraService.carteiraByUsuario(usuario);

        List<CarteiraAtivo> ativos = carteiraAtivoService.findByAtivosCarteira(carteira.getId(), null);

        List<CarteiraAtivoValor> carteiraAtivoValorList = new ArrayList<>();

        try {
            for (DespesaAtivo ativo : ativos) {
                CarteiraAtivoValor carteiraAtivoValor = new CarteiraAtivoValor();
                carteiraAtivoValor.setDespesaAtivo(ativo);
                Response response = responseService.getDadosAtivo(ativo.getAtivoAbstract().getCodigo());
                carteiraAtivoValor.setValor(response.getValorAcao());
                carteiraAtivoValor.setLucro((float) (response.getValorAcao() - ativo.getValor()));

                carteiraAtivoValorList.add(carteiraAtivoValor);
            }
        } catch (ResponseException e) {
            model.addAttribute("errorFlash", e.getMessage());
            model.addAttribute("usuario", usuario);
            return "dashboard/imprimirRelatorio";
        }

        model.addAttribute("ativosCarteira", carteiraAtivoValorList);
        model.addAttribute("usuario", usuario);

        return "dashboard/imprimirRelatorio";
    }

    @GetMapping(value = "/carteiraativo/relatorio/enviar")
    public String enviarRelatorio(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dashboard/imprimirRelatorio");

        Usuario usuario = usuarioService.usuarioLogado();
        Carteira carteira = carteiraService.carteiraByUsuario(usuario);

        List<CarteiraAtivo> ativos = carteiraAtivoService.findByAtivosCarteira(carteira.getId(), null);

        String mensagemEmail = emailService.montarCorpoEmail(ativos);

        model.addAttribute("ativosCarteira", ativos);
        model.addAttribute("usuario", usuario);

        if (emailService.enviarEmail(mensagemEmail, usuario)) {
            modelAndView.addObject("successMessage", "Relatório enviado por email com sucesso.");
        } else {
            modelAndView.addObject("failMessage", "Relatório não pode ser enviado.");
        }

        return "redirect:/carteiraativo/relatorio";
    }
}
