package com.epicenergy.repository;

import java.util.Optional;

import com.epicenergy.model.Comune;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComuneRepository extends JpaRepository<Comune, Long>{

	Optional<Comune> findByNomeComuneIgnoreCase(String nome);
}
