package com.residencia.ecommerce.service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.residencia.ecommerce.entity.Pedido;

@Service
public class EmailService {
	@Autowired
	JavaMailSender mailSender;

	@Autowired
	TemplateEngine templateEngine;

	@Value("${spring.mail.host}")
	private String mailHost;

	@Value("${spring.mail.port}")
	private String mailPort;
//blabla
	@Value("${spring.mail.username}")
	private String mailUserName;

	@Value("${spring.mail.password}")
	private String mailPassword;

	@Value("${spring.mail.default}")
	private String mailDefault;

	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendEmailText(String recipient, String subject, String message) {
		SimpleMailMessage smm = new SimpleMailMessage();

		smm.setTo(recipient);
		smm.setSubject(subject);
		smm.setText(message);

		smm.setFrom(mailDefault);

		mailSender.send(smm);
	}

	public void sendEmailHTML(String recipient, String subject, Pedido pedido) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

		helper.setText(generateEmailHTML(pedido), true);
		helper.setTo(recipient);
		helper.setSubject(subject);
		helper.setFrom(mailDefault);

		mailSender.send(mimeMessage);
	}
	// Tem que testar!!! -- Mateus
	private String generateEmailHTML(Pedido pedido) {
		Map<String, Object> variables = new HashMap<>();
		variables.put("idPedido", pedido.getIdPedido());
		variables.put("dataPedido", pedido.getDataPedido());
		variables.put("dataEntrega", pedido.getDataEntrega());
		variables.put("dataEnvio", pedido.getDataEnvio());
		variables.put("idCliente", pedido.getCliente().getIdCliente());
		variables.put("clienteNome", pedido.getCliente().getNomeCompleto());
		variables.put("listaDeItems", pedido.getItemPedidoList());

		String status;
		if (pedido.getStatus() == true) {
			status = "ATIVO";
		} else {
			status = "INATIVO";
		}

		variables.put("status", status);

		final String templateFileName = "pedido";
		String output = this.templateEngine.process(templateFileName, new Context(Locale.getDefault(), variables));

		return output;
	}
}
