package com.epicenergy.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.epicenergy.exception.EpicEnergyException;
import com.epicenergy.model.Comune;
import com.epicenergy.model.DTO.response.ComuneResponseDTO;
import com.epicenergy.repository.ComuneRepository;
import com.epicenergy.service.ComuneService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class ComuneServiceImpl implements ComuneService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ComuneRepository comuneRepository;
	
	@Override
	public Page<ComuneResponseDTO> getAllComuni() {
		List<Comune> comuni = comuneRepository.findAll();
		List<ComuneResponseDTO> response = comuni.stream().map(comune -> modelMapper.map(comune, ComuneResponseDTO.class))
                .collect(Collectors.toList());
		return new PageImpl<>(response);
		
	}

	@Override
	public ComuneResponseDTO getComuneByNome(String nome) {
		Optional<Comune> comune = comuneRepository.findByNomeComuneIgnoreCase(nome);
		if(!comune.isPresent()) {
			throw new EpicEnergyException("Comune inesistente!");
		}
		return modelMapper.map(comune, ComuneResponseDTO.class);
	}

}
