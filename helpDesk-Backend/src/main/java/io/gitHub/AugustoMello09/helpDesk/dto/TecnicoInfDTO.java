package io.gitHub.AugustoMello09.helpDesk.dto;

import java.util.UUID;

import io.gitHub.AugustoMello09.helpDesk.entities.Tecnico;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TecnicoInfDTO {
	
	private UUID id;
	private String nome;
	private String email;
	
	public TecnicoInfDTO() {
	}
	
	public TecnicoInfDTO(Tecnico entity) {
		id = entity.getId();
		nome = entity.getNome();
		email = entity.getEmail();
	}
	
	

}
