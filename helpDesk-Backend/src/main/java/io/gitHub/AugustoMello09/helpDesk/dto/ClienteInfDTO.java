package io.gitHub.AugustoMello09.helpDesk.dto;

import java.util.UUID;

import io.gitHub.AugustoMello09.helpDesk.entities.Cliente;
import lombok.Data;

@Data
public class ClienteInfDTO {
	
	private UUID id;
	private String nome;
	private String email;
	
	public ClienteInfDTO() {
	}
	
	public ClienteInfDTO(Cliente entity) {
		id = entity.getId();
		nome = entity.getNome();
		email = entity.getEmail();
	}
	
	

}
