package com.epicenergy;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.epicenergy.model.Cliente;
import com.epicenergy.model.Fattura;
import com.epicenergy.model.Indirizzo;
import com.epicenergy.model.Stato;
import com.epicenergy.model.Tipo;
import com.epicenergy.model.TipoEnum;
import com.epicenergy.repository.IndirizzoRepository;
import com.epicenergy.repository.TipoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class ClienteControllerTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	IndirizzoRepository indirizzoRepository;
	@Autowired
	TipoRepository tipoRepository;


	Cliente c1 = new Cliente();
	Cliente c2 = new Cliente();

	@BeforeEach
	public void contextInit() {
		log.info("Inizio");

		Stato stato1 = new Stato();
		stato1.setNome("Pagato");

		Stato stato2 = new Stato();
		stato2.setNome("Non pagato");

		Tipo tipo = new Tipo();
		tipo.setTipo(TipoEnum.PA);
		
		tipoRepository.save(tipo);

		Indirizzo i1 = new Indirizzo();
		i1.setCap("00152");
		i1.setCivico(12);
		i1.setLocalita("Roma");
		i1.setVia("Via Francesco Massi");

		Indirizzo i2 = new Indirizzo();
		i2.setCap("00199");
		i2.setCivico(57);
		i2.setLocalita("Milano");
		i2.setVia("Via Raffaele Battistini");
		
		indirizzoRepository.save(i1);
		indirizzoRepository.save(i2);
		

		Fattura f1 = new Fattura();
		f1.setAnno(2020);
		f1.setData(LocalDate.parse("2020-04-04"));
		f1.setImporto(BigDecimal.valueOf(13450.99));
		f1.setNumero(122);
		f1.setStato(stato1);

		Fattura f2 = new Fattura();
		f2.setAnno(2021);
		f2.setData(LocalDate.parse("2021-05-14"));
		f2.setImporto(BigDecimal.valueOf(5999.49));
		f2.setNumero(389);
		f2.setStato(stato2);

		List<Fattura> fatture = new ArrayList<>();
		fatture.add(f2);
		fatture.add(f1);
		
		
		c2.setCognomeContatto("Walker");
		c2.setDataInserimento(LocalDate.parse("2020-07-12"));
		c2.setDataUltimoContatto(LocalDate.parse("2020-10-17"));
		c2.setEmail("patate&co.business@gmail.com");
		c2.setEmailContatto("walker.paul@gmail.com");
		c2.setFatturatoAnnuale(1500000l);
		c2.setNomeContatto("Paul");
		c2.setPartitaIVA(45893542384l);
		c2.setPec("patate&co@pec.com");
		c2.setRagioneSociale("Pippo");
		c2.setSedeLegale(i1);
		c2.setSedeOperativa(i2);
		c2.setTelefono(3678869087l);
		c2.setTelefonoContatto(33375911l);
		c2.setTipo(tipo);

		c1.setCognomeContatto("Rossi");
		c1.setDataInserimento(LocalDate.parse("2021-06-07"));
		c1.setDataUltimoContatto(LocalDate.parse("2021-12-24"));
		c1.setEmail("riot.business@gmail.com");
		c1.setEmailContatto("marco.rossi@gmail.com");
		c1.setFatturatoAnnuale(1000000000l);
		c1.setFatture(fatture);
		c1.setNomeContatto("Marco");
		c1.setPartitaIVA(12893542384l);
		c1.setPec("riot@pec.com");
		c1.setRagioneSociale("Riot");
		c1.setSedeLegale(i1);
		c1.setSedeOperativa(i2);
		c1.setTelefono(3337869087l);
		c1.setTelefonoContatto(333713921l);
		c1.setTipo(tipo);
		
		log.info("Clienti creati correttamente!");
	}

	@Test
	@WithMockUser(username = "m.rossi", password = "test", roles = "ADMIN")
	public void addNewCliente() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		String json = mapper.writeValueAsString(c1);

		@SuppressWarnings("unused")
		MvcResult result = mockMvc
				.perform(post("/api/cliente/aggiungi").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated()).andExpect(content().json("{'ragioneSociale':'Riot'}"))
				.andExpect(content().json("{'cognomeContatto':'Rossi'}")).andReturn();
	}

	@Test
	@WithAnonymousUser
	public void getAllClienti() throws Exception {
		this.mockMvc.perform(get("/api/cliente/lista")).andExpect(status().isUnauthorized());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void listaClientiWhenUtenteMockIsAuthenticated() throws Exception {
		this.mockMvc.perform(get("/api/cliente/lista")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void deleteCliente() throws Exception {
		this.mockMvc.perform(delete("/api/cliente/delete/1")).andExpect(status().isNoContent());
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void updateCliente() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		String json = mapper.writeValueAsString(c2);
        this.mockMvc.perform(
                put("/api/cliente/update/2")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(json)).andExpect(status().isOk()).andExpect(content().json("{'ragioneSociale':'Pippo'}"));	
	}
	
	@Test
	@WithMockUser(username = "user", password = "user", roles = "USER")
	public void deleteStudent2() throws Exception {
		this.mockMvc.perform(delete("/api/cliente/delete/1")).andExpect(status().isForbidden());
	}
	
	@Test
	@WithMockUser(username = "user", password = "user", roles = "USER")
	public void filtraPerDataInserimento() throws Exception {
		this.mockMvc.perform(get("/api/cliente/order/datainserimento")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "user", password = "user", roles = "USER")
	public void filtraPerDataUltimoContatto() throws Exception {
		this.mockMvc.perform(get("/api/cliente/order/dataultimocontatto")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "user", password = "user", roles = "USER")
	public void OrdinaPerProvinciaSedeLegale() throws Exception {
		this.mockMvc.perform(get("api/cliente/order/sedelegale/")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "user", password = "user", roles = "USER")
	public void ordinaPerFatturatoAnnuale() throws Exception {
		this.mockMvc.perform(get("api/cliente/order/fatturato/")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "user", password = "user", roles = "USER")
	public void filtraPerFatturatoAnnuale() throws Exception {
		this.mockMvc.perform(get("api/cliente/filter/fatturato/9/9999999")).andExpect(status().isOk());
	}
			
	@Test
	@WithMockUser(username = "user", password = "user", roles = "USER")
	public void filtraDataInserimentoNonEsistente() throws Exception {
		this.mockMvc.perform(get("api/cliente/filter/datainserimento/1999-02-01")).andExpect(status().isNotFound());
	}
	
	@Test
	@WithMockUser(username = "user", password = "user", roles = "USER")
	public void filtraDataUltimoContattoNonEsistente() throws Exception {
		this.mockMvc.perform(get("api/cliente/filter/dataultimocontatto/1999-12-24")).andExpect(status().isNotFound());
	}

	@Test
	@WithMockUser(username = "user", password = "user", roles = "USER")
	public void filtraPerRagioneSocialeNonEsistente() throws Exception {
		this.mockMvc.perform(get("api/cliente/filter/nome/zz")).andExpect(status().isNotFound());
	}
	
}
