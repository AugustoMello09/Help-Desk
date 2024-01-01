package io.gitHub.AugustoMello09.helpDesk.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import io.gitHub.AugustoMello09.helpDesk.entities.Tecnico;
import lombok.Data;

@Data
public class TecnicoDTO {

	private UUID id;
	private String nome;
	private String email;
	private Set<CargoDTO> cargos = new HashSet<>();

	public TecnicoDTO() {
	}

	public TecnicoDTO(Tecnico entity) {
		id = entity.getId();
		nome = entity.getNome();
		email = entity.getEmail();
		entity.getCargos().forEach(y -> this.cargos.add(new CargoDTO(y)));
	}

	public TecnicoDTO(UUID id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

}
