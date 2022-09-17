package com.epicenergy.controller.rest;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.epicenergy.model.DTO.request.FatturaRequestDTO;
import com.epicenergy.model.DTO.response.FatturaResponseDTO;
import com.epicenergy.service.FatturaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Fattura")
public class FatturaController {

	@Autowired
	private FatturaService fatturaService;
	
	
	//CRUD BASE
	@GetMapping(path = "/fattura/lista")
	@Operation(summary="Lista di tutte le fatture")
	public ResponseEntity<Page<FatturaResponseDTO>> getAllFatture(){
		return ResponseEntity.ok(fatturaService.getAllFatture());
	}
	
	@PutMapping(path = "/fattura/id/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<FatturaResponseDTO> updateFattura(@PathVariable Long id, @RequestBody FatturaRequestDTO fattura){
		return ResponseEntity.ok(fatturaService.updateFattura(fattura, id));
	}
	
	@PostMapping(path = "/fattura/crea")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary="Crea una nuova fattura")
	public ResponseEntity<FatturaResponseDTO> createFattura(@RequestBody FatturaRequestDTO fattura){
		return new ResponseEntity<>(fatturaService.createFattura(fattura), HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/fattura/elimina/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary="Cancella una fattura")
	public ResponseEntity<String> deleteFattura(Pageable pageable, @PathVariable Long id){
		fatturaService.deleteFattura(id);
		return new ResponseEntity<>("Fattura eliminata!",HttpStatus.NO_CONTENT);
	}
	
	//CRUD FILTRAGGIO
	@GetMapping(path = "/fattura/filter/cliente/{nome}")
	@Operation(summary="Filtra fatture per Ragione Sociale Cliente")
	public ResponseEntity<Page<FatturaResponseDTO>> filterFattureByCliente(@PathVariable String nome){
		return ResponseEntity.ok(fatturaService.filterFattureByCliente(nome));
	}
	
	@GetMapping(path = "/fattura/filter/stato/{nome}")
	@Operation(summary="Filtra fatture per Stato")
	public ResponseEntity<Page<FatturaResponseDTO>> filterFattureByStato(@PathVariable String nome){
		return ResponseEntity.ok(fatturaService.filterFattureByStato(nome));
	}
	
	@GetMapping(path = "/fattura/filter/data/{data}")
	@Operation(summary="Filtra fatture per Data")
	public ResponseEntity<Page<FatturaResponseDTO>> filterFattureByData(@PathVariable @DateTimeFormat(iso = ISO.DATE) LocalDate data){
		return ResponseEntity.ok(fatturaService.filterFattureByData(data));
	}
	
	@GetMapping(path = "/fattura/filter/anno/{anno}")
	@Operation(summary="Filtra fatture per Anno")
	public ResponseEntity<Page<FatturaResponseDTO>> filterFattureByAnno(@PathVariable Integer anno){
		return ResponseEntity.ok(fatturaService.filterFattureByAnno(anno));
	}
	
	@GetMapping(path = "/fattura/filter/importo/{importo1}/{importo2}")
	@Operation(summary="Filtra fatture per Range Importo")
	public ResponseEntity<Page<FatturaResponseDTO>> filterFattureByRange(@PathVariable BigDecimal importo1,
			@PathVariable BigDecimal importo2){
		return ResponseEntity.ok(fatturaService.filterFattureByRange(importo1, importo2));
	}
}
