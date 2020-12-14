package br.com.stonks.stonks;
import br.com.stonks.stonks.models.*;
import br.com.stonks.stonks.services.CarteiraService;
import br.com.stonks.stonks.services.RoleService;
import br.com.stonks.stonks.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@SpringBootApplication
public class StonksApplication implements CommandLineRunner {

	@Autowired
	RoleService roleService;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	BCryptPasswordEncoder encoder;

	@Autowired
	CarteiraService carteiraService;

	public static String EMAIL_ADMIN = "admin@stonks.com";
	public static String SENHA_ADMIN = "123456";

	public static void main(String[] args) {
		SpringApplication.run(StonksApplication.class, args);
	}

	public void run(String... var1) throws Exception {
		System.out.println("Iniciando configuração do banco.");

		Role siteUser = roleService.findByRole("SITE_USER");
		Role adminUser = roleService.findByRole("ADMIN_USER");
		Role superUser = roleService.findByRole("SUPER_USER");

		if(siteUser == null){
			siteUser = new Role("SITE_USER", "Permissao de acesso padrão do site");
			roleService.save(siteUser);
		}
		if(adminUser == null){
			adminUser = new Role("ADMIN_USER", "Permissao de acesso de administrador do site");
			roleService.save(adminUser);
		}
		if(superUser == null){
			superUser = new Role("SUPER_USER", "Permissao de acesso de super usuario do site");
			roleService.save(superUser);
		}

		Usuario usuarioAdmin = usuarioService.findByEmail(EMAIL_ADMIN);
		if (usuarioAdmin == null){
			usuarioAdmin = new Usuario("Admin", EMAIL_ADMIN, encoder.encode(SENHA_ADMIN),
					"000.000.000-00",true, new Date());
			usuarioService.save(usuarioAdmin);
			usuarioAdmin.setRoles(new HashSet<Role>(Arrays.asList(adminUser)));
			usuarioService.save(usuarioAdmin);
			Carteira carteira = new Carteira(usuarioAdmin);
			carteiraService.salvarCarteira(carteira);
		}

		System.out.println("Fim da configuração do banco.\n.\n.\n.");

		System.out.println("Login: "+EMAIL_ADMIN+"\nsenha: "+SENHA_ADMIN);

		System.out.println(".\n.\n.\n.\nAplicação Stonks iniciada -> http://localhost:8080/login");
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
