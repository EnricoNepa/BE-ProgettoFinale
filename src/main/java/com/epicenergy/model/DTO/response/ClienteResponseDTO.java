package com.epicenergy.model.DTO.response;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.epicenergy.model.Fattura;
import com.epicenergy.model.Indirizzo;
import com.epicenergy.model.Tipo;
import com.fasterxml.jackson.annotation.JsonInclude;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY) //esclude dal JSON tutti i valori nulli
public class ClienteResponseDTO implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String ragioneSociale;
	private Long partitaIVA;
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataInserimento;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataUltimoContatto;
	private Long fatturatoAnnuale;
	private String pec;
	private Long telefono;
	private String emailContatto;
	private String nomeContatto;
	private String cognomeContatto;
	private Long telefonoContatto;
	private TipoResponseDTO tipo;
	private IndirizzoResponseDTO sedeLegale;
	private IndirizzoResponseDTO sedeOperativa;
	
}
