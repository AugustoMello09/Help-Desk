package io.gitHub.AugustoMello09.helpDesk.dto;

import java.time.LocalDateTime;

import io.gitHub.AugustoMello09.helpDesk.entities.Chamado;
import io.gitHub.AugustoMello09.helpDesk.entities.enums.StatusChamado;
import lombok.Data;

@Data
public class ChamadoDTO {

	private Long id;

	private LocalDateTime dataAberto;

	private String descricao;
	private LocalDateTime dataFechamento;
	private StatusChamado statusChamado;
	
	private ClienteDTO cliente;
	
	public ChamadoDTO() {
		
	}
	
	public ChamadoDTO(Chamado entity) {
		cliente = new ClienteDTO(entity.getCliente());
		id = entity.getId();
		dataAberto = LocalDateTime.now();
		descricao = entity.getDescricao();
		dataFechamento = entity.getDataFechamento();
		statusChamado = StatusChamado.ABERTO;
		
	}
	
	

}
