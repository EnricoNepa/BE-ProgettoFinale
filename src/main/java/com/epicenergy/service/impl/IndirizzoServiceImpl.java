package com.epicenergy.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.epicenergy.exception.EpicEnergyException;
import com.epicenergy.model.Indirizzo;
import com.epicenergy.model.DTO.request.IndirizzoRequestDTO;
import com.epicenergy.model.DTO.response.IndirizzoResponseDTO;
import com.epicenergy.repository.IndirizzoRepository;
import com.epicenergy.service.IndirizzoService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class IndirizzoServiceImpl implements IndirizzoService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IndirizzoRepository indirizzoRepository;
	
	@Override
	public Page<IndirizzoResponseDTO> getAllIndirizzi() {
		List<Indirizzo> indirizzi = indirizzoRepository.findAll();
		List<IndirizzoResponseDTO> response = indirizzi.stream().map(indirizzo -> modelMapper.map(indirizzo, IndirizzoResponseDTO.class))
                .collect(Collectors.toList());
		return new PageImpl<>(response);
	}

	@Override
	public void deleteIndirizzo(Long id) {
		if (!indirizzoRepository.existsById(id)) {
			throw new EpicEnergyException("Fattura inesistente!");
		}
		indirizzoRepository.deleteById(id);
		
	}

}
