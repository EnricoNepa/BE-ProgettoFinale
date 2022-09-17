package com.epicenergy.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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
	@ManyToOne
	private Tipo tipo;
	@ManyToOne(cascade = CascadeType.ALL)
	private Indirizzo sedeLegale;
	@ManyToOne(cascade = CascadeType.ALL)
	private Indirizzo sedeOperativa;
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE)
	private List<Fattura> fatture;
}
