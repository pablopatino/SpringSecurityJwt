package com.pruebacolegio.colegio.error;

import java.io.Serializable;

public class Error implements Serializable {

	private int codigo;
	private String mensaje;

	public Error(int codigo, String mensaje) {
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	private static final long serialVersionUID = 1L;

}
