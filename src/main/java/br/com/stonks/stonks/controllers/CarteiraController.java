
package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.exception.ResponseException;
import br.com.stonks.stonks.models.Carteira;
import br.com.stonks.stonks.models.CarteiraAtivo;
import br.com.stonks.stonks.models.Response;
import br.com.stonks.stonks.models.Usuario;
import br.com.stonks.stonks.services.*;
import br.ufrn.imd.stonks.framework.framework.exception.AbstractEntityException;
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
import java.util.Optional;

@Controller
public class CarteiraController {

    @Autowired
    private CarteiraService carteiraService;

    @Autowired
    private CarteiraAtivoServiceImp carteiraAtivoService;

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
        if (carteira != null) {
            modelAndView.addObject("ativosCarteira", carteiraAtivoService.findByAtivosDespesa(carteira.getId(), null));
        } else {
            modelAndView.addObject("ativosCarteira",null);
        }
        modelAndView.addObject("usuario", usuarioLogado);

        return modelAndView;
    }

    @RequestMapping(value = "/carteira/cadastrar", method = RequestMethod.POST)
    public ModelAndView create(@Valid CarteiraAtivo carteiraAtivo,
                               BindingResult bindingResult,
                               @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataTransacao,
                               ModelMap modelMap) {

        ModelAndView modelAndView = new ModelAndView();

        carteiraAtivo.setDataTransacao(dataTransacao);
        Usuario usuarioLogado = usuarioService.usuarioLogado();

        try {
            carteiraService.adicionar(carteiraAtivo, usuarioLogado);
        } catch (AbstractEntityException e) {
            modelAndView.addObject("errorFlash", e.getMessage());
        }

        Carteira carteira = (Carteira) carteiraService.despesaByUsuario();

        modelAndView.addObject("ativosCarteira",
                carteiraAtivoService.findByAtivosDespesa(carteira.getId(), null));
        modelAndView.addObject("usuario", usuarioLogado);

        modelAndView.setViewName("/carteira/index");

        return modelAndView;
    }

    @RequestMapping(value = "/carteira/editar/{id}")
    public ModelAndView edit(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Usuario usuarioLogado = usuarioService.usuarioLogado();

        modelAndView.addObject("ativos", ativoService.findAll());
        modelAndView.addObject("carteiraAtivo", carteiraAtivoService.findById(id).get());
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
    public ModelAndView update(@PathVariable("id") int id, HttpServletRequest request, @ModelAttribute("carteiraAtivo") CarteiraAtivo carteiraAtivo) {
        Usuario usuarioLogado = usuarioService.usuarioLogado();
        Carteira carteira = carteiraService.carteiraByUsuario(usuarioLogado);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("ativosCarteira", carteiraAtivoService.findByAtivosDespesa(carteira.getId(), null));
        modelAndView.addObject("usuario", usuarioLogado);
        modelAndView.setViewName("/carteira/index");

        Optional<CarteiraAtivo> carteiraAtivoInstance = carteiraAtivoService.findById(id);

        if (!carteiraAtivoInstance.isPresent()) {
            modelAndView.addObject("errorFlash", "Ativo não encontrado");
            return modelAndView;
        }
        carteiraAtivo.setDespesa(carteiraAtivoInstance.get().getDespesa());
        carteiraAtivoService.salvar(carteiraAtivo);

        modelAndView.addObject("successFlash", "Ativo Atualizado");
        modelAndView.setViewName("/carteira/index");

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
        Optional<CarteiraAtivo> carteiraAtivo = carteiraAtivoService.findById(id);

        if (!carteiraAtivo.isPresent()) {
            return "{\"message\": \"Carteira não encontrada\"}";
        }

        Response response;
        double valorLucro;

        try {
            response = responseService.getDadosAtivo(carteiraAtivo.get().getAtivoAbstract().getCodigo());
            valorLucro = response.getValorAcao() - carteiraAtivo.get().getValor();
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
