package com.epicenergy.service;

import com.epicenergy.model.DTO.response.ProvinciaResponseDTO;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


public interface ProvinciaService {

	//CRUD BASE
	Page<ProvinciaResponseDTO> getAllProvincia();
	ProvinciaResponseDTO getProvinciaByNome(String nome);
	ProvinciaResponseDTO getProvinciaBySigla(String sigla);
}
