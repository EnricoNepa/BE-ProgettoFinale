package com.epicenergy.controller.rest;

import com.epicenergy.model.DTO.response.ComuneResponseDTO;
import com.epicenergy.service.ComuneService;

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
@Tag(name = "Comune")
public class ComuneController {

	@Autowired
	ComuneService comuneService;
	
	@GetMapping(path = "/comune/lista")
	@Operation(summary="Lista di tutti i Comuni")
	public ResponseEntity<Page<ComuneResponseDTO>> getAllComuni(){
		return ResponseEntity.ok(comuneService.getAllComuni());
	}
	
	@GetMapping(path = "/comune/nome/{nome}")
	@Operation(summary="Ricerca comune per nome")
	public ResponseEntity<ComuneResponseDTO> getComunebyNome(@PathVariable String nome){
		return ResponseEntity.ok(comuneService.getComuneByNome(nome));
	}
}
