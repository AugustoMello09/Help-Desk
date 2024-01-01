package io.gitHub.AugustoMello09.helpDesk.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.gitHub.AugustoMello09.helpDesk.dto.CargoDTO;
import io.gitHub.AugustoMello09.helpDesk.dto.TecnicoDTO;
import io.gitHub.AugustoMello09.helpDesk.entities.Cargo;
import io.gitHub.AugustoMello09.helpDesk.entities.Tecnico;
import io.gitHub.AugustoMello09.helpDesk.repositories.CargoRepository;
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
	
	public TecnicoDTO findById(UUID id) {
		Optional<Tecnico> cli = repository.findById(id);
		Tecnico entity = cli.orElseThrow(
				()-> new ObjectNotFoundException("Tecnico não encontrado"));
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
		Tecnico entity = repository.findById(id).orElseThrow(
				()-> new ObjectNotFoundException("Tecnico não encontrado"));
		verificarEmailExistente(tecnicoDTO);
		entity.setEmail(tecnicoDTO.getEmail());
		repository.save(entity);
		return new TecnicoDTO(entity);
	}
	
	
	protected void atribuirCargo(Tecnico cli, TecnicoDTO tecnicoDTO) {
		cli.getCargos().clear();
		for (CargoDTO car : tecnicoDTO.getCargos()) {
			Cargo cargo = cargoRepository.findById(car.getId()).orElseThrow(
					()-> new ObjectNotFoundException("Cargo não encontrado"));
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
