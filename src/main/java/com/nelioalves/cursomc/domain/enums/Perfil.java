package com.nelioalves.cursomc.domain.enums;

public enum Perfil {

	CLIENTE(1, "ROLE_CLIENTE"), ADMIN(2, "ROLE_ADMIN");

	private int codigo;
	private String descricao;

	private Perfil(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Perfil toEnum(Integer codigo) {

		if (codigo == null)
			return null;

		for (Perfil tipoCliente : Perfil.values()) {
			if (tipoCliente.getCodigo() == codigo) {
				return tipoCliente;
			}
		}

		throw new IllegalArgumentException("Codigo invalido: " + codigo);
	}

}
