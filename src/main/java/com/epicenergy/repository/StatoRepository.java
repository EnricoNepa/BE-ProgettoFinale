package com.epicenergy.repository;

import java.util.Optional;

import com.epicenergy.model.Stato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatoRepository extends JpaRepository<Stato, Long>{

	Boolean existsByNomeIgnoreCase(String nome);
	Optional<Stato> findByNomeIgnoreCase(String nome);
	
}
