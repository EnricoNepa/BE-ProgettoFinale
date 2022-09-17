package com.epicenergy.repository;

import com.epicenergy.model.Indirizzo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndirizzoRepository extends JpaRepository<Indirizzo, Long>{

}
