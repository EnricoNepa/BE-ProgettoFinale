package com.epicenergy.controller.rest;

import com.epicenergy.model.DTO.request.StatoRequestDTO;
import com.epicenergy.model.DTO.response.StatoResponseDTO;
import com.epicenergy.service.StatoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Stato")
public class StatoController {

	@Autowired
	StatoService statoService;
	
	@PostMapping(path = "/stato/create")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary="Crea un nuovo Stato")
	public ResponseEntity<StatoResponseDTO> createStato(@RequestBody StatoRequestDTO stato){
		return ResponseEntity.ok(statoService.createStato(stato));
	}
	
	@DeleteMapping(path = "/stato/delete")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary="Elimina uno Stato")
	public ResponseEntity<String> deleteStato(@PathVariable Long id){
		statoService.deleteStato(id);
		return new ResponseEntity<>("Cliente eliminato!",HttpStatus.NO_CONTENT);
	}
}
