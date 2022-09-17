package com.epicenergy.model.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndirizzoRequestDTO {

	private String via;
	private int civico;
	private String localita;
	private String cap;
	private String nomeComune;
}
