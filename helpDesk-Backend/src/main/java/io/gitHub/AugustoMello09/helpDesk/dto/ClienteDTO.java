package io.gitHub.AugustoMello09.helpDesk.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import io.gitHub.AugustoMello09.helpDesk.entities.Cliente;
import lombok.Data;

@Data
public class ClienteDTO {

	private UUID id;
	private String nome;
	private String email;
	private List<ChamadoDTO> chamados = new ArrayList<>();
	private Set<CargoDTO> cargos = new HashSet<>();

	public ClienteDTO() {
	}

	public ClienteDTO(Cliente entity) {
		id = entity.getId();
		nome = entity.getNome();
		email = entity.getEmail();
		entity.getCargos().forEach(y -> this.cargos.add(new CargoDTO(y)));
	}

	public ClienteDTO(UUID id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

}
