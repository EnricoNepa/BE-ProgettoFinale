package com.epicenergy.model.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinciaRequestDTO {

	private String nome;
	private String sigla;
	private String regione;

}
