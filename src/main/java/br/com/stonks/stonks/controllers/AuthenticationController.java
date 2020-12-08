package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.models.Carteira;
import br.com.stonks.stonks.models.Usuario;
import br.com.stonks.stonks.services.CarteiraAtivoService;
import br.com.stonks.stonks.services.CarteiraService;
import br.com.stonks.stonks.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticationController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CarteiraService carteiraService;

    @Autowired
    private CarteiraAtivoService carteiraAtivoService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        Usuario usuario = new Usuario();
        modelAndView.addObject("user", usuario);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dashboard/home");

        Usuario usuarioLogado = usuarioService.usuarioLogado();
        Carteira carteira = null;
        carteira= carteiraService.carteiraByUsuario(usuarioLogado);

        modelAndView.addObject("usuario", usuarioLogado);

        double sum = 0;

        if (carteira != null) {
            sum = carteiraAtivoService.totalCarteira(carteira.getId());
        }

        modelAndView.addObject("carteiraAtivos", carteira != null ?
                carteiraAtivoService.findByAtivosCarteiraCompra(carteira.getId()) : null);
        modelAndView.addObject("total", sum);

        return modelAndView;
    }
}
