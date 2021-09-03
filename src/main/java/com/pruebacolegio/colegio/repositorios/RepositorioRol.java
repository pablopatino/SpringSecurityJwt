package com.pruebacolegio.colegio.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pruebacolegio.colegio.modelos.Rol;
import com.pruebacolegio.colegio.modelos.enums.RolNombre;

@Repository
public interface RepositorioRol extends JpaRepository<Rol, Integer> {

	Optional<Rol> findByRolNombre(RolNombre rolNombre);
	
}
