package com.epicenergy.controller.rest;

import com.epicenergy.model.DTO.response.ProvinciaResponseDTO;
import com.epicenergy.service.ProvinciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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
@Tag(name = "Provincia")
public class ProvinciaController {

	@Autowired
	ProvinciaService provinciaService;
	
	@GetMapping(path = "/provincia/lista")
	@Operation(summary="Lista di tutte le Province")
	public ResponseEntity<Page<ProvinciaResponseDTO>> getAllProvince(){
		return ResponseEntity.ok(provinciaService.getAllProvincia());
	}
	
	@GetMapping(path = "/provincia/nome/{nome}")
	@Operation(summary="Ricerca provincia per Nome")
	public ResponseEntity<ProvinciaResponseDTO> getProvinciaByNome(@PathVariable String nome){
		return ResponseEntity.ok(provinciaService.getProvinciaByNome(nome));
	}
	
	@GetMapping(path = "/provincia/sigla/{sigla}")
	@Operation(summary="Ricerca Provincia per Sigla")
	public ResponseEntity<ProvinciaResponseDTO> getProvinciaBySigla(@PathVariable String sigla){
		return ResponseEntity.ok(provinciaService.getProvinciaBySigla(sigla));
	}
}
