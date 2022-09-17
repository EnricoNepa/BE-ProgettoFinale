package com.epicenergy.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class Fattura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer anno;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	private BigDecimal importo;
	private Integer numero;
	@ManyToOne
	private Stato stato;
	@ManyToOne
	@JsonIgnoreProperties({ "fatture" })
	private Cliente cliente;
	
}
