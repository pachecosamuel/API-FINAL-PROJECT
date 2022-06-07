package com.residencia.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.ClienteDTO;
import com.residencia.ecommerce.dto.EnderecoDTO;
import com.residencia.ecommerce.entity.Cliente;
import com.residencia.ecommerce.exception.AlreadyExistsException;
import com.residencia.ecommerce.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EnderecoService enderecoService;

	public List<ClienteDTO> findAllCliente() {
		List<ClienteDTO> listClientesDTO = new ArrayList<ClienteDTO>();

		for (Cliente clienteEntity : clienteRepository.findAll()) {
			listClientesDTO.add(converterEntidadeParaDto(clienteEntity));
		}

		return listClientesDTO;
	}

	public ClienteDTO findClienteById(Integer id) {
		return clienteRepository.findById(id).isPresent() ? converterEntidadeParaDto(clienteRepository.findById(id).get()) : null;
	}

	public ClienteDTO saveCliente(ClienteDTO clienteDTO) {
		for (ClienteDTO clienteExistente : findAllCliente()) {
			if (clienteExistente.getCpf() == clienteDTO.getCpf()) {
				throw new AlreadyExistsException("Já existe um Cliente cadastrado com o CPF: " + clienteDTO.getCpf());
			}

			if (clienteExistente.getEmail() == clienteDTO.getEmail()) {
				throw new AlreadyExistsException("Já existe um Cliente cadastrado com o E-mail: " + clienteDTO.getEmail());
			}
		}

		Cliente clienteSalvo = clienteRepository.save(convertDTOToEntidade(clienteDTO));
		
		return findClienteById(clienteSalvo.getIdCliente());
	}

	public ClienteDTO updateCliente(ClienteDTO clienteDTO, Integer id) {
		if (clienteDTO.getCpf() != null) {
			for (ClienteDTO clienteExistente : findAllCliente()) {
				if (clienteExistente.getCpf() == clienteDTO.getCpf()) {
					throw new AlreadyExistsException("Já existe um Cliente cadastrado com o CPF: " + clienteDTO.getCpf());
				}
	
				if (clienteExistente.getEmail() == clienteDTO.getEmail()) {
					throw new AlreadyExistsException("Já existe um Cliente cadastrado com o E-mail: " + clienteDTO.getEmail());
				}
			}
		}
		
		clienteDTO.setIdCliente(id);
		
		ClienteDTO clienteAntigoDTO = findClienteById(id);
		
		if (clienteDTO.getCepEndereco() == null && clienteDTO.getNumeroEndereco() == null) {
			clienteDTO.setCepEndereco(clienteAntigoDTO.getEnderecoDTO().getCep());
			clienteDTO.setNumeroEndereco(clienteAntigoDTO.getEnderecoDTO().getNumeroEndereco());
		}
		
		if (clienteDTO.getEmail() == null) {
			clienteDTO.setEmail(clienteAntigoDTO.getEmail());
		}
		
		if (clienteDTO.getNomeCompleto() == null) {
			clienteDTO.setNomeCompleto(clienteAntigoDTO.getNomeCompleto());
		}
		
		if (clienteDTO.getCpf() == null) {
			clienteDTO.setCpf(clienteAntigoDTO.getCpf());
		}

		if (clienteDTO.getComplemento() == null) {
			if (clienteAntigoDTO.getComplemento() != null) {
				clienteDTO.setComplemento(clienteAntigoDTO.getComplemento());
			}
		}

		if (clienteDTO.getDataNascimento() == null) {
			clienteDTO.setDataNascimento(clienteAntigoDTO.getDataNascimento());
		}

		if (clienteDTO.getTelefone() == null) {
			clienteDTO.setTelefone(clienteAntigoDTO.getTelefone());
		}

		if (clienteDTO.getTelefone() == null) {
			clienteDTO.setTelefone(clienteAntigoDTO.getTelefone());
		}
		
		Cliente clienteSalvo = clienteRepository.save(convertDTOToEntidade(clienteDTO));
		
		return findClienteById(clienteSalvo.getIdCliente());
	}

	public void deleteClienteById(Integer id) {
		clienteRepository.deleteById(id);
	}
	
	public Cliente convertDTOToEntidade(ClienteDTO clienteDTO){
		Cliente cliente = new Cliente();
		cliente.setIdCliente(clienteDTO.getIdCliente());
		cliente.setCpf(clienteDTO.getCpf());
		cliente.setDataNascimento(clienteDTO.getDataNascimento());
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setNomeCompleto(clienteDTO.getNomeCompleto());
		cliente.setTelefone(clienteDTO.getTelefone());

		if (enderecoService.findEnderecoByCep(clienteDTO.getCepEndereco()).isEmpty()) {
			EnderecoDTO newEnderecoDTO = enderecoService.saveEnderecoViaCEP(clienteDTO.getCepEndereco(), clienteDTO.getNumeroEndereco());
			if (clienteDTO.getComplemento() != null) {
				newEnderecoDTO.setComplemento(clienteDTO.getComplemento());
			}
			
			cliente.setEndereco(enderecoService.convertDTOToEntidade(newEnderecoDTO));
		} else {
			for (EnderecoDTO enderecoDTO : enderecoService.findEnderecoByCep(clienteDTO.getCepEndereco())) {
				if (enderecoDTO.getNumeroEndereco() == clienteDTO.getNumeroEndereco()) {
					if (clienteDTO.getComplemento() != null) {
						enderecoDTO.setComplemento(clienteDTO.getComplemento());
					} else {
						EnderecoDTO newEnderecoDTO = enderecoService.saveEnderecoViaCEP(clienteDTO.getCepEndereco(), clienteDTO.getNumeroEndereco());
						if (clienteDTO.getComplemento() != null) {
							newEnderecoDTO.setComplemento(clienteDTO.getComplemento());
						}
					}
					
					cliente.setEndereco(enderecoService.convertDTOToEntidade(enderecoDTO));
				}
			}
		}
		
		return cliente;
	}
		
	public ClienteDTO converterEntidadeParaDto(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setIdCliente(cliente.getIdCliente());
		clienteDTO.setCpf(cliente.getCpf());
		clienteDTO.setDataNascimento(cliente.getDataNascimento());
		clienteDTO.setEmail(cliente.getEmail());
		clienteDTO.setNomeCompleto(cliente.getNomeCompleto());
		clienteDTO.setTelefone(cliente.getTelefone());
		
		if (cliente.getEndereco() != null) {
			clienteDTO.setEnderecoDTO(enderecoService.findEnderecoById(cliente.getEndereco().getIdEndereco()));
		}
		
		return clienteDTO;
	}

}
