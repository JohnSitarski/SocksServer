package com.jsitarski.networking.exceptions.socks;

public class ConnectionLostException extends Exception {

	private static final long serialVersionUID = -6042566610990383526L;
	private static final String ERROR = "Connection to socks proxy lost!";

	public ConnectionLostException() {
		super(ERROR);
	}
}
