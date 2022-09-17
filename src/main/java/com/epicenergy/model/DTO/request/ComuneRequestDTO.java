package com.epicenergy.model.DTO.request;

import com.epicenergy.model.Provincia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComuneRequestDTO {


	private String nomeComune;
	private ProvinciaRequestDTO provincia;
}
