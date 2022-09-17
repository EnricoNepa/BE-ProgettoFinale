package com.epicenergy.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.epicenergy.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	//
	Boolean existsByRagioneSocialeIgnoreCase(String nome);
	
	//CRUD ORDINAMENTO
	List<Cliente> findAllByOrderByRagioneSociale();
	List<Cliente> findAllByOrderByFatturatoAnnuale();
	List<Cliente> findAllByOrderByDataInserimento();
	List<Cliente> findAllByOrderByDataUltimoContatto();
	List<Cliente> findAllByOrderBySedeLegale_Comune_NomeProvincia();
	
	//CRUD FILTRAGGIO
	Optional<Cliente> findByRagioneSocialeIgnoreCase(String nome);
	List<Cliente> findByFatturatoAnnualeBetween(Long fatturato1, Long fatturato2);
	List<Cliente> findByDataInserimento(LocalDate data);
	List<Cliente> findByDataUltimoContatto(LocalDate data);
	List<Cliente> findByRagioneSocialeContainingIgnoreCase(String nome); // Containing viene tradotto in "… WHERE x.nome LIKE ?1 (racchiude il valore tra %)"
	
	
}
