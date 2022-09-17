package com.epicenergy.service;

import com.epicenergy.model.DTO.response.ComuneResponseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


public interface ComuneService {

	//CRUD BASE
	Page<ComuneResponseDTO> getAllComuni();
	ComuneResponseDTO getComuneByNome(String nome);
}
