
package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.exception.ResponseException;
import br.com.stonks.stonks.models.*;
import br.com.stonks.stonks.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
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

    @RequestMapping(value = "/carteira/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        Usuario usuarioLogado = usuarioService.usuarioLogado();
        Carteira carteira = carteiraService.carteiraByUsuario(usuarioLogado);

        modelAndView.setViewName("/carteira/index");
        modelAndView.addObject("ativosCarteira", carteiraAtivoService.findByAtivosCarteira(carteira.getId()));
        modelAndView.addObject("usuario", usuarioLogado);

        return modelAndView;
    }

    @RequestMapping(value = "/carteira/cadastrar", method = RequestMethod.POST)
    public ModelAndView create(@Valid CarteiraAtivo carteiraAtivo,
                               BindingResult bindingResult,
                               @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataTransacao,
                               int ativo,
                               ModelMap modelMap) {

        ModelAndView modelAndView = new ModelAndView();

        carteiraAtivo.setDataTransacao(dataTransacao);
        Usuario usuarioLogado = usuarioService.usuarioLogado();
        Carteira carteira = carteiraService.carteiraByUsuario(usuarioLogado);

        Ativo ativoInstance = ativoService.findById(ativo);

        if (carteira == null) {
            carteira = new Carteira(usuarioLogado);
            carteiraService.salvarCarteira(carteira);
        }

        carteiraAtivo.setCarteira(carteira);
        carteiraAtivo.setAtivo(ativoInstance);

        if (carteiraAtivoService.isAlreadyPresent(carteiraAtivo)) {
            modelAndView.addObject("errorFlash", "CarteiraAtivo ja existente");
        } else {
            carteiraAtivoService.salvar(carteiraAtivo);
            modelAndView.addObject("successFlash", "Ativo registrado na carteira com sucesso.");
        }

        modelAndView.addObject("ativosCarteira", carteiraAtivoService.findByAtivosCarteira(carteira.getId()));
        modelAndView.addObject("usuario", usuarioLogado);

        modelAndView.setViewName("/carteira/index");

        return modelAndView;
    }

    @RequestMapping(value = "/carteira/editar/{id}")
    public ModelAndView edit(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Usuario usuarioLogado = usuarioService.usuarioLogado();

        modelAndView.addObject("ativos", ativoService.findAll());
        modelAndView.addObject("carteiraAtivo", carteiraAtivoService.findById(id));
        modelAndView.addObject("usuario", usuarioLogado);

        modelAndView.setViewName("/carteira/edit");

        return modelAndView;
    }

    @RequestMapping(value = "/carteira/cadastrar", method = RequestMethod.GET)
    public ModelAndView cadastrarCarteira() {
        Usuario usuarioLogado = usuarioService.usuarioLogado();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ativos", ativoService.findAll());
        modelAndView.addObject("carteiraAtivo", new CarteiraAtivo());
        modelAndView.addObject("usuario", usuarioLogado);
        modelAndView.setViewName("/carteira/create");

        return modelAndView;
    }

    @RequestMapping(value = "/carteira/editar/{id}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable("id") int id,
                               HttpServletRequest request,
                               @Valid CarteiraAtivo carteiraAtivo,
                               BindingResult bindingResult,
                               @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataTransacao,
                               int ativo) {
        Usuario usuarioLogado = usuarioService.usuarioLogado();
        Carteira carteira = carteiraService.carteiraByUsuario(usuarioLogado);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("usuario", usuarioLogado);
        modelAndView.setViewName("/carteira/index");

        CarteiraAtivo carteiraAtivoInstance = carteiraAtivoService.findById(id);

        if (carteiraAtivoInstance == null) {
            modelAndView.addObject("errorFlash", "Ativo não encontrado");
            return modelAndView;
        }
        carteiraAtivo.setCarteira(carteira);
        carteiraAtivo.setAtivo(ativoService.findById(ativo));
        carteiraAtivo.setDataTransacao(dataTransacao);

        carteiraAtivoService.atualizar(carteiraAtivo);

        modelAndView.addObject("successFlash", "Ativo Atualizado");
        modelAndView.setViewName("/carteira/index");
        modelAndView.addObject("ativosCarteira", carteiraAtivoService.findByAtivosCarteira(carteira.getId()));

        return modelAndView;
    }

    @RequestMapping(value = "/carteira/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") int id) {
        carteiraService.deleteById(id);

        return "redirect:/dashboard/home";
    }


    @RequestMapping(value = "/ativo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String dadosAtivo(@PathVariable("id") int id) {
        CarteiraAtivo carteiraAtivo = carteiraAtivoService.findById(id);

        if (carteiraAtivo.getId() == 0) {
            return "{\"message\": \"Carteira não encontrada\"}";
        }

        Response response;
        double valorLucro;

        try {
            response = responseService.getDadosAtivo(carteiraAtivo.getAtivo().getCodigo());
            valorLucro = response.getValorAcao() - carteiraAtivo.getValor();
        } catch (ResponseException e) {
            return "{\"message\": " + e.getMessage() + "}";
        }

        BigDecimal bd = new BigDecimal(valorLucro);
        bd.setScale(2, BigDecimal.ROUND_HALF_DOWN);

        return "{\"dados\":" + response.getTabelaDados() + "," +
                " \"valorAcao\": " + response.getValorAcao() + "," +
                " \"lucroAcao\": " + bd.setScale(2, BigDecimal.ROUND_HALF_DOWN) + "}";
    }
}
