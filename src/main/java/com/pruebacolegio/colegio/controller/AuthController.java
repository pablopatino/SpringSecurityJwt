package com.pruebacolegio.colegio.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebacolegio.colegio.dto.security.JwtDTO;
import com.pruebacolegio.colegio.dto.security.LoginUsuario;
import com.pruebacolegio.colegio.dto.security.NuevoUsuario;
import com.pruebacolegio.colegio.modelos.Rol;
import com.pruebacolegio.colegio.modelos.Usuario;
import com.pruebacolegio.colegio.modelos.enums.RolNombre;
import com.pruebacolegio.colegio.security.jwt.JwtProvider;
import com.pruebacolegio.colegio.services.RolServices;
import com.pruebacolegio.colegio.services.UsuarioServices;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UsuarioServices usaServices;
	
	@Autowired
	RolServices rolServices;
	
	@Autowired
	JwtProvider jwtProvider;
	
	
	@PostMapping("/nuevo")
	public ResponseEntity<?> nuevoUsuario(@RequestBody NuevoUsuario nuevoUsuario){
		Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));
		Set<Rol> roles = new HashSet<>();
		roles.add(rolServices.getRolByNombre(RolNombre.ROLE_USER).get());
		if (nuevoUsuario.getRoles().contains("admin")) {
			roles.add(rolServices.getRolByNombre(RolNombre.ROLE_ADMIN).get());
		}
		usuario.setRoles(roles);
		usaServices.saveUsuario(usuario);
		return ResponseEntity.status(HttpStatus.OK).body(usuario);
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtDTO> login(@RequestBody LoginUsuario loginUsuario){
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity(jwtDTO, HttpStatus.OK);
		
	}
	
}
