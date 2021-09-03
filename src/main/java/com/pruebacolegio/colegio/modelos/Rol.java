package com.pruebacolegio.colegio.modelos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.pruebacolegio.colegio.modelos.enums.RolNombre;

@Entity
public class Rol implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	@Enumerated(EnumType.STRING)
	private RolNombre rolNombre;

	public Rol() {
	}

	public Rol(RolNombre rolNombre) {
		this.rolNombre = rolNombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RolNombre getRolNombre() {
		return rolNombre;
	}

	public void setRolNombre(RolNombre rolNombre) {
		this.rolNombre = rolNombre;
	}

	private static final long serialVersionUID = 1L;

}
