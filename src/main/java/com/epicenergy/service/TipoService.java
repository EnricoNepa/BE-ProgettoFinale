package com.epicenergy.service;

import com.epicenergy.model.DTO.response.TipoResponseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


public interface TipoService {

	Page<TipoResponseDTO> getAllTipi();
}
