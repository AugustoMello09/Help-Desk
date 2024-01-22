package io.gitHub.AugustoMello09.helpDesk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.gitHub.AugustoMello09.helpDesk.dto.AuthenticationDTO;
import io.gitHub.AugustoMello09.helpDesk.dto.DadosTokenJwtDTO;
import io.gitHub.AugustoMello09.helpDesk.entities.Usuario;
import io.gitHub.AugustoMello09.helpDesk.services.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping(value = "/login")
	public ResponseEntity<?> login(@Valid @RequestBody AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var authentication = authenticationManager.authenticate(usernamePassword);
        var tokenJWT = tokenService.generateToken((Usuario) authentication.getPrincipal());
		return ResponseEntity.ok().body(new DadosTokenJwtDTO(tokenJWT));
	}

}
