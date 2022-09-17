package com.epicenergy.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.epicenergy.exception.EpicEnergyException;
import com.epicenergy.model.Cliente;
import com.epicenergy.model.Fattura;
import com.epicenergy.model.DTO.request.FatturaRequestDTO;
import com.epicenergy.model.DTO.response.FatturaResponseDTO;
import com.epicenergy.repository.ClienteRepository;
import com.epicenergy.repository.FatturaRepository;
import com.epicenergy.repository.StatoRepository;
import com.epicenergy.service.FatturaService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class FatturaServiceImpl implements FatturaService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private FatturaRepository fatturaRepository;
	
	@Autowired
	private StatoRepository statoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public Page<FatturaResponseDTO> getAllFatture() {
		List<Fattura> fatture = fatturaRepository.findAll();
		List<FatturaResponseDTO> response = fatture.stream().map(fattura -> modelMapper.map(fattura, FatturaResponseDTO.class))
                .collect(Collectors.toList());
		return new PageImpl<>(response);
	}

	@Override
	public FatturaResponseDTO updateFattura(FatturaRequestDTO fatturaRequest, Long id) {
		if(!fatturaRepository.existsById(id)) {
			throw new EpicEnergyException("Fattura inesistente");
		}
		if(!clienteRepository.existsByRagioneSocialeIgnoreCase(fatturaRequest.getNomeCliente())) {
			throw new EpicEnergyException("Cliente inesistente");
		}
		Fattura fattura = modelMapper.map(fatturaRequest, Fattura.class);
		if(!statoRepository.existsByNomeIgnoreCase(fattura.getStato().getNome())) {
			throw new EpicEnergyException("Stato inesistente");
		}
		Cliente cliente = clienteRepository.findByRagioneSocialeIgnoreCase(fatturaRequest.getNomeCliente()).get();
		fattura.setId(id);
		fattura.setCliente(cliente);
		fattura.setStato(statoRepository.findByNomeIgnoreCase(fatturaRequest.getNomeStato()).get());
		return modelMapper.map(fatturaRepository.save(fattura), FatturaResponseDTO.class);
	}

	@Override
	public FatturaResponseDTO createFattura(FatturaRequestDTO fatturaRequest) {
		
		if(!clienteRepository.existsByRagioneSocialeIgnoreCase(fatturaRequest.getNomeCliente())) {
			throw new EpicEnergyException("Cliente inesistente");
		}
		
		Fattura fattura = modelMapper.map(fatturaRequest, Fattura.class);
		
		
		if(!statoRepository.existsByNomeIgnoreCase(fattura.getStato().getNome())) {
			throw new EpicEnergyException("Stato inesistente");
		}
		
		Cliente cliente = clienteRepository.findByRagioneSocialeIgnoreCase(fatturaRequest.getNomeCliente()).get();
		fattura.setCliente(cliente);
		fattura.setStato(statoRepository.findByNomeIgnoreCase(fatturaRequest.getNomeStato()).get());
		
		return modelMapper.map(fatturaRepository.save(fattura), FatturaResponseDTO.class);		
	}

	@Override
	public void deleteFattura(Long id) {
		if (!fatturaRepository.existsById(id)) {
			throw new EpicEnergyException("Fattura inesistente!");
		}
		fatturaRepository.deleteById(id);
	}

	@Override
	public Page<FatturaResponseDTO> filterFattureByCliente(String nome) {
		List<Fattura> fatture = fatturaRepository.findByClienteRagioneSocialeIgnoreCase(nome);
		List<FatturaResponseDTO> response = fatture.stream().map(fattura -> modelMapper.map(fattura, FatturaResponseDTO.class))
                .collect(Collectors.toList());
		return new PageImpl<>(response);
	}

	@Override
	public Page<FatturaResponseDTO> filterFattureByStato(String nome) {
		List<Fattura> fatture = fatturaRepository.findByStatoNomeIgnoreCase(nome);
		List<FatturaResponseDTO> response = fatture.stream().map(fattura -> modelMapper.map(fattura, FatturaResponseDTO.class))
                .collect(Collectors.toList());
		return new PageImpl<>(response);
	}

	@Override
	public Page<FatturaResponseDTO> filterFattureByData(LocalDate data) {
		List<Fattura> fatture = fatturaRepository.findByData(data);
		List<FatturaResponseDTO> response = fatture.stream().map(fattura -> modelMapper.map(fattura, FatturaResponseDTO.class))
                .collect(Collectors.toList());
		return new PageImpl<>(response);
	}

	@Override
	public Page<FatturaResponseDTO> filterFattureByAnno(Integer anno) {
		List<Fattura> fatture = fatturaRepository.findByAnno(anno);
		List<FatturaResponseDTO> response = fatture.stream().map(fattura -> modelMapper.map(fattura, FatturaResponseDTO.class))
                .collect(Collectors.toList());
		return new PageImpl<>(response);
	}

	@Override
	public Page<FatturaResponseDTO> filterFattureByRange(BigDecimal importo1, BigDecimal importo2) {
		List<Fattura> fatture = fatturaRepository.findByImportoBetween(importo1, importo2);
		List<FatturaResponseDTO> response = fatture.stream().map(fattura -> modelMapper.map(fattura, FatturaResponseDTO.class))
                .collect(Collectors.toList());
		return new PageImpl<>(response);
	}

}
