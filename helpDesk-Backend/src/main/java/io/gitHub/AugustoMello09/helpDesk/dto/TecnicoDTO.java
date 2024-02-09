package io.gitHub.AugustoMello09.helpDesk.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import io.gitHub.AugustoMello09.helpDesk.entities.Tecnico;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TecnicoDTO {

	private UUID id;
	
	@Size(max = 60, message = "tamanho máximo de 60 caracteres.")
	@NotBlank(message = "Campo obrigatório.")
	private String nome;
	
	@Email(message = "Tem que ser um email válido. ")
	@NotBlank(message = "Campo obrigatório.")
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
