package com.epicenergy.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.epicenergy.model.Tipo;
import com.epicenergy.model.DTO.response.TipoResponseDTO;
import com.epicenergy.repository.TipoRepository;
import com.epicenergy.service.TipoService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class TipoServiceImpl implements TipoService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private TipoRepository tipoRepository;
	
	@Override
	public Page<TipoResponseDTO> getAllTipi() {
		List<Tipo> tipi = tipoRepository.findAll();
		List<TipoResponseDTO> response = tipi.stream().map(tipo -> modelMapper.map(tipo, TipoResponseDTO.class))
                .collect(Collectors.toList());
		return new PageImpl<>(response);
	}

}
