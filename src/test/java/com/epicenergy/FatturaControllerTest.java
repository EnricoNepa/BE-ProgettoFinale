package com.epicenergy;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.epicenergy.repository.ClienteRepository;
import com.epicenergy.repository.FatturaRepository;
import com.epicenergy.repository.IndirizzoRepository;
import com.epicenergy.repository.StatoRepository;
import com.epicenergy.repository.TipoRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class FatturaControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	@Autowired
	FatturaRepository fatturaRepository;
	@Autowired
	IndirizzoRepository indirizzoRepository;
	@Autowired
	TipoRepository tipoRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	StatoRepository statoRepository;
	
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
		statoRepository.save(stato1);
		statoRepository.save(stato2);

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
		
		fatturaRepository.save(f1);

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
		clienteRepository.save(c1);
		clienteRepository.save(c2);

		log.info("Fine");
	}
	
	@Test
	@WithAnonymousUser
	public void getAllFatture() throws Exception {
		this.mockMvc.perform(get("/api/fattura/lista")).andExpect(status().isUnauthorized());
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void listaClientiWhenUtenteMockIsAuthenticated() throws Exception {
		this.mockMvc.perform(get("/api/fattura/lista")).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void deleteStudent() throws Exception {
		this.mockMvc.perform(delete("/api/fattura/delete/1")).andExpect(status().isNoContent());
	}
	
	@Test
	@WithMockUser(username = "user", password = "user", roles = "USER")
	public void deleteStudentNonAutorizzato() throws Exception {
		this.mockMvc.perform(delete("/api/fattura/delete/1")).andExpect(status().isForbidden());
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void listaFatturePerRagioneSociale() throws Exception {
		this.mockMvc.perform(get("api/fattura/filter/cliente/MisterX")).andExpect(status().isNotFound());
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	public void listaFatturePerData() throws Exception {
		this.mockMvc.perform(get("api/fattura/filter/data/2020-04-04")).andExpect(status().isNotFound());
	}
	
}
