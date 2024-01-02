package io.gitHub.AugustoMello09.helpDesk.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.gitHub.AugustoMello09.helpDesk.dto.CargoDTO;
import io.gitHub.AugustoMello09.helpDesk.dto.ChamadoDTO;
import io.gitHub.AugustoMello09.helpDesk.dto.ClienteDTO;
import io.gitHub.AugustoMello09.helpDesk.entities.Cargo;
import io.gitHub.AugustoMello09.helpDesk.entities.Chamado;
import io.gitHub.AugustoMello09.helpDesk.entities.Cliente;
import io.gitHub.AugustoMello09.helpDesk.entities.enums.StatusChamado;
import io.gitHub.AugustoMello09.helpDesk.repositories.CargoRepository;
import io.gitHub.AugustoMello09.helpDesk.repositories.ChamadoRepository;
import io.gitHub.AugustoMello09.helpDesk.repositories.ClienteRepository;
import io.gitHub.AugustoMello09.helpDesk.services.exceptions.DataIntegratyViolationException;
import io.gitHub.AugustoMello09.helpDesk.services.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private CargoRepository cargoRepository;
	
	@Autowired
	private ChamadoRepository chamadoRepository;

	public ClienteDTO findById(UUID id) {
		Optional<Cliente> cli = repository.findById(id);
		Cliente entity = cli.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
		return new ClienteDTO(entity);
	}

	@Transactional
	public ClienteDTO create(ClienteDTO clienteDTO) {
		Cliente entity = new Cliente();
		entity.setNome(clienteDTO.getNome());
		verificarEmailExistente(clienteDTO);
		entity.setEmail(clienteDTO.getEmail());
		atribuirCargo(entity, clienteDTO);
		repository.save(entity);
		return new ClienteDTO(entity);
	}

	@Transactional
	public ClienteDTO updateEmail(ClienteDTO clienteDTO, UUID id) {
		Cliente entity = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
		verificarEmailExistente(clienteDTO);
		entity.setEmail(clienteDTO.getEmail());
		repository.save(entity);
		return new ClienteDTO(entity);
	}
	
	public ChamadoDTO buscarChamado(Long id) {
		Optional<Chamado> chamado = chamadoRepository.findById(id);
		Chamado entity = chamado.orElseThrow(
				() -> new ObjectNotFoundException("Chamado não encontrado"));
		return new ChamadoDTO(entity);
	}

	@Transactional
	public ChamadoDTO crairChamado(ChamadoDTO chamadoDTO, UUID id) {
		Cliente cliente = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
		Chamado chamado = new Chamado();
		chamado.setCliente(cliente);
		chamado.setDataAberto(LocalDateTime.now());
		chamado.setDescricao(chamadoDTO.getDescricao());
		chamado.setStatusChamado(StatusChamado.ABERTO);
		chamadoRepository.save(chamado);
		return new ChamadoDTO(chamado);
	}

	protected void atribuirCargo(Cliente cli, ClienteDTO clienteDTO) {
		cli.getCargos().clear();
		for (CargoDTO car : clienteDTO.getCargos()) {
			Cargo cargo = cargoRepository.findById(car.getId())
					.orElseThrow(() -> new ObjectNotFoundException("Cargo não encontrado"));
			cli.getCargos().add(cargo);

		}
	}

	protected void verificarEmailExistente(ClienteDTO clienteDTO) {
		Optional<Cliente> entity = repository.findByEmail(clienteDTO.getEmail());
		if (entity.isPresent() && !entity.get().getId().equals(clienteDTO.getId())) {
			throw new DataIntegratyViolationException("Email já existe");
		}
	}
}
