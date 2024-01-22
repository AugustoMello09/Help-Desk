package io.gitHub.AugustoMello09.helpDesk.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteInsertDTO extends ClienteDTO {
	
	private String senha;

}
