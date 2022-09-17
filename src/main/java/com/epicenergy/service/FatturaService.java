package com.epicenergy.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.epicenergy.model.DTO.response.FatturaResponseDTO;
import com.epicenergy.model.DTO.request.FatturaRequestDTO;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


public interface FatturaService {

	//CRUD BASE
	Page<FatturaResponseDTO> getAllFatture();
	FatturaResponseDTO updateFattura(FatturaRequestDTO fatturaRequest, Long id);
	FatturaResponseDTO createFattura(FatturaRequestDTO fatturaRequest);
	void deleteFattura(Long id);

	//CRUD FILTRAGGIO
	Page<FatturaResponseDTO> filterFattureByCliente(String nome);
	Page<FatturaResponseDTO> filterFattureByStato(String nome);
	Page<FatturaResponseDTO> filterFattureByData(LocalDate data);
	Page<FatturaResponseDTO> filterFattureByAnno(Integer anno);
	Page<FatturaResponseDTO> filterFattureByRange(BigDecimal importo1, BigDecimal importo2);

}
