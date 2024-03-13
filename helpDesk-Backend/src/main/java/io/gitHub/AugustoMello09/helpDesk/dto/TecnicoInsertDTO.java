package io.gitHub.AugustoMello09.helpDesk.dto;

import jakarta.validation.constraints.NotBlank;

public class TecnicoInsertDTO extends TecnicoDTO {

	@NotBlank(message = "Campo obrigatório.")
	private String senha;

	public TecnicoInsertDTO() {
	}

	public TecnicoInsertDTO(@NotBlank(message = "Campo obrigatório.") String senha) {
		super();
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

}
