package io.gitHub.AugustoMello09.helpDesk.dto;

import jakarta.validation.constraints.NotBlank;

public class ClienteInsertDTO extends ClienteDTO {

	@NotBlank(message = "Campo obrigatório.")
	private String senha;

	public ClienteInsertDTO() {
	}

	public ClienteInsertDTO(String senha) {
		super();
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

}
