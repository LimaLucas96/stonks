package br.com.stonks.stonks.services;

import br.com.stonks.stonks.EmailConfig;
import br.com.stonks.stonks.models.CarteiraAtivo;
import br.com.stonks.stonks.models.Usuario;
import br.ufrn.imd.stonks.framework.framework.model.DespesaAtivo;
import br.ufrn.imd.stonks.framework.framework.service.EmailServiceAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailService extends EmailServiceAbstract<CarteiraAtivo> {

    @Autowired
    private EmailConfig emailConfig;

    public Boolean enviarEmail(String mensagemEmail, Usuario usuario) {
        try {
            super.enviarEmail(mensagemEmail, usuario, "no-reply@stonks.com", "Relatório do Stonks");
            return true;
        } catch (MailException e) {
            System.out.print(e.getMessage());
        }

        return false;
    }

    @Override
    public String montarCorpoEmail(List<CarteiraAtivo> entities) {
        StringBuilder body = new StringBuilder("<h2>Seu relatório Stonks</h2> <br/>");

        body.append("<table><tr><th> Ativo </th><th> Valor </th><th> Quantidade </th><th> Data da Transação </th></tr>");

        for (DespesaAtivo ca : entities) {
            body.append("<tr> <th>")
                    .append(ca.getAtivoAbstract().getCodigo())
                    .append("</th>").append("<th>")
                    .append(ca.getValor())
                    .append("</th>").append("<th>")
                    .append(ca.getQuantidade())
                    .append("</th>").append("<th>")
                    .append(ca.getDataTransacao())
                    .append("</th> </tr>");
        }

        body.append("</table>");

        return body.toString();
    }

    @Override
    public JavaMailSenderImpl configurarHost() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailConfig.getHost());
        mailSender.setPort(emailConfig.getPort());
        mailSender.setUsername(emailConfig.getUsername());
        mailSender.setPassword(emailConfig.getPassword());

        return mailSender;
    }
}
