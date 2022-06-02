package com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.ClienteDTO;
import com.residencia.ecommerce.entity.Cliente;
import com.residencia.ecommerce.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	public List<Cliente> findAllCliente() {
		return clienteRepository.findAll();
	}

	public Cliente findClienteById(Integer id) {
		return clienteRepository.findById(id).isPresent() ? clienteRepository.findById(id).get() : null;
	}

	public Cliente saveCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente updateCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public void deleteClienteById(Integer id) {
		clienteRepository.deleteById(id);
	}
	
	private Cliente convertDTOToEntidade(ClienteDTO clienteDTO){
		Cliente cliente = new Cliente();
		cliente.setIdCliente(clienteDTO.getIdCliente());
		cliente.setCpf(clienteDTO.getCpf());
		cliente.setDataNascimento(clienteDTO.getDataNascimento());
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setNomeCompleto(clienteDTO.getNomeCompleto());
		cliente.setTelefone(clienteDTO.getTelefone());
		cliente.getEndereco().setIdEndereco(clienteDTO.getEnderecoDTO().getIdEndereco());
		
		
		return cliente;
	}
		
	private ClienteDTO converterEntidadeParaDto(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setIdCliente(cliente.getIdCliente());
		clienteDTO.setIdCliente(cliente.getIdCliente());
		clienteDTO.setCpf(cliente.getCpf());
		clienteDTO.setDataNascimento(cliente.getDataNascimento());
		clienteDTO.setEmail(cliente.getEmail());
		clienteDTO.setNomeCompleto(cliente.getNomeCompleto());
		clienteDTO.setTelefone(cliente.getTelefone());
		clienteDTO.getEnderecoDTO().setIdEndereco(cliente.getEndereco().getIdEndereco());
		return clienteDTO;
	}

}
