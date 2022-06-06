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

import com.residencia.ecommerce.dto.PedidoDTO;

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

	public void sendEmailHTML(String recipient, String subject, PedidoDTO pedidoDTO) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

		helper.setText(generateEmailHTML(pedidoDTO), true);
		helper.setTo(recipient);
		helper.setSubject(subject);
		helper.setFrom(mailDefault);

		mailSender.send(mimeMessage);
	}
	// Tem que testar!!! -- Mateus
	private String generateEmailHTML(PedidoDTO pedidoDTO) {
		Map<String, Object> variables = new HashMap<>();
		variables.put("idPedido", pedidoDTO.getIdPedido());
		variables.put("dataPedido", pedidoDTO.getDataPedido());
		variables.put("dataEntrega", pedidoDTO.getDataEntrega());
		variables.put("dataEnvio", pedidoDTO.getDataEnvio());
		variables.put("idCliente", pedidoDTO.getClienteDTO().getIdCliente());
		variables.put("clienteNome", pedidoDTO.getClienteDTO().getNomeCompleto());
		variables.put("listaDeItems", pedidoDTO.getListItemPedidoDTO());

		String status;
		if (pedidoDTO.getStatus() == true) {
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
