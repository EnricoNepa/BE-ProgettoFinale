package com.epicenergy.util;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.epicenergy.model.Cliente;
import com.epicenergy.model.Comune;
import com.epicenergy.model.Indirizzo;
import com.epicenergy.model.Provincia;
import com.epicenergy.model.Stato;
import com.epicenergy.model.Tipo;
import com.epicenergy.model.TipoEnum;
import com.epicenergy.repository.ClienteRepository;
import com.epicenergy.repository.ComuneRepository;
import com.epicenergy.repository.IndirizzoRepository;
import com.epicenergy.repository.ProvinciaRepository;
import com.epicenergy.repository.StatoRepository;
import com.epicenergy.repository.TipoRepository;
import com.epicenergy.security.model.Role;
import com.epicenergy.security.model.Roles;
import com.epicenergy.security.model.User;
import com.epicenergy.security.repository.RoleRepository;
import com.epicenergy.security.repository.UserRepository;
import com.opencsv.CSVReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class InitDataRunner implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private ProvinciaRepository provinciaRepository;
	@Autowired
	private ComuneRepository comuneRepository;
	@Autowired
	TipoRepository tipoRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	StatoRepository statoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		initSecurity();
		log.info("Security caricata!");
		initStato();
		log.info("Stati caricati!"); 
		initTipo();
		log.info("Tipi caricati!"); 
		initProvincia();
		log.info("Province caricate!");                   //Inizializzazione dati
		initComune();									  //dell'applicazione
		log.info("Comuni caricati!"); 		
		initCliente();
		log.info("Clienti caricati!"); 
				
	}

	private void initProvincia() throws Exception {
		CSVReader csvReader = new CSVReader(new FileReader("province-italiane.csv"));
		String[] values = null;
		csvReader.readNext();
		while ((values = csvReader.readNext()) != null) {
			provinciaRepository.save(new Provincia(values[0], values[1], values[2]));
		}
	}
	
	private void initComune() throws Exception {
		CSVReader csvReader = new CSVReader(new FileReader("comuni-italiani.csv"));
		String[] values = null;
		csvReader.readNext();
		while((values = csvReader.readNext()) != null) {
			Optional<Provincia> provincia = provinciaRepository.findByNomeIgnoreCase(values[3]);
			if(provincia.isPresent()) {
			comuneRepository.save(new Comune(values[2], values[3], provincia.get()));
			}
		}

	}
	
	private void initStato() throws Exception {
		Stato stato1 = new Stato();
		Stato stato2 = new Stato();

		stato1.setNome("Pagato");
		stato2.setNome("Non pagato");

		statoRepository.save(stato1);
		statoRepository.save(stato2);

	}

	private void initTipo() throws Exception {
		Tipo tipo1 = new Tipo();
		Tipo tipo2 = new Tipo();
		Tipo tipo3 = new Tipo();
		Tipo tipo4 = new Tipo();

		tipo1.setTipo(TipoEnum.PA);
		tipo2.setTipo(TipoEnum.SAS);
		tipo3.setTipo(TipoEnum.SPA);
		tipo4.setTipo(TipoEnum.SRL);

		tipoRepository.save(tipo1);
		tipoRepository.save(tipo2);
		tipoRepository.save(tipo3);
		tipoRepository.save(tipo4);

	}

	
	private void initCliente() throws Exception {
		
		Indirizzo i1 = new Indirizzo();
		i1.setCap("00152");
		i1.setCivico(12);
		i1.setLocalita("Roma");
		i1.setVia("Via Francesco Massi");
		i1.setComune(comuneRepository.findById(1l).get());

		Indirizzo i2 = new Indirizzo();
		i2.setCap("00199");
		i2.setCivico(57);
		i2.setLocalita("Milano");
		i2.setVia("Via Raffaele Battistini");
		i2.setComune(comuneRepository.findById(2l).get());

		Indirizzo i3 = new Indirizzo();
		i3.setCap("50489");
		i3.setCivico(180);
		i3.setLocalita("Agliè");
		i3.setVia("Via Giuseppe Verdi");
		i3.setComune(comuneRepository.findById(3l).get());
		
		Cliente c1 = new Cliente();
		c1.setCognomeContatto("Rossi");
		c1.setDataInserimento(LocalDate.parse("2021-06-07"));
		c1.setDataUltimoContatto(LocalDate.parse("2021-12-24"));
		c1.setEmail("riot.business@gmail.com");
		c1.setEmailContatto("marco.rossi@gmail.com");
		c1.setFatturatoAnnuale(1000000000l);
		c1.setNomeContatto("Marco");
		c1.setPartitaIVA(12893542384l);
		c1.setPec("riot@pec.com");
		c1.setRagioneSociale("Riot");
		c1.setSedeLegale(i1);
		c1.setSedeOperativa(i2);
		c1.setTelefono(3337869087l);
		c1.setTelefonoContatto(333713921l);
		c1.setTipo(tipoRepository.findById(1l).get());
	
		Cliente c2 = new Cliente();
		c2.setCognomeContatto("Walker");
		c2.setDataInserimento(LocalDate.parse("2020-07-12"));
		c2.setDataUltimoContatto(LocalDate.parse("2020-10-17"));
		c2.setEmail("patate&co.business@gmail.com");
		c2.setEmailContatto("walker.paul@gmail.com");
		c2.setFatturatoAnnuale(1500000l);
		c2.setNomeContatto("Paul");
		c2.setPartitaIVA	(45893542384l);
		c2.setPec("patate&co@pec.com");
		c2.setRagioneSociale("Patate&co");
		c2.setSedeLegale(i3);
		c2.setSedeOperativa(i3);
		c2.setTelefono(3678869087l);
		c2.setTelefonoContatto(33375911l);
		c2.setTipo(tipoRepository.findById(2l).get());

		clienteRepository.save(c1);
		clienteRepository.save(c2);
	}
	
	private void initSecurity(){
		 BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
			
			Role role = new Role();
		    Role role2 = new Role();
		   
		    role2.setRoleName(Roles.ROLE_USER);
			role.setRoleName(Roles.ROLE_ADMIN);
		
			User user = new User();
			User user2 = new User();
			Set<Role> rolesAdmin = new HashSet<>(); 
			Set<Role> rolesUser = new HashSet<>(); 
			rolesAdmin.add(role);
			rolesUser.add(role2);
			
			user2.setUserName("user");
			user2.setPassword(bCrypt.encode("user"));
			user2.setEmail("user@gmail.com");
			user2.setRoles(rolesUser);
			user2.setActive(true);
			
			user.setUserName("admin");
			user.setPassword(bCrypt.encode("admin"));
			user.setEmail("admin@domain.com");
			user.setRoles(rolesAdmin);
			user.setActive(true);
			
			
			roleRepository.save(role2);
			roleRepository.save(role);
		
			userRepository.save(user);
			userRepository.save(user2);
	}
}
