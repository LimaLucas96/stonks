
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/carteira/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.usuarioPorEmail(principal.getUsername());
        Carteira carteira = carteiraService.carteiraByUsuario(usuario);

        modelAndView.setViewName("/carteira/index");
        modelAndView.addObject("ativosCarteira", carteiraAtivoService.findByCarteira(carteira.getId()));
        modelAndView.addObject("usuario", usuario);

        return modelAndView;
    }

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
            modelAndView.addObject("errorFlash", "CarteiraAtivo ja existente");
        }
        else {
            carteiraAtivoService.salvar(carteiraAtivo);
            modelAndView.addObject("successFlash", "Ativo registrado na carteira com sucesso.");
        }

        modelAndView.addObject("ativosCarteira", carteiraAtivoService.findByCarteira(carteira.getId()));
        modelAndView.addObject("usuario", usuario);

        modelAndView.setViewName("/carteira/index");

        return modelAndView;

    }

    @RequestMapping(value = "/carteira/editar/{id}")
    public ModelAndView edit(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.usuarioPorEmail(principal.getUsername());

        modelAndView.addObject("ativos", ativoService.findAll());
        modelAndView.addObject("carteiraAtivo", carteiraAtivoService.findById(id).get() );
        modelAndView.addObject("usuario", usuario);

        modelAndView.setViewName("/carteira/edit");

        return modelAndView;
    }

    @RequestMapping(value = "/carteira/cadastrar", method = RequestMethod.GET)
    public ModelAndView cadastrarCarteira() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/carteira/create");
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.usuarioPorEmail(principal.getUsername());
        modelAndView.addObject("ativos", ativoService.findAll());
        modelAndView.addObject("carteiraAtivo", new CarteiraAtivo());
        modelAndView.addObject("usuario", usuario);
        return modelAndView;
    }

    @RequestMapping(value = "/carteira/editar/{id}", method = RequestMethod.POST)
    public ModelAndView update(@PathVariable("id") int id, HttpServletRequest request, @ModelAttribute("carteiraAtivo") CarteiraAtivo carteiraAtivo) {

        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.usuarioPorEmail(principal.getUsername());
        Carteira carteira = carteiraService.carteiraByUsuario(usuario);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("ativosCarteira", carteiraAtivoService.findByCarteira(carteira.getId()));
        modelAndView.addObject("usuario", usuario);
        modelAndView.setViewName("/carteira/index");

        CarteiraAtivo carteiraAtivoInstance = carteiraAtivoService.findById(id).get();

        if (carteiraAtivoInstance == null) {
            modelAndView.addObject("errorFlash", "Ativo não encontrado");

            return modelAndView;
        }
        carteiraAtivo.setCarteira(carteiraAtivoInstance.getCarteira());
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


    @RequestMapping(value = "/ativo/{symbol}", method = RequestMethod.GET)
    @ResponseBody
    public String dadosAtivo(@PathVariable("symbol") String symbol) {
        Response response = responseService.getDadosAtivo(symbol);
        return "{\"dados\":"+response.getTabelaDados()+"}";
    }
}
