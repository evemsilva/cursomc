package com.nelioalves.cursomc.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

	private int codigo;
	private String descricao;

	private EstadoPagamento(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public static EstadoPagamento toEnum(Integer codigo) {

		if (codigo == null)
			return null;

		for (EstadoPagamento estado : EstadoPagamento.values()) {
			if (estado.getCodigo() == codigo) {
				return estado;
			}
		}

		throw new IllegalArgumentException("Codigo invalido: " + codigo);
	}

}
