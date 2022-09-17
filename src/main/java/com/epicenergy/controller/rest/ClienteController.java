package com.epicenergy.controller.rest;

import java.time.LocalDate;

import com.epicenergy.model.DTO.request.ClienteRequestDTO;
import com.epicenergy.model.DTO.response.ClienteResponseDTO;
import com.epicenergy.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
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
@Tag(name = "Cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	//CRUD BASE
	@GetMapping(path = "/cliente/lista")
	@Operation(summary="Lista di tutti i clienti")
	public ResponseEntity<Page<ClienteResponseDTO>> getAllClienti(){
		return ResponseEntity.ok(clienteService.getAllClienti());
	}
	
	@PostMapping(path = "/cliente/create")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary="Crea un nuovo cliente")
	public ResponseEntity<ClienteResponseDTO> createCliente(@RequestBody ClienteRequestDTO cliente){	
		return new ResponseEntity<>(clienteService.createCliente(cliente), HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/cliente/update/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary="Modifica un cliente")
	public ResponseEntity<ClienteResponseDTO> updateCliente(@RequestBody ClienteRequestDTO cliente, @PathVariable Long id){
		return ResponseEntity.ok(clienteService.updateCliente(cliente, id));
	}
	
	@DeleteMapping(path = "/cliente/delete/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@Operation(summary="Cancella un cliente")
	public ResponseEntity<String> deleteCliente(@PathVariable Long id){
		clienteService.deleteCliente(id);
		return new ResponseEntity<String>("Cliente eliminato!",HttpStatus.NO_CONTENT);
	}
	
	//CRUD ORDINAMENTO
	@GetMapping(path = "/cliente/order/nome/")
	@Operation(summary="Ordina clienti per Ragione Sociale")
	public ResponseEntity<Page<ClienteResponseDTO>> orderClientiByNome(){
		return ResponseEntity.ok(clienteService.orderClientiByNome());
	}
	
	@GetMapping(path = "/cliente/order/fatturato/")
	@Operation(summary="Ordina clienti per Fatturato Annuale")
	public ResponseEntity<Page<ClienteResponseDTO>> orderClientiByFatturatoAnnuale(){
		return ResponseEntity.ok(clienteService.orderClientiByFatturatoAnnuale());
	}
	
	@GetMapping(path = "/cliente/order/datainserimento/")
	@Operation(summary="Ordina clienti per Data Inserimento")
	public ResponseEntity<Page<ClienteResponseDTO>> orderClientiByDataInserimento(){
		return ResponseEntity.ok(clienteService.orderClientiByDataInserimento());
	}
	
	@GetMapping(path = "/cliente/order/dataultimocontatto/")
	@Operation(summary="Ordina clienti per Data Ultimo Contatto")
	public ResponseEntity<Page<ClienteResponseDTO>> orderClientiByDataUltimoContatto(){
		return ResponseEntity.ok(clienteService.orderClientiByDataUltimoContatto());
	}
	
	@GetMapping(path = "/cliente/order/sedelegale/")
	@Operation(summary="Ordina clienti per Provincia Sede Legale")
	public ResponseEntity<Page<ClienteResponseDTO>> orderClientiBySedeLegale(){
		return ResponseEntity.ok(clienteService.orderClientiByProvinciaSedeLegale());
	}
	
	//CRUD FILTRAGGIO
	@GetMapping(path = "/cliente/filter/nome/{nome}")
	@Operation(summary="Filtra clienti per Ragione Sociale")
	public ResponseEntity<ClienteResponseDTO> filterClientiByCliente(@PathVariable String nome){
		return ResponseEntity.ok(clienteService.filterClientiByNome(nome));
	}
	
	@GetMapping(path = "/cliente/filter/fatturato/{fatturato1}/{fatturato2}")
	@Operation(summary="Filtra clienti per Range di Fatturato")
	public ResponseEntity<Page<ClienteResponseDTO>> filterClientiByFatturatoAnnuale(@PathVariable Long fatturato1, @PathVariable Long fatturato2){
		return ResponseEntity.ok(clienteService.filterClientiByFatturatoAnnuale(fatturato1, fatturato2));
	}
	
	@GetMapping(path = "/cliente/filter/datainserimento/{data}")
	@Operation(summary="Filtra clienti per Data Inserimento")
	public ResponseEntity<Page<ClienteResponseDTO>> filterClientiByDataInserimento(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data ){
		return ResponseEntity.ok(clienteService.filterClientiByDataInserimento(data));
	}
	
	@GetMapping(path = "/cliente/filter/dataultimocontatto/{data}")
	@Operation(summary="Filtra clienti per Data Ultimo Contatto")
	public ResponseEntity<Page<ClienteResponseDTO>> filterClientiByDataUltimoContatto(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data){
		return ResponseEntity.ok(clienteService.filterClientiByDataUltimoContatto(data));
	}
	
	@GetMapping(path = "/cliente/filter/partialname/{nome}")
	@Operation(summary="Filtra clienti per Nome Parziale")
	public ResponseEntity<Page<ClienteResponseDTO>> filterClientiByPartialNome(@PathVariable String nome){
		return ResponseEntity.ok(clienteService.filterClientiByPartialNome(nome));
	}
	
}
