package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.models.Carteira;
import br.com.stonks.stonks.models.CarteiraAtivo;
import br.com.stonks.stonks.models.Usuario;
import br.com.stonks.stonks.services.CarteiraAtivoService;
import br.com.stonks.stonks.services.CarteiraService;
import br.com.stonks.stonks.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@Controller
public class HistoricoTransacaoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CarteiraService carteiraService;

    @Autowired
    private CarteiraAtivoService carteiraAtivoService;

    @GetMapping("/historicoTransacao/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        Usuario usuarioLogado = usuarioService.usuarioLogado();

        Carteira carteira = carteiraService.carteiraByUsuario(usuarioLogado);

        HashMap<String, String> params = new HashMap<>();
        params.put("sort", "dataTransacao");
        params.put("order", "desc");
        List<CarteiraAtivo> ativos = carteiraAtivoService.findByAtivosCarteira(carteira.getId(), params);

        modelAndView.addObject("usuario", usuarioLogado);
        modelAndView.addObject("ativosCarteira", ativos);

        modelAndView.setViewName("/dashboard/historicoTransacoes");

        return modelAndView;
    }
}
