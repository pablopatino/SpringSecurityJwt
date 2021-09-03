package com.pruebacolegio.colegio.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pruebacolegio.colegio.services.userdetailservices.UserDetailsServicesImlp;

//Se ejecuta por cada peticion y valida el token

public class JwtTokenFilter extends OncePerRequestFilter {

	@Autowired
	private  JwtProvider jwtProvider;
	@Autowired
	private  UserDetailsServicesImlp userDetailsServicesImlp;
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
		try {
			String token = getToken(req);
			if (token != null && this.jwtProvider.validatedToken(token) ) {
				String nombreUsuario = jwtProvider.getNombreUsuarioPorElToken(token);
				UserDetails userDetails = this.userDetailsServicesImlp.loadUserByUsername(nombreUsuario);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
			
			
		} catch (Exception e) {
			e.getMessage();
		}
		
		filterChain.doFilter(req, res);
	}

	private String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer")) {
			return header.replace("Bearer", "");
		}
		return null;
	}

}
