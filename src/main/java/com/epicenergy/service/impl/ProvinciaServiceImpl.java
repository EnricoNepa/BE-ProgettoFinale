package com.epicenergy.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.epicenergy.exception.EpicEnergyException;
import com.epicenergy.model.Cliente;
import com.epicenergy.model.Provincia;
import com.epicenergy.model.DTO.response.ClienteResponseDTO;
import com.epicenergy.model.DTO.response.ProvinciaResponseDTO;
import com.epicenergy.repository.ClienteRepository;
import com.epicenergy.repository.ProvinciaRepository;
import com.epicenergy.service.ProvinciaService;

import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaServiceImpl implements ProvinciaService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ProvinciaRepository provinciaRepository;

	@Override
	public Page<ProvinciaResponseDTO> getAllProvincia() {
		List<Provincia> province = provinciaRepository.findAll();
		List<ProvinciaResponseDTO> response = province.stream().map(provincia -> modelMapper.map(provincia, ProvinciaResponseDTO.class))
                .collect(Collectors.toList());
		return new PageImpl<>(response);
	}

	@Override
	public ProvinciaResponseDTO getProvinciaByNome(String nome) {
		Optional<Provincia> provincia = provinciaRepository.findByNomeIgnoreCase(nome);
		if(provincia.isEmpty()) {
			throw new EpicEnergyException("Provincia inesistente!");
		}
		return modelMapper.map(provincia, ProvinciaResponseDTO.class);
	}

	@Override
	public ProvinciaResponseDTO getProvinciaBySigla(String sigla) {
		Optional<Provincia> provincia = provinciaRepository.findBySiglaIgnoreCase(sigla);
		if(provincia.isEmpty()) {
			throw new EpicEnergyException("Provincia inesistente!");
		}
		return modelMapper.map(provincia, ProvinciaResponseDTO.class);
	}

}
