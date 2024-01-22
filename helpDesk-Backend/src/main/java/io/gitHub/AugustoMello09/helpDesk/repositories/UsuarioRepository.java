package io.gitHub.AugustoMello09.helpDesk.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.gitHub.AugustoMello09.helpDesk.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
	Usuario findByEmail(String email);
}
