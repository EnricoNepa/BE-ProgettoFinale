package com.epicenergy.service;

import java.time.LocalDate;

import com.epicenergy.model.DTO.request.ClienteRequestDTO;
import com.epicenergy.model.DTO.response.ClienteResponseDTO;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


public interface ClienteService {

	//CRUD BASE
	Page<ClienteResponseDTO> getAllClienti();
	ClienteResponseDTO createCliente(ClienteRequestDTO clienteRequest);
	ClienteResponseDTO updateCliente(ClienteRequestDTO clienteRequest, Long id);
	void deleteCliente(Long id);
	
	//CRUD ORDINAMENTO
	Page<ClienteResponseDTO> orderClientiByNome();
	Page<ClienteResponseDTO> orderClientiByFatturatoAnnuale();
	Page<ClienteResponseDTO> orderClientiByDataInserimento();
	Page<ClienteResponseDTO> orderClientiByDataUltimoContatto();
	Page<ClienteResponseDTO> orderClientiByProvinciaSedeLegale();
	

	//CRUD FILTRAGGIO
	ClienteResponseDTO filterClientiByNome(String nome);
	Page<ClienteResponseDTO> filterClientiByFatturatoAnnuale(Long fatturato1, Long fatturato2);
	Page<ClienteResponseDTO> filterClientiByDataInserimento(LocalDate data);
	Page<ClienteResponseDTO> filterClientiByDataUltimoContatto(LocalDate data);
	Page<ClienteResponseDTO> filterClientiByPartialNome(String nome);


}
