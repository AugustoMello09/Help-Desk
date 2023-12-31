package io.gitHub.AugustoMello09.helpDesk.dto;

import io.gitHub.AugustoMello09.helpDesk.entities.Cargo;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CargoDTO {

	private Long id;
	private String authority;
	
	public CargoDTO() {}
	
	public CargoDTO(Cargo entity) {
		id = entity.getId();
		authority = entity.getAuthority();
	}
}
