package br.com.caelum.fj27.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.fj27.DTO.AuthenticationTokenOutputDto;
import br.com.caelum.fj27.dto.input.LoginInputDto;
import br.com.caelum.fj27.security.jwt.TokenManager;

@RestController
@RequestMapping("/api/auth")
public class UserAuthenticatorController {

	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private TokenManager tokenManager;

	@PostMapping
	public ResponseEntity<?> authenticate(@RequestBody LoginInputDto loginInfo) {

		UsernamePasswordAuthenticationToken authenticationToken = loginInfo.build();

		try {
			Authentication authentication = authManager.authenticate(authenticationToken);

			String jwt = tokenManager.generateToken(authentication);

			AuthenticationTokenOutputDto tokenResponse = new AuthenticationTokenOutputDto("Bearer", jwt);

			return ResponseEntity.ok(tokenResponse);

		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}

	}
}
