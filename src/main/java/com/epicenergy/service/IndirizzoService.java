package com.epicenergy.service;

import com.epicenergy.model.DTO.response.IndirizzoResponseDTO;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


public interface IndirizzoService {

	//CRUD BASE
	Page<IndirizzoResponseDTO> getAllIndirizzi();
	void deleteIndirizzo(Long id);
	
}
