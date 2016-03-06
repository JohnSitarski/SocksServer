package com.jsitarski.networking.wrappers.socks;

public enum ClientCommandCode {
	ESTABLISH_STREAM_CONNECTION(1, "establish a TCP/IP stream connection"),

	ESTABLISH_PORT_BINDING(2, "establish a TCP/IP port binding"),

	ASSOCIATE_UDP_PORT(3, "associate a UDP port");

	private int code;
	private String message;

	ClientCommandCode(final int code, final String message) {
		this.setCode(code);
		this.setMessage(message);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static ClientCommandCode getCommandCodeByCode(final int code) {
		for (ClientCommandCode cc : ClientCommandCode.values()) {
			if (cc.code == code)
				return cc;
		}
		return null;
	}

}
