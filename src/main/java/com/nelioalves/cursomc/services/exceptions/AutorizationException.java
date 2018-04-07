package com.nelioalves.cursomc.services.exceptions;

public class AutorizationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AutorizationException(String msg) {
		super(msg);
	}

	public AutorizationException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
