package com.epicenergy.repository;

import java.util.Optional;

import com.epicenergy.model.Tipo;
import com.epicenergy.model.TipoEnum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Long>{

	Boolean existsByTipo(TipoEnum tipoEnum);
	Optional<Tipo> findByTipo(TipoEnum tipo);
}
