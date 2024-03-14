package com.val.config.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import com.val.config.RSA.RSAKeyRecord;
import com.val.dto.TokenType;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class JwtAccessTokenFilter extends OncePerRequestFilter {

	@Autowired
	RSAKeyRecord rsaKeyRecord;

	@Autowired
	private JwtTokenUtils jwtTokenUtils;

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			log.info("[JwtAccessTokenFilter:doFilterInternal] :: Started ");

			log.info("[JwtAccessTokenFilter:doFilterInternal]Filtering the Http Request:{}", request.getRequestURI());

			final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
			JwtDecoder jwtDecoder = NimbusJwtDecoder.withPublicKey(rsaKeyRecord.rsaPublicKey()).build();

			if (!authHeader.startsWith(TokenType.Bearer.name())) {
				filterChain.doFilter(request, response);
				return;
			}

			String token = authHeader.substring(7);
			Jwt decodedToken = jwtDecoder.decode(token);

			String username = jwtTokenUtils.getUserName(decodedToken);

			if (!username.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = jwtTokenUtils.getUserDetails(username);
				if (jwtTokenUtils.isTokenValid(decodedToken, userDetails)) {
					SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
					UsernamePasswordAuthenticationToken createdToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					createdToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					securityContext.setAuthentication(createdToken);
					SecurityContextHolder.setContext(securityContext);
				}
			}
			log.info("[JwtAccessTokenFilter:doFilterInternal] Completed");
			filterChain.doFilter(request, response);
		} catch (JwtValidationException jwtValidationException) {
			response.setStatus(HttpStatus.FORBIDDEN.value());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			log.error("[JwtAccessTokenFilter:doFilterInternal] Exception due to :{}",
					jwtValidationException.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, jwtValidationException.getMessage());
		}
	}
}