package io.gitHub.AugustoMello09.helpDesk.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import io.gitHub.AugustoMello09.helpDesk.dto.CargoDTO;
import io.gitHub.AugustoMello09.helpDesk.dto.ClienteDTO;
import io.gitHub.AugustoMello09.helpDesk.entities.Cargo;
import io.gitHub.AugustoMello09.helpDesk.entities.Cliente;
import io.gitHub.AugustoMello09.helpDesk.repositories.CargoRepository;
import io.gitHub.AugustoMello09.helpDesk.repositories.ClienteRepository;
import io.gitHub.AugustoMello09.helpDesk.services.exceptions.DataIntegratyViolationException;
import io.gitHub.AugustoMello09.helpDesk.services.exceptions.ObjectNotFoundException;

@SpringBootTest
public class ClienteServiceTest {

	private static final String NOME = "José";

	private static final String EMAIL = "meuEmail@gmail.com";

	private static final UUID ID = UUID.fromString("148cf4fc-b379-4e25-8bf4-f73feb06befa");

	private Optional<Cliente> clienteOptional;

	private ClienteDTO clienteDto;

	private Cliente cliente;

	private Cargo cargo;

	private Optional<Cargo> cargoOptional;

	private CargoDTO cargoDTO;

	@Mock
	private ClienteRepository repository;

	@Mock
	private CargoRepository cargoRepository;

	@InjectMocks
	private ClienteService service;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		startCliente();
	}

	@DisplayName("Deve retornar um Cliente com sucesso.")
	@Test
	public void shouldReturnAClientWithSuccess() {
		when(repository.findById(ID)).thenReturn(clienteOptional);
		var response = service.findById(ID);
		assertNotNull(response);
		assertEquals(ClienteDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME, response.getNome());
		assertEquals(EMAIL, response.getEmail());
	}

	@DisplayName("Deve retornar Cliente não encontrado.")
	@Test
	public void shouldReturnClientNotFound() {
		when(repository.findById(ID)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.findById(ID));
	}

	@DisplayName("Deve alterar o email com sucesso quando cliente encontrado.")
	@Test
	void whenUpdateClientThenReturnClientDTO() {
		ClienteDTO objDto = new ClienteDTO(ID, NOME, "novoEmail@gmail.com");
		Cliente cliente = new Cliente(ID, NOME, EMAIL);

		when(repository.findById(ID)).thenReturn(Optional.of(cliente));
		when(repository.save(any(Cliente.class))).thenReturn(cliente);
		var result = service.updateEmail(objDto, ID);
		assertNotNull(result);
		assertEquals(ID, result.getId());
		assertEquals(objDto.getEmail(), result.getEmail());
		verify(repository, times(1)).findById(ID);
		verify(repository, times(1)).save(any(Cliente.class));
	}

	@DisplayName("Atualização Deve retornar Cliente não encontrado.")
	@Test
	public void shouldUpdateReturnClientNotFound() {
		when(repository.findById(ID)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.updateEmail(clienteDto, ID));
	}

	@DisplayName("Deve retornar Email já existe.")
	@Test
	public void shouldReturnDataIntegratyViolationExceptionWhenEmailExist() {
		UUID existingClientId = UUID.randomUUID();
		ClienteDTO clienteDTO = new ClienteDTO(UUID.randomUUID(), "José", "meuEmail@gmail.com");
		when(repository.findByEmail(clienteDTO.getEmail()))
				.thenReturn(Optional.of(new Cliente(existingClientId, "OutroNome", "meuEmail@gmail.com")));
		DataIntegratyViolationException exception = assertThrows(DataIntegratyViolationException.class, () -> {
			service.verificarEmailExistente(clienteDTO);
		});
		assertEquals("Email já existe", exception.getMessage());
	}

	@DisplayName("Não deve lançar exceção quando o e-mail existe para o mesmo cliente")
	@Test
	public void shouldNotThrowExceptionWhenEmailExistsForSameClient() {
		UUID clientId = UUID.randomUUID();
		ClienteDTO clienteDTO = new ClienteDTO(clientId, "José", "meuEmail@gmail.com");
		when(repository.findByEmail(clienteDTO.getEmail()))
				.thenReturn(Optional.of(new Cliente(clientId, "José", "meuEmail@gmail.com")));
		assertDoesNotThrow(() -> {
			service.verificarEmailExistente(clienteDTO);
		});
	}

	@DisplayName("Deve criar um cliente.")
	@Test
	public void testCreate() {
		when(repository.save(any(Cliente.class))).thenReturn(cliente);
		when(cargoRepository.findById(anyLong())).thenReturn(cargoOptional);
		ClienteDTO response = service.create(clienteDto);
		assertNotNull(response);
		assertEquals(NOME, response.getNome());
		assertEquals(EMAIL, response.getEmail());
		assertNotNull(response.getCargos());
		verify(repository, times(1)).save(any(Cliente.class));
	}

	@DisplayName("Deve associar um cargo na criação.")
	@Test
	public void atribuirCargo() {
		when(cargoRepository.findById(cargoDTO.getId())).thenReturn(Optional.of(cargo));
		service.atribuirCargo(cliente, clienteDto);
		assertEquals(1, cliente.getCargos().size());
		assertTrue(cliente.getCargos().contains(cargo));
	}

	@DisplayName("Não deve encontrar um cargo.")
	@Test
	public void CargoNotFound() {
		when(cargoRepository.findById(anyLong())).thenReturn(Optional.empty());
		ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> {
			service.atribuirCargo(cliente, clienteDto);
		});
		assertEquals("Cargo não encontrado", exception.getMessage());

	}

	private void startCliente() {
		cliente = new Cliente(ID, NOME, EMAIL);
		clienteDto = new ClienteDTO(ID, NOME, EMAIL);
		clienteOptional = Optional.of(cliente);
		cargo = new Cargo(1L, "ADM");
		cargoOptional = Optional.of(cargo);
		cargoDTO = new CargoDTO(1L, "ADM");
		cliente.setCargos(new HashSet<>());
		cliente.getCargos().add(cargo);
		clienteDto.setCargos(new HashSet<>());
		clienteDto.getCargos().add(cargoDTO);
	}

}
