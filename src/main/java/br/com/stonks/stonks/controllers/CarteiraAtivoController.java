package br.com.stonks.stonks.controllers;

import br.com.stonks.stonks.EmailConfig;
import br.com.stonks.stonks.models.Carteira;
import br.com.stonks.stonks.models.CarteiraAtivo;
import br.com.stonks.stonks.models.Usuario;
import br.com.stonks.stonks.services.CarteiraAtivoService;
import br.com.stonks.stonks.services.CarteiraService;
import br.com.stonks.stonks.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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
    private EmailConfig emailConfig;

    @GetMapping(value = "/carteiraativo/relatorio")
    public String imprimirRelatorio(Model model){

        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.usuarioPorEmail(principal.getUsername());
        Carteira carteira = carteiraService.carteiraByUsuario(usuario);

        List<CarteiraAtivo> ativos = carteiraAtivoService.findByAtivosCarteira(carteira.getId());

        model.addAttribute("ativosCarteira", ativos);
        model.addAttribute("usuario", usuario);

        return "dashboard/imprimirRelatorio";
    }
    
    @GetMapping(value = "/carteiraativo/relatorio/enviar")
    public String enviarRelatorio(Model model){
    	ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dashboard/imprimirRelatorio");
        
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario usuario = usuarioService.usuarioPorEmail(principal.getUsername());
        Carteira carteira = carteiraService.carteiraByUsuario(usuario);

        List<CarteiraAtivo> ativos = carteiraAtivoService.findByAtivosCarteira(carteira.getId());
        
        String body = "<h2>Seu relatório Stonks</h2> <br/>";
        
        body += "<table>"
				+ "<tr>"
					+ "<th> Ativo </th>"
					+ "<th> Valor </th>"
					+ "<th> Quantidade </th>"
					+ "<th> Data da Transação </th>"
				+ "</tr>";
        
        for (CarteiraAtivo ca : ativos) {
        	body += "<tr> <th>" + ca.getAtivo().getCodigo() + "</th>" 
        			+ "<th>" + ca.getValor() + "</th>"
        			+ "<th>" + ca.getQuantidade() + "</th>"
        			+ "<th>" + ca.getDataTransacao() + "</th> </tr>";
        }
        
        body += "</table>";

        model.addAttribute("ativosCarteira", ativos);
        model.addAttribute("usuario", usuario);
                
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailConfig.getHost());
        mailSender.setPort(emailConfig.getPort());
        mailSender.setUsername(emailConfig.getUsername());
        mailSender.setPassword(emailConfig.getPassword());
        
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        
        try {
			helper.setFrom("no-reply@stonks.com"); 
			helper.setTo(usuario.getEmail());
			helper.setSubject("Relatório do Stonks");
			helper.setText(body, true);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        try {
        	mailSender.send(mimeMessage);
        	modelAndView.addObject("successMessage", "Relatório enviado por email com sucesso.");
        } catch (MailException e) {
        	System.out.print(e.getMessage());
        	modelAndView.addObject("failMessage", "Relatório não pode ser enviado.");
        }
        
        return "dashboard/imprimirRelatorio";
    }
    
}
