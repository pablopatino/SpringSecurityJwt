package com.pruebacolegio.colegio.services;

import java.util.Optional;

import com.pruebacolegio.colegio.modelos.Usuario;

public interface UsuarioServices {

	public Optional<Usuario> getByNombreUsuario(String userName);
	public Usuario saveUsuario(Usuario usuario);
}
