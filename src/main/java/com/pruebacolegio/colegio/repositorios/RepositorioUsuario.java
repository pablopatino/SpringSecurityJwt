package com.pruebacolegio.colegio.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pruebacolegio.colegio.modelos.Usuario;

@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByNombreUsuario(String nombre);
}
