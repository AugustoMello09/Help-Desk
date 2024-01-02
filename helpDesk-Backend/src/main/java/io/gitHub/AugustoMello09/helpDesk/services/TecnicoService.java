package io.gitHub.AugustoMello09.helpDesk.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.gitHub.AugustoMello09.helpDesk.dto.CargoDTO;
import io.gitHub.AugustoMello09.helpDesk.dto.ChamadoDTO;
import io.gitHub.AugustoMello09.helpDesk.dto.TecnicoDTO;
import io.gitHub.AugustoMello09.helpDesk.entities.Cargo;
import io.gitHub.AugustoMello09.helpDesk.entities.Chamado;
import io.gitHub.AugustoMello09.helpDesk.entities.Tecnico;
import io.gitHub.AugustoMello09.helpDesk.entities.enums.StatusChamado;
import io.gitHub.AugustoMello09.helpDesk.repositories.CargoRepository;
import io.gitHub.AugustoMello09.helpDesk.repositories.ChamadoRepository;
import io.gitHub.AugustoMello09.helpDesk.repositories.TecnicoRepository;
import io.gitHub.AugustoMello09.helpDesk.services.exceptions.DataIntegratyViolationException;
import io.gitHub.AugustoMello09.helpDesk.services.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;

	@Autowired
	private CargoRepository cargoRepository;

	@Autowired
	private ChamadoRepository chamadoRepository;

	public TecnicoDTO findById(UUID id) {
		Optional<Tecnico> cli = repository.findById(id);
		Tecnico entity = cli.orElseThrow(() -> new ObjectNotFoundException("Tecnico não encontrado"));
		return new TecnicoDTO(entity);
	}

	@Transactional
	public TecnicoDTO create(TecnicoDTO tecnicoDTO) {
		Tecnico entity = new Tecnico();
		entity.setNome(tecnicoDTO.getNome());
		verificarEmailExistente(tecnicoDTO);
		entity.setEmail(tecnicoDTO.getEmail());
		atribuirCargo(entity, tecnicoDTO);
		repository.save(entity);
		return new TecnicoDTO(entity);
	}

	@Transactional
	public TecnicoDTO updateEmail(TecnicoDTO tecnicoDTO, UUID id) {
		Tecnico entity = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Tecnico não encontrado"));
		verificarEmailExistente(tecnicoDTO);
		entity.setEmail(tecnicoDTO.getEmail());
		repository.save(entity);
		return new TecnicoDTO(entity);
	}

	@Transactional
	public ChamadoDTO aceitarChamado(Long id, UUID idTecnico) {
		Chamado chamado = chamadoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado"));
		if (chamado.getTecnico() != null && !chamado.getTecnico().getId().equals(idTecnico)) {
	        throw new DataIntegratyViolationException("Este chamado já está em andamento e possui um técnico associado.");
	    }
		Tecnico tecnico = repository.findById(idTecnico)
				.orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado"));
		chamado.setTecnico(tecnico);
		chamado.setStatusChamado(StatusChamado.ANDAMENTO);
		chamadoRepository.save(chamado);
		return new ChamadoDTO(chamado);
	}

	@Transactional
	public ChamadoDTO finalizarChamado(Long id, UUID idTecnico) {
		Chamado chamado = chamadoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado"));
		if (chamado.getTecnico() != null && !chamado.getTecnico().getId().equals(idTecnico)) {
			throw new DataIntegratyViolationException("Somente o técnico associado pode finalizar este chamado aberto.");
		}
		chamado.setStatusChamado(StatusChamado.FECHADO);
		chamado.setDataFechamento(LocalDateTime.now());
		chamadoRepository.save(chamado);
		return new ChamadoDTO(chamado);
	}

	protected void atribuirCargo(Tecnico cli, TecnicoDTO tecnicoDTO) {
		cli.getCargos().clear();
		for (CargoDTO car : tecnicoDTO.getCargos()) {
			Cargo cargo = cargoRepository.findById(car.getId())
					.orElseThrow(() -> new ObjectNotFoundException("Cargo não encontrado"));
			cli.getCargos().add(cargo);
		}
	}

	protected void verificarEmailExistente(TecnicoDTO tecnicoDTO) {
		Optional<Tecnico> entity = repository.findByEmail(tecnicoDTO.getEmail());
		if (entity.isPresent() && !entity.get().getId().equals(tecnicoDTO.getId())) {
			throw new DataIntegratyViolationException("Email já existe");
		}
	}

}