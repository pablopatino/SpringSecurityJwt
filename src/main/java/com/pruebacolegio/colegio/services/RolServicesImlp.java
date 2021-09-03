package com.pruebacolegio.colegio.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.pruebacolegio.colegio.modelos.Rol;
import com.pruebacolegio.colegio.modelos.enums.RolNombre;
import com.pruebacolegio.colegio.repositorios.RepositorioRol;

@Service
@Transactional
public class RolServicesImlp implements RolServices {

	private final RepositorioRol repositorioRol;

	public RolServicesImlp(RepositorioRol repositorioRol) {
		this.repositorioRol = repositorioRol;
	}

	@Override
	public Optional<Rol> getRolByNombre(RolNombre nombreRol) {
		return this.repositorioRol.findByRolNombre(nombreRol);
	}

	@Override
	public Rol saveRol(Rol rol) {
		return this.repositorioRol.save(rol);
	}

}
