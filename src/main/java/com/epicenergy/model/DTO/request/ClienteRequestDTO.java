package com.epicenergy.model.DTO.request;

import java.io.Serializable;
import java.time.LocalDate;

import com.epicenergy.model.Tipo;
import com.epicenergy.model.TipoEnum;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequestDTO implements Serializable{
	
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
	private TipoEnum tipo;	
	private IndirizzoRequestDTO sedeLegale;	
	private IndirizzoRequestDTO sedeOperativa;

}
