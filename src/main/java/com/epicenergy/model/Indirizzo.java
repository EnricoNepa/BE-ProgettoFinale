package com.epicenergy.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Indirizzo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String via;
	private int civico;
	private String localita;
	private String cap;
	@ManyToOne
	private Comune comune;
	@OneToMany(mappedBy = "sedeLegale")
	private List<Cliente> clientiSedeLegale;
	@OneToMany(mappedBy = "sedeOperativa")
	private List<Cliente> clientiSedeOperativa;

	
}
