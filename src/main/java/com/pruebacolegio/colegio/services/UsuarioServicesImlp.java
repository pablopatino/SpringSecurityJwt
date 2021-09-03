package com.pruebacolegio.colegio.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.pruebacolegio.colegio.modelos.Usuario;
import com.pruebacolegio.colegio.repositorios.RepositorioUsuario;

@Service
@Transactional
public class UsuarioServicesImlp implements UsuarioServices {

	private final RepositorioUsuario repositorioUsuario;

	public UsuarioServicesImlp(RepositorioUsuario repositorioUsuario) {
		this.repositorioUsuario = repositorioUsuario;
	}
	
	@Override
	public Optional<Usuario> getByNombreUsuario(String userName) {
		return this.repositorioUsuario.findByNombreUsuario(userName);
	}
	
	@Override
	public Usuario saveUsuario(Usuario usuario) {
		return this.repositorioUsuario.save(usuario);
	}
	
	
	
}
