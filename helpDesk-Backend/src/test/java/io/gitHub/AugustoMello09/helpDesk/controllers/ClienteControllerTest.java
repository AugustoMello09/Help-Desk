package io.gitHub.AugustoMello09.helpDesk.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import io.gitHub.AugustoMello09.helpDesk.dto.ClienteDTO;
import io.gitHub.AugustoMello09.helpDesk.services.ClienteService;

@AutoConfigureMockMvc
@SpringBootTest
public class ClienteControllerTest {

	private static final String NOME = "José";

	private static final String EMAIL = "meuEmail@gmail.com";

	private static final UUID ID = UUID.fromString("148cf4fc-b379-4e25-8bf4-f73feb06befa");

	private ClienteDTO clienteDTO;

	@Mock
	private ClienteService service;

	@InjectMocks
	private ClienteController controller;

	@Autowired
	private MockMvc mockMvc;

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
		when(service.create(any(ClienteDTO.class))).thenReturn(clienteDTO);
		var response = controller.create(clienteDTO);
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NOME, response.getBody().getNome());
		assertEquals(EMAIL, response.getBody().getEmail());
		verify(service).create(clienteDTO);
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
	
	@DisplayName("Deve retornar o status OK. ")
	@Test
	public void shouldReturnOkStatus() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/cliente"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("ok"));
	}

	public void startCliente() {
		clienteDTO = new ClienteDTO(ID, NOME, EMAIL);
	}

}
