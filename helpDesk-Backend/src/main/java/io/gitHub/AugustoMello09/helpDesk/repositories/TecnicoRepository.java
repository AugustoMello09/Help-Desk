package io.gitHub.AugustoMello09.helpDesk.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.gitHub.AugustoMello09.helpDesk.entities.Tecnico;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, UUID>{

	Optional<Tecnico> findByEmail(String email);
}
