package br.com.stonks.stonks.services;

import br.com.stonks.stonks.EmailConfig;
import br.com.stonks.stonks.models.CarteiraAtivo;
import br.com.stonks.stonks.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailServiceImp implements EmailService {

    @Autowired
    private EmailConfig emailConfig;

    @Override
    public String montarCorpoEmailRelatorio(List<CarteiraAtivo> ativos) {
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

        return body;
    }

    public Boolean enviarEmail(String mensagemEmail, Usuario usuario) {
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
            helper.setText(mensagemEmail, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        try {
            mailSender.send(mimeMessage);
            return true;
        } catch (MailException e) {
            System.out.print(e.getMessage());
        }

        return false;
    }
}
