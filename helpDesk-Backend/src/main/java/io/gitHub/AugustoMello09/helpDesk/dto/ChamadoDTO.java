package io.gitHub.AugustoMello09.helpDesk.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.gitHub.AugustoMello09.helpDesk.entities.Chamado;
import io.gitHub.AugustoMello09.helpDesk.entities.enums.StatusChamado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ChamadoDTO {

	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataAberto;
	
	@Size(max = 200, message = "tamanho máximo de 200 caracteres.")
	@NotBlank(message = "Campo obrigatório.")
	private String descricao;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
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
