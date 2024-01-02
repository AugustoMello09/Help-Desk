package io.gitHub.AugustoMello09.helpDesk.controllers;

import java.net.URI;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping(value = "cliente")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@GetMapping
	public String status() {
		return "ok";
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable UUID id) {
		var cliente = service.findById(id);
		return ResponseEntity.ok().body(cliente);
	}

	@PostMapping
	public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO dto) {
		var newObj = service.create(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}
	
	@PatchMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@RequestBody ClienteDTO dto,@PathVariable UUID id){
		var newObj = service.updateEmail(dto, id);
		return ResponseEntity.ok().body(newObj);
	}
	
	@GetMapping(value = "/buscarChamado/{id}")
	public ResponseEntity<ChamadoDTO> buscarChamado(@PathVariable Long id){
		var response = service.buscarChamado(id);
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping(value = "/fazerChamado/{id}")
	public ResponseEntity<ChamadoDTO> criarChamado(@RequestBody ChamadoDTO dto, @PathVariable UUID id){
		var newObj = service.crairChamado(dto, id);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).body(newObj);
	}

}
