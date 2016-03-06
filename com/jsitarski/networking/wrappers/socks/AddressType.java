package com.jsitarski.networking.wrappers.socks;

public enum AddressType {

	IPV4_ADDRESS(1, "IPv4 address"),

	DOMAIN_NAME(3, "Domain name"),

	IPV6_ADDRESS(4, "IPv6 address");

	private int code;
	private String description;

	AddressType(final int code, final String description) {
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

	public static AddressType getAddressTypeByCode(final int code) {
		for (final AddressType at : AddressType.values()) {
			if (at.code == code) {
				return at;
			}
		}
		return null;
	}
}
