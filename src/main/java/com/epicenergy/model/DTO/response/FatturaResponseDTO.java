package com.epicenergy.model.DTO.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.epicenergy.model.Cliente;
import com.epicenergy.model.Stato;
import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY) //esclude dal JSON tutti i valori nulli
public class FatturaResponseDTO {
	
	private Integer anno;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	private BigDecimal importo;
	private Integer numero;
	private StatoResponseDTO stato;
	private String clienteRagioneSociale;

}
