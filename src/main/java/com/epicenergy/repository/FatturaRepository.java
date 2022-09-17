package com.epicenergy.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.epicenergy.model.Fattura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Long>{

	List<Fattura> findByClienteRagioneSocialeIgnoreCase(String nome);
	List<Fattura> findByStatoNomeIgnoreCase(String nome);
	List<Fattura> findByData(LocalDate data);
	List<Fattura> findByAnno(Integer anno);
	List<Fattura> findByImportoBetween(BigDecimal importo1, BigDecimal importo2); 
	
}
