package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.EmailConfig;
import br.com.stonks.stonks.models.Carteira;
import br.com.stonks.stonks.models.CarteiraAtivo;
import br.com.stonks.stonks.models.Operacao;
import br.com.stonks.stonks.models.Usuario;
import br.com.stonks.stonks.services.CarteiraAtivoService;
import br.com.stonks.stonks.services.CarteiraService;
import br.com.stonks.stonks.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
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
    
    private EmailConfig emailConfig;
 

    @GetMapping(value = "/carteiraativo/relatorio")
    public String imprimirRelatorio(Model model){

        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.usuarioPorEmail(principal.getUsername());
        Carteira carteira = carteiraService.carteiraByUsuario(usuario);

        List<CarteiraAtivo> ativos = carteiraAtivoService.findByCarteira(carteira.getId());

        model.addAttribute("ativosCarteira", ativos);
        model.addAttribute("usuario", usuario);

        return "dashboard/imprimirRelatorio";
    }
    
    @GetMapping(value = "/carteiraativo/relatorio/enviar")
    public String enviarRelatorio(Model model){

        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.usuarioPorEmail(principal.getUsername());
        Carteira carteira = carteiraService.carteiraByUsuario(usuario);

        List<CarteiraAtivo> ativos = carteiraAtivoService.findByCarteira(carteira.getId());
        
        String body = "Ativo / Valor / Quantidade / Data da Transação\n";
        
        for (CarteiraAtivo ca : ativos) {
        	body += ca.getAtivo().getCodigo() 
        			+ " / " + ca.getValor() 
        			+ " / " + ca.getQuantidade()
        			+ " / " + ca.getDataTransacao() + "\n";
        }

        model.addAttribute("ativosCarteira", ativos);
        model.addAttribute("usuario", usuario);
        
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailConfig.getHost());
        mailSender.setPort(emailConfig.getPort());
        mailSender.setUsername(emailConfig.getUsername());
        mailSender.setPassword(emailConfig.getPassword());
        
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        
        mailMessage.setFrom("admin@stonks.com");
        mailMessage.setTo(usuario.getEmail());
        mailMessage.setSubject("Relatório do Stonks");
        mailMessage.setText(body);
        
        mailSender.send(mailMessage);

        return "dashboard/imprimirRelatorio";
    }
    
}
