package io.gitHub.AugustoMello09.helpDesk.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.gitHub.AugustoMello09.helpDesk.entities.Chamado;
import io.gitHub.AugustoMello09.helpDesk.entities.enums.StatusChamado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

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

	public ChamadoDTO(Long id, LocalDateTime dataAberto, String descricao, LocalDateTime dataFechamento,
			StatusChamado statusChamado, ClienteInfDTO cliente, TecnicoInfDTO tecnico) {
		super();
		this.id = id;
		this.dataAberto = dataAberto;
		this.descricao = descricao;
		this.dataFechamento = dataFechamento;
		this.statusChamado = statusChamado;
		this.cliente = cliente;
		this.tecnico = tecnico;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataAberto() {
		return dataAberto;
	}

	public void setDataAberto(LocalDateTime dataAberto) {
		this.dataAberto = dataAberto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public StatusChamado getStatusChamado() {
		return statusChamado;
	}

	public void setStatusChamado(StatusChamado statusChamado) {
		this.statusChamado = statusChamado;
	}

	public ClienteInfDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteInfDTO cliente) {
		this.cliente = cliente;
	}

	public TecnicoInfDTO getTecnico() {
		return tecnico;
	}

	public void setTecnico(TecnicoInfDTO tecnico) {
		this.tecnico = tecnico;
	}

}
