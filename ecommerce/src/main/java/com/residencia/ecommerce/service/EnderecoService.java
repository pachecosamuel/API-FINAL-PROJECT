package com.residencia.ecommerce.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.residencia.ecommerce.dto.CepDTO;
import com.residencia.ecommerce.dto.EnderecoDTO;
import com.residencia.ecommerce.entity.Endereco;
import com.residencia.ecommerce.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;

	public List<EnderecoDTO> findAllEndereco() {
		List<Endereco> listEnderecoEntity = enderecoRepository.findAll();
		List<EnderecoDTO> listEnderecoDTO = new ArrayList<>();

		for (Endereco endereco : listEnderecoEntity) {
			listEnderecoDTO.add(converterEntidadeParaDto(endereco));
		}

		return listEnderecoDTO;
	}

	public EnderecoDTO findEnderecoById(Integer id) {
		return enderecoRepository.findById(id).isPresent()
				? converterEntidadeParaDto(enderecoRepository.findById(id).get())
				: null;
	}

	public List<EnderecoDTO> findEnderecoByCep(String cep) {
		if (enderecoRepository.findByCep(cep) == null) {
			return null;
		} else {
			List<EnderecoDTO> enderecoDTOlist = new ArrayList<EnderecoDTO>();
			for (Endereco endereco : enderecoRepository.findByCep(cep)) {
				enderecoDTOlist.add(converterEntidadeParaDto(endereco));
			}
			return enderecoDTOlist;
		}
	}

	public EnderecoDTO saveEndereco(EnderecoDTO enderecoDTO) {
		return converterEntidadeParaDto(enderecoRepository.save(convertDTOToEntidade(enderecoDTO)));
	}

	public EnderecoDTO saveEnderecoViaCEP(String cep, Integer numero) {
		CepDTO newCepDTO = getCepDTOFromExternal(cep);
		newCepDTO.setNumeroEndereco(numero);

		return converterEntidadeParaDto(enderecoRepository.save(convertDTOToEntidade(CepDTOtoEnderecoDTO(newCepDTO))));
	}

	public EnderecoDTO updateEnderecoViaCEP(Integer id, String cep, Integer numero) {
		EnderecoDTO newEnderecoDTO = CepDTOtoEnderecoDTO(getCepDTOFromExternal(cep));
		newEnderecoDTO.setIdEndereco(id);
		newEnderecoDTO.setNumeroEndereco(numero);

		return converterEntidadeParaDto(enderecoRepository.save(convertDTOToEntidade(newEnderecoDTO)));
	}

	public void deleteEnderecoById(Integer id) {
		enderecoRepository.deleteById(id);
	}

	public Endereco convertDTOToEntidade(EnderecoDTO enderecoDTO) {
		Endereco endereco = new Endereco();
		endereco.setIdEndereco(enderecoDTO.getIdEndereco());
		endereco.setBairro(enderecoDTO.getBairro());
		endereco.setCep(enderecoDTO.getCep());
		endereco.setCidade(enderecoDTO.getCidade());
		endereco.setComplemento(enderecoDTO.getComplemento());
		endereco.setNumeroEndereco(enderecoDTO.getNumeroEndereco());
		endereco.setRua(enderecoDTO.getRua());
		endereco.setUf(enderecoDTO.getUf());

		return endereco;
	}

	public EnderecoDTO converterEntidadeParaDto(Endereco endereco) {
		EnderecoDTO enderecoDTO = new EnderecoDTO();
		enderecoDTO.setIdEndereco(endereco.getIdEndereco());
		enderecoDTO.setBairro(endereco.getBairro());
		enderecoDTO.setCep(endereco.getCep());
		enderecoDTO.setCidade(endereco.getCidade());
		enderecoDTO.setComplemento(endereco.getComplemento());
		enderecoDTO.setNumeroEndereco(endereco.getNumeroEndereco());
		enderecoDTO.setRua(endereco.getRua());
		enderecoDTO.setUf(endereco.getUf());
		return enderecoDTO;
	}

	public CepDTO getCepDTOFromExternal(String cep) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://viacep.com.br/ws/{cep}/json/";
		Map<String, String> params = new HashMap<String, String>();
		params.put("cep", cep);

		return restTemplate.getForObject(uri, CepDTO.class, params);
	}

	public EnderecoDTO CepDTOtoEnderecoDTO(CepDTO cepDTO) {
		EnderecoDTO enderecoDTO = new EnderecoDTO();
		enderecoDTO.setBairro(cepDTO.getBairro());
		enderecoDTO.setCep(cepDTO.getCep());
		enderecoDTO.setCidade(cepDTO.getLocalidade());
		enderecoDTO.setComplemento(cepDTO.getComplemento());
		enderecoDTO.setNumeroEndereco(cepDTO.getNumeroEndereco());
		enderecoDTO.setRua(cepDTO.getLogradouro());
		enderecoDTO.setUf(cepDTO.getUf());

		return enderecoDTO;
	}
}
