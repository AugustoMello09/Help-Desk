package io.gitHub.AugustoMello09.helpDesk.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.gitHub.AugustoMello09.helpDesk.dto.CargoDTO;
import io.gitHub.AugustoMello09.helpDesk.dto.TecnicoDTO;
import io.gitHub.AugustoMello09.helpDesk.dto.TecnicoInsertDTO;
import io.gitHub.AugustoMello09.helpDesk.entities.Cargo;
import io.gitHub.AugustoMello09.helpDesk.entities.Chamado;
import io.gitHub.AugustoMello09.helpDesk.entities.Cliente;
import io.gitHub.AugustoMello09.helpDesk.entities.Tecnico;
import io.gitHub.AugustoMello09.helpDesk.entities.enums.StatusChamado;
import io.gitHub.AugustoMello09.helpDesk.repositories.CargoRepository;
import io.gitHub.AugustoMello09.helpDesk.repositories.ChamadoRepository;
import io.gitHub.AugustoMello09.helpDesk.repositories.TecnicoRepository;
import io.gitHub.AugustoMello09.helpDesk.services.exceptions.DataIntegratyViolationException;
import io.gitHub.AugustoMello09.helpDesk.services.exceptions.ObjectNotFoundException;

@SpringBootTest
public class TecnicoServiceTest {

	private static final String SENHA = "123";

	private static final String NOME = "José";

	private static final String EMAIL = "meuEmail@gmail.com";

	private static final UUID ID = UUID.fromString("148cf4fc-b379-4e25-8bf4-f73feb06befa");
	private static final UUID ID2 = UUID.fromString("151cf4fc-b379-4e25-8bf4-f73feb06befa");

	private Optional<Tecnico> tecnicoOptional;
	
	private TecnicoInsertDTO tecnicoInsertDTO;

	private TecnicoDTO tecnicoDto;

	private Tecnico tecnico;

	private Cargo cargo;

	private Optional<Cargo> cargoOptional;

	private CargoDTO cargoDTO;

	private Chamado chamado;

	private Cliente cliente;

	private Optional<Chamado> chamadoOptional;

	@Mock
	private ChamadoRepository chamadoRepository;

	@Mock
	private TecnicoRepository repository;

	@Mock
	private CargoRepository cargoRepository;

	@Mock
	private JavaMailSender emailSender;

	@InjectMocks
	private TecnicoService service;
	
	@Mock
	private BCryptPasswordEncoder passwordEncoder;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		startTecnico();
	}

	@DisplayName("Deve retornar um Técnico com sucesso.")
	@Test
	public void shouldReturnATecWithSuccess() {
		when(repository.findById(ID)).thenReturn(tecnicoOptional);
		var response = service.findById(ID);
		assertNotNull(response);
		assertEquals(TecnicoDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME, response.getNome());
		assertEquals(EMAIL, response.getEmail());
	}

	@DisplayName("Deve retornar Tecnico não encontrado.")
	@Test
	public void shouldReturnTecNotFound() {
		when(repository.findById(ID)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.findById(ID));
	}

	@DisplayName("Deve alterar o email com sucesso quando técnico encontrado.")
	@Test
	void whenUpdateClientThenReturnTecnicoDTO() {
		TecnicoDTO objDto = new TecnicoDTO(ID, NOME, "novoEmail@gmail.com");
		Tecnico tecnico = new Tecnico(ID, NOME, EMAIL, SENHA);

		when(repository.findById(ID)).thenReturn(Optional.of(tecnico));
		when(repository.save(any(Tecnico.class))).thenReturn(tecnico);
		var result = service.updateEmail(objDto, ID);
		assertNotNull(result);
		assertEquals(ID, result.getId());
		assertEquals(objDto.getEmail(), result.getEmail());
		verify(repository, times(1)).findById(ID);
		verify(repository, times(1)).save(any(Tecnico.class));
	}

	@DisplayName("Atualização Deve retornar Técnico não encontrado.")
	@Test
	public void shouldUpdateReturnTecNotFound() {
		when(repository.findById(ID)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.updateEmail(tecnicoDto, ID));
	}

	@DisplayName("Deve retornar Email já existe.")
	@Test
	public void shouldReturnDataIntegratyViolationExceptionWhenEmailExist() {
		UUID existingTecnicoId = UUID.randomUUID();
		TecnicoDTO tecnicoDTO = new TecnicoDTO(UUID.randomUUID(), "José", "meuEmail@gmail.com");
		when(repository.findByEmail(tecnicoDTO.getEmail()))
				.thenReturn(Optional.of(new Tecnico(existingTecnicoId, "OutroNome", "meuEmail@gmail.com", SENHA)));
		DataIntegratyViolationException exception = assertThrows(DataIntegratyViolationException.class, () -> {
			service.verificarEmailExistente(tecnicoDTO);
		});
		assertEquals("Email já existe", exception.getMessage());
	}

	@DisplayName("Não deve lançar exceção quando o e-mail existe para o mesmo técnico")
	@Test
	public void shouldNotThrowExceptionWhenEmailExistsForSameTec() {
		UUID tecnicoId = UUID.randomUUID();
		TecnicoDTO tecnicoDTO = new TecnicoDTO(tecnicoId, "José", "meuEmail@gmail.com");
		when(repository.findByEmail(tecnicoDTO.getEmail()))
				.thenReturn(Optional.of(new Tecnico(tecnicoId, "José", "meuEmail@gmail.com", SENHA)));
		assertDoesNotThrow(() -> {
			service.verificarEmailExistente(tecnicoDTO);
		});
	}

	@DisplayName("Deve criar um tecnico.")
	@Test
	public void testCreate() {
		when(repository.save(any(Tecnico.class))).thenReturn(tecnico);
		when(cargoRepository.findById(anyLong())).thenReturn(cargoOptional);
		TecnicoDTO response = service.create(tecnicoInsertDTO);
		assertNotNull(response);
		assertNotNull(response.getCargos());
		verify(repository, times(1)).save(any(Tecnico.class));
	}

	@DisplayName("Deve associar um cargo na criação.")
	@Test
	public void atribuirCargo() {
		when(cargoRepository.findById(cargoDTO.getId())).thenReturn(Optional.of(cargo));
		service.atribuirCargo(tecnico, tecnicoDto);
		assertEquals(1, tecnico.getCargos().size());
		assertTrue(tecnico.getCargos().contains(cargo));
	}

	@DisplayName("Não deve encontrar um cargo.")
	@Test
	public void CargoNotFound() {
		when(cargoRepository.findById(anyLong())).thenReturn(Optional.empty());
		ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> {
			service.atribuirCargo(tecnico, tecnicoDto);
		});
		assertEquals("Cargo não encontrado", exception.getMessage());

	}

	@DisplayName("Deve aceitar o chamado.")
	@Test
	public void shuoldAcceptChamado() {
		when(chamadoRepository.findById(anyLong())).thenReturn(chamadoOptional);
		when(repository.findById(ID)).thenReturn(Optional.of(tecnico));
		when(chamadoRepository.save(any(Chamado.class))).thenReturn(chamado);
		var response = service.aceitarChamado(1L, ID);
		assertNotNull(response);
		verify(chamadoRepository, times(1)).save(any(Chamado.class));
		verify(emailSender, times(1)).send(any(SimpleMailMessage.class));
	}

	@DisplayName("Não encontrar o chamado.")
	@Test
	public void shouldNotFoundChamado() {
		when(chamadoRepository.findById(anyLong())).thenReturn(Optional.empty());
		ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> {
			service.aceitarChamado(1L, ID);
		});
		assertEquals("Chamado não encontrado", exception.getMessage());
	}

	@DisplayName("Não encontrar o Tecnico.")
	@Test
	public void shouldNotFoundTecnico() {
		when(chamadoRepository.findById(anyLong())).thenReturn(Optional.of(chamado));
		when(repository.findById(ID)).thenReturn(Optional.empty());
		ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> {
			service.aceitarChamado(1L, ID);
		});
		assertEquals("Técnico não encontrado", exception.getMessage());
	}

	@DisplayName("Somente uma pessoa pode aceitar um chamado.")
	@Test
	public void shouldReturnEsteChamado() {
		Cliente cl = new Cliente(ID, NOME, EMAIL, SENHA);
		Tecnico c2 = new Tecnico(ID, NOME, EMAIL, SENHA);
		Chamado ch1 = new Chamado(1L, null, EMAIL, null, StatusChamado.ANDAMENTO, c2, cl);
		Tecnico c3 = new Tecnico(ID2, NOME, EMAIL, SENHA);
		when(chamadoRepository.findById(anyLong())).thenReturn(Optional.of(ch1));
		DataIntegratyViolationException exception = assertThrows(DataIntegratyViolationException.class, () -> {
			service.aceitarChamado(1L, c3.getId());
		});
		assertEquals("Este chamado já está em andamento e possui um técnico associado.", exception.getMessage());

	}

	@DisplayName("Deve finalizar o chamado.")
	@Test
	public void shouldFinishChamado() {
		when(chamadoRepository.findById(anyLong())).thenReturn(chamadoOptional);
		when(repository.findById(ID)).thenReturn(Optional.of(tecnico));
		when(chamadoRepository.save(any(Chamado.class))).thenReturn(chamado);
		var response = service.finalizarChamado(1L, ID);
		assertNotNull(response);
		verify(chamadoRepository, times(1)).save(any(Chamado.class));
		verify(emailSender, times(1)).send(any(SimpleMailMessage.class));
	}

	@DisplayName("Não encontrar o chamado.")
	@Test
	public void shouldNotFoundChamado2() {
		when(chamadoRepository.findById(anyLong())).thenReturn(Optional.empty());
		ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> {
			service.finalizarChamado(1L, ID);
		});
		assertEquals("Chamado não encontrado", exception.getMessage());
	}

	@DisplayName("Somente o técnico associado pode encerrar o chamado.")
	@Test
	public void shouldReturnSomenteChamado() {
		Cliente cl = new Cliente(ID, NOME, EMAIL, SENHA);
		Tecnico c2 = new Tecnico(ID, NOME, EMAIL, SENHA);
		Chamado ch1 = new Chamado(1L, null, EMAIL, null, StatusChamado.ANDAMENTO, c2, cl);
		Tecnico c3 = new Tecnico(ID2, NOME, EMAIL, SENHA);
		when(chamadoRepository.findById(anyLong())).thenReturn(Optional.of(ch1));
		DataIntegratyViolationException exception = assertThrows(DataIntegratyViolationException.class, () -> {
			service.finalizarChamado(1L, c3.getId());
		});
		assertEquals("Somente o técnico associado pode finalizar este chamado aberto.", exception.getMessage());

	}
	
	@DisplayName("Deve dar erro ao enviar o email ao aceitar o chamado.")
	@Test
	public void shouldHandleEmailSendingError() {
		when(chamadoRepository.findById(anyLong())).thenReturn(Optional.of(chamado));
		when(repository.findById(ID)).thenReturn(Optional.of(tecnico));
		doThrow(new MailSendException("Erro ao enviar e-mail")).when(emailSender).send(any(SimpleMailMessage.class));
		assertThrows(DataIntegratyViolationException.class, () -> {
			service.aceitarChamado(1L, ID);
		});
	}
	
	@DisplayName("Deve dar erro ao enviar o email ao finalizar o chamado.")
	@Test
	public void shouldHandleEmailSendingErrorFinish() {
		when(chamadoRepository.findById(anyLong())).thenReturn(Optional.of(chamado));
		when(repository.findById(ID)).thenReturn(Optional.of(tecnico));
		doThrow(new MailSendException("Erro ao enviar e-mail")).when(emailSender).send(any(SimpleMailMessage.class));
		assertThrows(DataIntegratyViolationException.class, () -> {
			service.finalizarChamado(1L, ID);
		});

	}
	
	@DisplayName("Deve retornar a lista de chamados.")
	@Test
	public void shouldReturnListChamados() {
		List<Chamado> cha = new ArrayList<>();
		cha.add(chamado);
		when(chamadoRepository.findAll()).thenReturn(cha);
		var response = service.findAllChamados();
		assertNotNull(response);
	}

	private void startTecnico() {
		tecnicoInsertDTO = new TecnicoInsertDTO(passwordEncoder.encode("123"));
		cliente = new Cliente(ID, NOME, EMAIL, passwordEncoder.encode("123"));
		tecnico = new Tecnico(ID, NOME, EMAIL, passwordEncoder.encode("123"));
		tecnicoDto = new TecnicoDTO(ID, NOME, EMAIL);
		tecnicoOptional = Optional.of(tecnico);
		cargo = new Cargo(1L, "ADM");
		cargoOptional = Optional.of(cargo);
		cargoDTO = new CargoDTO(1L, "ADM");
		tecnico.setCargos(new HashSet<>());
		tecnico.getCargos().add(cargo);
		tecnicoDto.setCargos(new HashSet<>());
		tecnicoDto.getCargos().add(cargoDTO);
		chamado = new Chamado(1L, LocalDateTime.now(), "oi", null, StatusChamado.ABERTO, tecnico, cliente);
		chamadoOptional = Optional.of(chamado);
	}

}
