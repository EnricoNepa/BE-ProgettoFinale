package com.epicenergy.model.DTO.response;

import java.util.List;

import com.epicenergy.model.Cliente;
import com.epicenergy.model.TipoEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY) //esclude dal JSON tutti i valori nulli
public class TipoResponseDTO {
	
	private TipoEnum tipo;

}
