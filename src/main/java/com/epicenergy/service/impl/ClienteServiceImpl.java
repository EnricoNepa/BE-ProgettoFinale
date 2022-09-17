package com.epicenergy.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.epicenergy.exception.EpicEnergyException;
import com.epicenergy.model.Cliente;
import com.epicenergy.model.Comune;
import com.epicenergy.model.Tipo;
import com.epicenergy.model.DTO.request.ClienteRequestDTO;
import com.epicenergy.model.DTO.response.ClienteResponseDTO;
import com.epicenergy.repository.ClienteRepository;
import com.epicenergy.repository.ComuneRepository;
import com.epicenergy.repository.StatoRepository;
import com.epicenergy.repository.TipoRepository;
import com.epicenergy.service.ClienteService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private TipoRepository tipoRepository;
	
	@Autowired
	private ComuneRepository comuneRepository;
	
	@Override
	public Page<ClienteResponseDTO> getAllClienti() {
		List<Cliente> clienti = clienteRepository.findAll();
		List<ClienteResponseDTO> response = clienti.stream().map(cliente -> modelMapper.map(cliente, ClienteResponseDTO.class))
                .collect(Collectors.toList());
		return new PageImpl<>(response);
	}

	@Override
	public ClienteResponseDTO createCliente(ClienteRequestDTO clienteRequest) {
		Cliente cliente = modelMapper.map(clienteRequest, Cliente.class);
        Optional<Tipo> tipoCheck=tipoRepository.findByTipo(clienteRequest.getTipo());
        Optional<Comune> comuneLegale = comuneRepository.findByNomeComuneIgnoreCase(clienteRequest.getSedeLegale().getNomeComune());
        Optional<Comune> comuneOperativo = comuneRepository.findByNomeComuneIgnoreCase(clienteRequest.getSedeOperativa().getNomeComune());
        //controllo tipo
        if(tipoCheck.isEmpty()){
        throw new EpicEnergyException("Il tipo non esiste");
        }
        //controllo comune legale ed operativo
        if (comuneLegale.isEmpty() && comuneOperativo.isEmpty()) {
            throw new EpicEnergyException("Il comune non esiste'");
        }

        cliente.getSedeLegale().setComune(comuneLegale.get());
        cliente.getSedeOperativa().setComune(comuneOperativo.get());
        cliente.setTipo(tipoCheck.get());

        return modelMapper.map(clienteRepository.save(cliente), ClienteResponseDTO.class);
	}

	@Override
	public ClienteResponseDTO updateCliente(ClienteRequestDTO clienteRequest, Long id) {
		//controllo esistenza cliente
        if (!clienteRepository.existsById(id)) {
            throw new EpicEnergyException("Il cliente con id immesso non esiste");
        }
        Cliente cliente = modelMapper.map(clienteRequest, Cliente.class);
        Optional<Tipo> tipoCheck=tipoRepository.findByTipo(clienteRequest.getTipo());
        Optional<Comune> comuneLegale = comuneRepository.findByNomeComuneIgnoreCase(clienteRequest.getSedeLegale().getNomeComune());
        Optional<Comune> comuneOperativo = comuneRepository.findByNomeComuneIgnoreCase(clienteRequest.getSedeOperativa().getNomeComune());
        //controllo tipo
        if(tipoCheck.isEmpty()){
            throw new EpicEnergyException("Il tipo non esiste");

        }
        //controllo comune legale ed operativo
        if (comuneLegale.isEmpty() && comuneOperativo.isEmpty()) {
            throw new EpicEnergyException("Il comune non esiste'");
        }

        cliente.getSedeLegale().setComune(comuneLegale.get());
        cliente.getSedeOperativa().setComune(comuneOperativo.get());
        cliente.setTipo(tipoCheck.get());


        cliente.getSedeLegale().setComune(comuneLegale.get());
        cliente.getSedeOperativa().setComune(comuneOperativo.get());
        cliente.setId(id);
        return modelMapper.map(clienteRepository.save(cliente), ClienteResponseDTO.class);
	}

	@Override
	public void deleteCliente(Long id) {
		if (!clienteRepository.existsById(id)) {
			throw new EpicEnergyException("Cliente inesistente!");
		}
		clienteRepository.deleteById(id);
	}

	@Override
	public Page<ClienteResponseDTO> orderClientiByNome() {
		List<Cliente> clienti = clienteRepository.findAllByOrderByRagioneSociale();
		List<ClienteResponseDTO> response = clienti.stream().map(cliente -> modelMapper.map(cliente, ClienteResponseDTO.class))
                .collect(Collectors.toList());
		return new PageImpl<>(response);
	}

	@Override
	public Page<ClienteResponseDTO> orderClientiByFatturatoAnnuale() {
		List<Cliente> clienti = clienteRepository.findAllByOrderByFatturatoAnnuale();
		List<ClienteResponseDTO> response = clienti.stream().map(cliente -> modelMapper.map(cliente, ClienteResponseDTO.class))
                .collect(Collectors.toList());
		return new PageImpl<>(response);
	}

	@Override
	public Page<ClienteResponseDTO> orderClientiByDataInserimento() {
		List<Cliente> clienti = clienteRepository.findAllByOrderByDataInserimento();
		List<ClienteResponseDTO> response = clienti.stream().map(cliente -> modelMapper.map(cliente, ClienteResponseDTO.class))
                .collect(Collectors.toList());
		return new PageImpl<>(response);
	}

	@Override
	public Page<ClienteResponseDTO> orderClientiByDataUltimoContatto() {
		List<Cliente> clienti = clienteRepository.findAllByOrderByDataUltimoContatto();
		List<ClienteResponseDTO> response = clienti.stream().map(cliente -> modelMapper.map(cliente, ClienteResponseDTO.class))
                .collect(Collectors.toList());
		return new PageImpl<>(response);
	}

	@Override
	public Page<ClienteResponseDTO> orderClientiByProvinciaSedeLegale() {
		List<Cliente> clienti = clienteRepository.findAllByOrderBySedeLegale_Comune_NomeProvincia();
		List<ClienteResponseDTO> response = clienti.stream().map(cliente -> modelMapper.map(cliente, ClienteResponseDTO.class))
                .collect(Collectors.toList());
		return new PageImpl<>(response);
	}

	@Override
	public ClienteResponseDTO filterClientiByNome(String nome) {
		Optional<Cliente> clienti = clienteRepository.findByRagioneSocialeIgnoreCase(nome);
		if(clienti.isEmpty()) {
			throw new EpicEnergyException("Cliente inesistente");
		}
		return modelMapper.map(clienti.get(), ClienteResponseDTO.class);
	}

	@Override
	public Page<ClienteResponseDTO> filterClientiByFatturatoAnnuale(Long fatturato1,
			Long fatturato2) {
		List<Cliente> clienti = clienteRepository.findByFatturatoAnnualeBetween(fatturato1, fatturato2);
		List<ClienteResponseDTO> response = clienti.stream().map(cliente -> modelMapper.map(cliente, ClienteResponseDTO.class))
                .collect(Collectors.toList());
		return new PageImpl<>(response);
	}

	@Override
	public Page<ClienteResponseDTO> filterClientiByDataInserimento(LocalDate data) {
		List<Cliente> clienti = clienteRepository.findByDataInserimento(data);
		List<ClienteResponseDTO> response = clienti.stream().map(cliente -> modelMapper.map(cliente, ClienteResponseDTO.class))
                .collect(Collectors.toList());
		return new PageImpl<>(response);
	}

	@Override
	public Page<ClienteResponseDTO> filterClientiByDataUltimoContatto(LocalDate data) {
		List<Cliente> clienti = clienteRepository.findByDataUltimoContatto(data);
		List<ClienteResponseDTO> response = clienti.stream().map(cliente -> modelMapper.map(cliente, ClienteResponseDTO.class))
                .collect(Collectors.toList());
		return new PageImpl<>(response);
	}

	@Override
	public Page<ClienteResponseDTO> filterClientiByPartialNome(String nome) {
		List<Cliente> clienti = clienteRepository.findByRagioneSocialeContainingIgnoreCase(nome);
		List<ClienteResponseDTO> response = clienti.stream().map(cliente -> modelMapper.map(cliente, ClienteResponseDTO.class))
                .collect(Collectors.toList());
		return new PageImpl<>(response);
	}


}
