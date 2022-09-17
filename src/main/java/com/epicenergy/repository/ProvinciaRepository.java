package com.epicenergy.repository;

import java.util.Optional;

import com.epicenergy.model.Provincia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long>{

	Optional<Provincia> findByNomeIgnoreCase(String nome);
	Optional<Provincia> findBySiglaIgnoreCase(String sigla);
}
