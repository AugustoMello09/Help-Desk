package io.gitHub.AugustoMello09.helpDesk.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.gitHub.AugustoMello09.helpDesk.dto.ChamadoDTO;
import io.gitHub.AugustoMello09.helpDesk.dto.ClienteDTO;
import io.gitHub.AugustoMello09.helpDesk.dto.ClienteInfDTO;
import io.gitHub.AugustoMello09.helpDesk.dto.ClienteInsertDTO;
import io.gitHub.AugustoMello09.helpDesk.dto.TecnicoInfDTO;
import io.gitHub.AugustoMello09.helpDesk.entities.enums.StatusChamado;
import io.gitHub.AugustoMello09.helpDesk.repositories.ChamadoRepository;
import io.gitHub.AugustoMello09.helpDesk.services.ClienteService;

@AutoConfigureMockMvc
@SpringBootTest
public class ClienteControllerTest {

	private static final String NOME = "José";

	private static final String EMAIL = "meuEmail@gmail.com";

	private static final UUID ID = UUID.fromString("148cf4fc-b379-4e25-8bf4-f73feb06befa");

	private ClienteDTO clienteDTO;

	private ChamadoDTO chamadoDTO;

	private TecnicoInfDTO tecnicoInfDTO;

	private ClienteInfDTO clienteInfDTO;
	
	private ClienteInsertDTO clienteInsertDTO;

	@Mock
	private ChamadoRepository chamadoRepository;

	@Mock
	private ClienteService service;

	@InjectMocks
	private ClienteController controller;


	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		startCliente();
	}

	@DisplayName("Deve retornar um usuário. ")
	@Test
	public void shouldControllerReturnFindById() {
		when(service.findById(ID)).thenReturn(clienteDTO);
		var response = controller.findById(ID);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ClienteDTO.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NOME, response.getBody().getNome());
		assertEquals(EMAIL, response.getBody().getEmail());
	}

	@DisplayName("Deve criar um usuário. ")
	@Test
	public void shouldReturnCreatedClienteDTOOnController() {
		when(service.create(any(ClienteInsertDTO.class))).thenReturn(clienteDTO);
		var response = controller.create(clienteInsertDTO);
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NOME, response.getBody().getNome());
		assertEquals(EMAIL, response.getBody().getEmail());
		verify(service).create(clienteInsertDTO);
	}

	@DisplayName("Deve atualizar o email do usuário. ")
	@Test
	public void shouldReturnUpdateEmailClienteDTOOnController() {
		when(service.updateEmail(clienteDTO, ID)).thenReturn(clienteDTO);
		var response = controller.update(clienteDTO, ID);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NOME, response.getBody().getNome());
		assertEquals(EMAIL, response.getBody().getEmail());
		verify(service).updateEmail(clienteDTO, ID);
	}
	
	@DisplayName("Deve retornar o Chamado. ")
	@Test
	public void shouldReturnChamado() {
		when(service.buscarChamado(anyLong())).thenReturn(chamadoDTO);
		var response = controller.buscarChamado(1L);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());	
	}
	
	@DisplayName("Deve criar um chamado. ")
	@Test
	public void shouldCreateChamado() {
		when(service.criarChamado(chamadoDTO, ID)).thenReturn(chamadoDTO);
		var response = controller.criarChamado(chamadoDTO, ID);
		assertNotNull(response);
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	@DisplayName("Deve retornar uma List chamado. ")
	@Test
	public void shouldReturnListChamadoDTO() {
		List<ChamadoDTO> chamdosDTO = new ArrayList<>();
		when(service.findAllMeuChamados(NOME)).thenReturn(chamdosDTO);
		var response = controller.meusChamados(NOME);
		assertNotNull(response);
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(HttpStatus.OK, response.getStatusCode());		
	}

	public void startCliente() {
		clienteInsertDTO = new ClienteInsertDTO("123");
		clienteDTO = new ClienteDTO(ID, NOME, EMAIL);
		tecnicoInfDTO = new TecnicoInfDTO(ID, NOME, EMAIL);
		clienteInfDTO = new ClienteInfDTO(ID, NOME, EMAIL);
		chamadoDTO = new ChamadoDTO(1L, LocalDateTime.now(), "oi", null, StatusChamado.ABERTO, clienteInfDTO,
				tecnicoInfDTO);
	}

}
