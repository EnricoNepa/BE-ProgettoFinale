package com.epicenergy.model.DTO.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY) //esclude dal JSON tutti i valori nulli
public class ProvinciaResponseDTO {
	
	private String nome;
	private String sigla;
	private String regione;

}
