package io.gitHub.AugustoMello09.helpDesk.dto;

import java.time.LocalDateTime;

import io.gitHub.AugustoMello09.helpDesk.entities.Chamado;
import io.gitHub.AugustoMello09.helpDesk.entities.enums.StatusChamado;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ChamadoDTO {

	private Long id;

	private LocalDateTime dataAberto;

	private String descricao;
	private LocalDateTime dataFechamento;
	private StatusChamado statusChamado;

	private ClienteInfDTO cliente;
	private TecnicoInfDTO tecnico;

	public ChamadoDTO() {

	}

	public ChamadoDTO(Chamado entity) {
		cliente = new ClienteInfDTO(entity.getCliente());
		if (entity.getTecnico() != null) {
			tecnico = new TecnicoInfDTO(entity.getTecnico());
		} else {
			tecnico = null;
		}
		id = entity.getId();
		dataAberto = LocalDateTime.now();
		descricao = entity.getDescricao();
		dataFechamento = entity.getDataFechamento();
		statusChamado = entity.getStatusChamado();

	}

}
