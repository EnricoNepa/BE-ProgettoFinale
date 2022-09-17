package com.epicenergy.controller.rest;

import com.epicenergy.model.DTO.response.IndirizzoResponseDTO;
import com.epicenergy.service.IndirizzoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Indirizzo")
public class IndirizzoController {

	@Autowired
	IndirizzoService indirizzoService;
	
	@GetMapping(path = "/indirizzo/lista")
	@Operation(summary="Lista di tutti gli indirizzi")
	public ResponseEntity<Page<IndirizzoResponseDTO>> getAllIndirizzi(){
		return ResponseEntity.ok(indirizzoService.getAllIndirizzi());
	}
	
	@DeleteMapping(path = "/indirizzo/delete/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary="Elimina un indirizzo")
	public ResponseEntity<String> deleteCliente(@PathVariable Long id){
		indirizzoService.deleteIndirizzo(id);
		return new ResponseEntity<>("Indirizzo eliminato!",HttpStatus.NO_CONTENT);
	}
}
