package com.epicenergy.model.DTO.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.epicenergy.model.Stato;
import com.epicenergy.model.DTO.response.ClienteResponseDTO;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FatturaRequestDTO {

	private Integer anno;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	private BigDecimal importo;
	private Integer numero;
	private String nomeStato;
	private String nomeCliente;
}
