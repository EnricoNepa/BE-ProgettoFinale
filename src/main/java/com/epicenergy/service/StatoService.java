package com.epicenergy.service;

import com.epicenergy.model.DTO.request.StatoRequestDTO;
import com.epicenergy.model.DTO.response.StatoResponseDTO;

import org.springframework.stereotype.Service;


public interface StatoService {

	StatoResponseDTO createStato(StatoRequestDTO statoRequest);
	void deleteStato(Long id);
}
