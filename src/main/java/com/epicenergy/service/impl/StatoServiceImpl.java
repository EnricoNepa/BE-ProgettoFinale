package com.epicenergy.service.impl;

import com.epicenergy.exception.EpicEnergyException;
import com.epicenergy.model.Stato;
import com.epicenergy.model.DTO.request.StatoRequestDTO;
import com.epicenergy.model.DTO.response.StatoResponseDTO;
import com.epicenergy.repository.StatoRepository;
import com.epicenergy.service.StatoService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatoServiceImpl implements StatoService{

	@Autowired
	StatoRepository statoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public StatoResponseDTO createStato(StatoRequestDTO statoRequest) {
		Stato stato = statoRepository.save(modelMapper.map(statoRequest, Stato.class));
		return modelMapper.map(stato, StatoResponseDTO.class);
	}

	@Override
	public void deleteStato(Long id) {
		if (!statoRepository.existsById(id)) {
			throw new EpicEnergyException("Stato fattura inesistente!");
		}
		statoRepository.deleteById(id);
		
	}

}
