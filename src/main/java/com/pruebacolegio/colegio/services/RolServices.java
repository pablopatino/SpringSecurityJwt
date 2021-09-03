package com.pruebacolegio.colegio.services;

import java.util.Optional;

import com.pruebacolegio.colegio.modelos.Rol;
import com.pruebacolegio.colegio.modelos.enums.RolNombre;

public interface RolServices {

	Optional<Rol> getRolByNombre(RolNombre rolNombre );
	Rol saveRol(Rol rol);
}
