package br.com.caelum.fj27.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.caelum.fj27.security.jwt.TokenManager;
import br.com.caelum.fj27.security.service.UsersService;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private TokenManager tokenManager;
	private UsersService userService;

	public JwtAuthenticationFilter(TokenManager tokenManager, UsersService userService) {
		super();
		this.tokenManager = tokenManager;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		String jwt = getTokenFromRequest(request);

		if (tokenManager.isValid(jwt)) {
			Long userId = tokenManager.getUserIdFromToken(jwt);
			UserDetails userDetails = userService.loadUserById(userId);

			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
					null, userDetails.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		chain.doFilter(request, response);
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

}
