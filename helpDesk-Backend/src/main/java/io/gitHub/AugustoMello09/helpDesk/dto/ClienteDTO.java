package io.gitHub.AugustoMello09.helpDesk.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import io.gitHub.AugustoMello09.helpDesk.entities.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteDTO {

	private UUID id;
	
	@Size(max = 60, message = "tamanho máximo de 60 caracteres.")
	@NotBlank(message = "Campo obrigatório.")
	private String nome;
	
	@Email(message = "Tem que ser um email válido. ")
	@NotBlank(message = "Campo obrigatório.")
	private String email;
	
	private List<ChamadoDTO> chamados = new ArrayList<>();
	private Set<CargoDTO> cargos = new HashSet<>();

	public ClienteDTO() {
	}

	public ClienteDTO(Cliente entity) {
		id = entity.getId();
		nome = entity.getNome();
		email = entity.getEmail();
		entity.getCargos().forEach(y -> this.cargos.add(new CargoDTO(y)));
	}

	public ClienteDTO(UUID id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ChamadoDTO> getChamados() {
		return chamados;
	}

	public void setChamados(List<ChamadoDTO> chamados) {
		this.chamados = chamados;
	}

	public Set<CargoDTO> getCargos() {
		return cargos;
	}

	public void setCargos(Set<CargoDTO> cargos) {
		this.cargos = cargos;
	}
	
	

}
