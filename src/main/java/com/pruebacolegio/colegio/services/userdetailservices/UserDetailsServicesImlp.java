package com.pruebacolegio.colegio.services.userdetailservices;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pruebacolegio.colegio.modelos.Usuario;
import com.pruebacolegio.colegio.modelos.UsuarioPrincipal;
import com.pruebacolegio.colegio.services.UsuarioServices;

@Service
public class UserDetailsServicesImlp implements UserDetailsService {

	private final UsuarioServices usuarioServices;

	public UserDetailsServicesImlp(UsuarioServices usuarioServices) {
		this.usuarioServices = usuarioServices;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = this.usuarioServices.getByNombreUsuario(username).get();
		return UsuarioPrincipal.build(usuario);
	}

}
