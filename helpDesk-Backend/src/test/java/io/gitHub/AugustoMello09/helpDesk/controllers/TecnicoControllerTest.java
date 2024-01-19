package io.gitHub.AugustoMello09.helpDesk.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
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

import io.gitHub.AugustoMello09.helpDesk.dto.ChamadoDTO;
import io.gitHub.AugustoMello09.helpDesk.dto.ClienteInfDTO;
import io.gitHub.AugustoMello09.helpDesk.dto.TecnicoDTO;
import io.gitHub.AugustoMello09.helpDesk.dto.TecnicoInfDTO;
import io.gitHub.AugustoMello09.helpDesk.entities.enums.StatusChamado;
import io.gitHub.AugustoMello09.helpDesk.services.TecnicoService;

@AutoConfigureMockMvc
@SpringBootTest
public class TecnicoControllerTest {

	private static final String NOME = "José";

	private static final String EMAIL = "meuEmail@gmail.com";

	private static final UUID ID = UUID.fromString("148cf4fc-b379-4e25-8bf4-f73feb06befa");

	private TecnicoDTO tecnicoDTO;
	
	private ChamadoDTO chamadoDTO;
	
	private TecnicoInfDTO tecnicoInfDTO;
	
	private ClienteInfDTO clienteInfDTO;

	@Mock
	private TecnicoService service;

	@InjectMocks
	private TecnicoController controller;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		startTecnico();
	}

	@DisplayName("Deve retornar um técnico. ")
	@Test
	public void shouldControllerReturnFindById() {
		when(service.findById(ID)).thenReturn(tecnicoDTO);
		var response = controller.findById(ID);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(TecnicoDTO.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NOME, response.getBody().getNome());
		assertEquals(EMAIL, response.getBody().getEmail());
	}

	@DisplayName("Deve criar um Técnico. ")
	@Test
	public void shouldReturnCreatedTecnicoDTOOnController() {
		when(service.create(any(TecnicoDTO.class))).thenReturn(tecnicoDTO);
		var response = controller.create(tecnicoDTO);
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NOME, response.getBody().getNome());
		assertEquals(EMAIL, response.getBody().getEmail());
		verify(service).create(tecnicoDTO);
	}

	@DisplayName("Deve atualizar o email do Técnico. ")
	@Test
	public void shouldReturnUpdateEmailTecnicoDTOOnController() {
		when(service.updateEmail(tecnicoDTO, ID)).thenReturn(tecnicoDTO);
		var response = controller.update(tecnicoDTO, ID);
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ID, response.getBody().getId());
		assertEquals(NOME, response.getBody().getNome());
		assertEquals(EMAIL, response.getBody().getEmail());
		verify(service).updateEmail(tecnicoDTO, ID);
	}
	
	@DisplayName("Deve retornar o status OK. ")
	@Test
	public void shouldReturnOkStatus() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/tecnico"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("ok"));
	}
	
	@DisplayName("Deve aceitar um chamado. ")
	@Test
	public void shouldAcceptChamado() {
		when(service.aceitarChamado(1L, ID)).thenReturn(chamadoDTO);
		var response = controller.aceitarChamado(1L, ID);
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
				
	}
	
	@DisplayName("Deve finalizar um chamado. ")
	@Test
	public void shouldFinishChamado() {
		when(service.finalizarChamado(1L, ID)).thenReturn(chamadoDTO);
		var response = controller.finalizarChamado(1L, ID);
		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());	
	}
	
	@DisplayName("Deve retornar uma lista de chamados. ")
	@Test
	public void shouldReturnListChamados() {
		List<ChamadoDTO> chamadosLista = new ArrayList<>();
		chamadosLista.add(chamadoDTO);
		when(service.findAllChamados()).thenReturn(chamadosLista);
		var response = controller.listaDeChamados();
		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());	
	}

	public void startTecnico() {
		tecnicoDTO = new TecnicoDTO(ID, NOME, EMAIL);
		tecnicoInfDTO = new TecnicoInfDTO(ID, NOME, EMAIL);
		clienteInfDTO = new ClienteInfDTO(ID, NOME, EMAIL);
		chamadoDTO = new ChamadoDTO(1L, null, EMAIL, null, StatusChamado.ABERTO, clienteInfDTO, tecnicoInfDTO);
		List<ChamadoDTO> chamadosLista = new ArrayList<>();
		chamadosLista.add(chamadoDTO);
	}

}
