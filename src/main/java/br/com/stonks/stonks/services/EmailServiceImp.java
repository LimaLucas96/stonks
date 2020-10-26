package br.com.stonks.stonks.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

import br.com.stonks.stonks.exception.EmailInvalidoException;

import com.sendgrid.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EmailServiceImp implements EmailService {
    
	@Value("${app.sendgrid.templateId}")
	private String templateId;
	
	@Autowired
    SendGrid sendGrid;

	@Override
	public String envairEmail(String email) throws EmailInvalidoException {
		try {
			
			Mail mail =  prepareMail(email);
			
			Request request = new Request();
			request.setMethod(Method.POST);
			request.setEndpoint("email/enviar");
			request.setBody(mail.build());
			
			Response response = sendGrid.api(request);
			
			if (response != null) {
				System.out.println(response.getHeaders());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return email;
	}
	
	public Mail prepareMail(String email) {
		Mail mail = new Mail();	
		
		Email fromEmail = new Email();
		fromEmail.setEmail("abmaeld@gmail.com");
		
		Email to = new Email();
		to.setEmail(email);
		
		Personalization personalization = new Personalization();
		personalization.addTo(to);
		
		mail.setTemplateId(templateId);
		
		return mail;

	}

}
