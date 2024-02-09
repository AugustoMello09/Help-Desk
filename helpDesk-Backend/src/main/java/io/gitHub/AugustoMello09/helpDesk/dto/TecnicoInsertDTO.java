package io.gitHub.AugustoMello09.helpDesk.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TecnicoInsertDTO extends TecnicoDTO{
	
	@NotBlank(message = "Campo obrigatório.")
	private String senha;
}
