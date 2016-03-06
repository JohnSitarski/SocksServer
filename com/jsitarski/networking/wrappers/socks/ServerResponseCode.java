package com.jsitarski.networking.wrappers.socks;

/**
 * 
 * @author John
 * 
 *         Socks 5 server response codes as described on
 *         https://en.wikipedia.org/wiki/SOCKS
 */
public enum ServerResponseCode {
	SUCCESSFUL(0, "request granted"), GENENERAL_FAILURE(1, "general failure"),

	RULESET_FAILURE(2, "connection not allowed by ruleset"), NETWORK_FAILURE(3, "network unreachable"),

	HOST_FAILURE(4, "host unreachable"), CONNECTION_REFUSED(5, "connection refused by destination host"),

	TTL_FAILURE(6, "TTL expired"), COMMAND_OR_PROTOCOL_FAILURE(7, "command not supported / protocol error"),

	ADDRESS_TYPE_FAILURE(8, "address type not supported")

	;
	private int code;
	private String message;

	ServerResponseCode(final int code, final String message) {
		this.setCode(code);
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public static ServerResponseCode getResponseByCode(final int status) {
		for (ServerResponseCode src : ServerResponseCode.values()) {
			if (src.code == status) {
				return src;
			}
		}
		return null;
	}

}
