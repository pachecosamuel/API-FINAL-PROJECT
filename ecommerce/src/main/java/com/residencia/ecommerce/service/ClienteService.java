package com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.entity.Cliente;
import com.residencia.ecommerce.exception.AlreadyExistsException;
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
		for (Cliente clienteExistente : findAllCliente()) {
			if (clienteExistente.getCpf() == cliente.getCpf()) {
				throw new AlreadyExistsException("Já existe um Cliente cadastrado com o CPF: " + cliente.getCpf());
			}

			if (clienteExistente.getEmail() == cliente.getEmail()) {
				throw new AlreadyExistsException("Já existe um Cliente cadastrado com o E-mail: " + cliente.getEmail());
			}
		}
		
		return clienteRepository.save(cliente);
	}

	public Cliente updateCliente(Cliente cliente) {
		if (cliente.getCpf() != null) {
			for (Cliente clienteExistente : findAllCliente()) {
				if (clienteExistente.getCpf() == cliente.getCpf()) {
					throw new AlreadyExistsException("Já existe um Cliente cadastrado com o CPF: " + cliente.getCpf());
				}
	
				if (clienteExistente.getEmail() == cliente.getEmail()) {
					throw new AlreadyExistsException("Já existe um Cliente cadastrado com o E-mail: " + cliente.getEmail());
				}
			}
		}
		
		return clienteRepository.save(cliente);
	}

	public void deleteClienteById(Integer id) {
		clienteRepository.deleteById(id);
	}

}
