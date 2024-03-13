package io.gitHub.AugustoMello09.helpDesk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.gitHub.AugustoMello09.helpDesk.entities.Usuario;
import io.gitHub.AugustoMello09.helpDesk.repositories.UsuarioRepository;


@Service
public class AuthorizationService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario entity = repository.findByEmail(email);
        if (entity == null) {
            throw new IllegalArgumentException("Email não encontrado");
        }
        return entity;
	}

}
