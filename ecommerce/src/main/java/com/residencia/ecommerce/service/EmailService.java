package com.residencia.ecommerce.service;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.residencia.ecommerce.dto.ItemPedidoDTO;
import com.residencia.ecommerce.dto.PedidoDTO;

@Service
public class EmailService {
	@Autowired
	JavaMailSender mailSender;

	@Autowired
	SpringTemplateEngine springTemplateEngine;

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

	public void sendEmailHTML(String recipient, String subject, PedidoDTO pedidoDTO) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

		Context context = new Context();
		context.setVariables(mapEmail(pedidoDTO));

		String html = springTemplateEngine.process("pedido", context);

		helper.setTo(recipient);
		helper.setText(html, true);
		helper.setSubject(subject);
		helper.setFrom(mailDefault);

		mailSender.send(mimeMessage);
	}

	public Map<String, Object> mapEmail(PedidoDTO pedidoDTO) {
		Map<String, Object> variables = new HashMap<>();
		variables.put("idPedido", pedidoDTO.getIdPedido());
		variables.put("dataPedido", new SimpleDateFormat("dd-MM-yyyy").format(pedidoDTO.getDataPedido()));
		variables.put("dataEntrega", new SimpleDateFormat("dd-MM-yyyy").format(pedidoDTO.getDataEntrega()));
		variables.put("dataEnvio", new SimpleDateFormat("dd-MM-yyyy").format(pedidoDTO.getDataEnvio()));
		variables.put("idCliente", pedidoDTO.getClienteDTO().getIdCliente());
		variables.put("clienteNome", pedidoDTO.getClienteDTO().getNomeCompleto());
		variables.put("valorTotal", String.format("%.2f", pedidoDTO.getValorTotal()));

		//Endereço
		variables.put("uf", pedidoDTO.getClienteDTO().getEnderecoDTO().getUf());
		variables.put("cidade", pedidoDTO.getClienteDTO().getEnderecoDTO().getCidade());
		variables.put("bairro", pedidoDTO.getClienteDTO().getEnderecoDTO().getBairro());
		variables.put("rua", pedidoDTO.getClienteDTO().getEnderecoDTO().getRua());
		variables.put("numero", pedidoDTO.getClienteDTO().getEnderecoDTO().getNumeroEndereco());
		variables.put("complemento", pedidoDTO.getClienteDTO().getEnderecoDTO().getComplemento());

		List<Integer> listaIDsPedidos = new ArrayList<Integer>();
		for (ItemPedidoDTO itemPedidoDTO : pedidoDTO.getListItemPedidoDTO()) {
			listaIDsPedidos.add(itemPedidoDTO.getIdProduto());
		}

		variables.put("listaDeItems", listaIDsPedidos);

		return variables;
	}

}
