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
import io.gitHub.AugustoMello09.helpDesk.dto.ClienteDTO;
import io.gitHub.AugustoMello09.helpDesk.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@CrossOrigin("*")
@Tag(name = "Help Desk Cliente endpoint")
@RestController
@RequestMapping(value = "cliente")
public class ClienteController {

	@Autowired
	private ClienteService service;
	
	@Operation(summary = "Status.")
	@GetMapping
	public String status() {
		return "ok";
	}

	@Operation(summary = "Busca um Cliente no banco de dados.")
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable UUID id) {
		var cliente = service.findById(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@Operation(summary = "Cria um Cliente no banco de dados.")
	@PostMapping
	public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO dto) {
		var newObj = service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}
	
	@Operation(summary = "Busca um Cliente no banco de dados e atualiza o email.")
	@PatchMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@RequestBody ClienteDTO dto,@PathVariable UUID id){
		var newObj = service.updateEmail(dto, id);
		return ResponseEntity.ok().body(newObj);
	}
	
	@Operation(summary = "Busca um chamado.")
	@GetMapping(value = "/buscarChamado/{id}")
	public ResponseEntity<ChamadoDTO> buscarChamado(@PathVariable Long id){
		var response = service.buscarChamado(id);
		return ResponseEntity.ok().body(response);
	}
	
	@Operation(summary = "Cria um chamado.")
	@PostMapping(value = "/fazerChamado/{id}")
	public ResponseEntity<ChamadoDTO> criarChamado(@RequestBody ChamadoDTO dto, @PathVariable UUID id){
		var newObj = service.criarChamado(dto, id);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}
	
	@GetMapping(value = "/meusChamados/{nome}")
	public ResponseEntity<List<ChamadoDTO>> meusChamados(@PathVariable String nome) {
		List<ChamadoDTO> lista = service.findAllMeuChamados(nome);
		return ResponseEntity.ok().body(lista);
	}

}
