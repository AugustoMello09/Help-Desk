package io.gitHub.AugustoMello09.helpDesk.controllers;

import java.net.URI;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.gitHub.AugustoMello09.helpDesk.dto.ChamadoDTO;
import io.gitHub.AugustoMello09.helpDesk.dto.TecnicoDTO;
import io.gitHub.AugustoMello09.helpDesk.services.TecnicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin("*")
@Tag(name = "Help Desk Técnico endpoint")
@RestController
@RequestMapping(value = "tecnico")
public class TecnicoController {

	@Autowired
	private TecnicoService service;
	
	@Operation(summary = "Status.")
	@GetMapping
	public String status() {
		return "ok";
	}
	
	@Operation(summary = "Busca um técnico no banco de dados.")
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable UUID id) {
		var tecnico = service.findById(id);
		return ResponseEntity.ok().body(tecnico);
	}
	
	@Operation(summary = "Cria um técnico no banco de dados.")
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@RequestBody TecnicoDTO dto) {
		var newObj = service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}
	
	@Operation(summary = "Busca um técnico no banco de dados e atualiza o email.")
	@PatchMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@RequestBody TecnicoDTO dto, @PathVariable UUID id) {
		var newObj = service.updateEmail(dto, id);
		return ResponseEntity.ok().body(newObj);
	}
	
	@Operation(summary = "Busca um chamado e aceita.")
	@PostMapping(value = "/aceitarChamado/{id}/tecnicoId/{idTecnico}")
	public ResponseEntity<ChamadoDTO> aceitarChamado(@PathVariable Long id, @PathVariable UUID idTecnico) {
		var newObj = service.aceitarChamado(id, idTecnico);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}
	
	@Operation(summary = "Busca um chamado que estava trabalhando e finaliza.")
	@PostMapping(value = "/finalizarChamado/{id}/tecnicoId/{idTecnico}")
	public ResponseEntity<ChamadoDTO> finalizarChamado(@PathVariable Long id, @PathVariable UUID idTecnico) {
		var newObj = service.finalizarChamado(id, idTecnico);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}
	
	@Operation(summary = "Busca os chamados em aberto, andamento e finalizados")
	@GetMapping(value = "/listaChamados")
	public ResponseEntity<List<ChamadoDTO>> listaDeChamados(){
		List<ChamadoDTO> chamados = service.findAllChamados();
		return ResponseEntity.ok().body(chamados);
	}

}
