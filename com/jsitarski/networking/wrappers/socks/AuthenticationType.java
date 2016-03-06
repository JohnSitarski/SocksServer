package com.jsitarski.networking.wrappers.socks;

public enum AuthenticationType {
	NO_AUTHENTICATION(0, "No authentication"),

	GSSAPI(1, "GSSAPI"),

	USERNAME_AND_PASSWORD(2, "Username/Password"),

	;

	private int code;
	private String description;

	AuthenticationType(final int code, final String description) {
		this.setCode(code);
		this.setDescription(description);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static AuthenticationType getAuthenticationTypeByCode(final int code) {
		for (AuthenticationType at : AuthenticationType.values()) {
			if (at.code == code)
				return at;
		}
		return null;
	}
}
