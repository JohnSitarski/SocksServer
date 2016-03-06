package com.jsitarski.networking.wrappers.socks;

public class SocksReply {
	private int socksversion;
	private int status;
	private int addressType;

	private ServerResponseCode serverResponse;

	private AddressType addressTypeE;

	// version, status ,reserve, address type, (address byte) decode the
	// byte[],high,low
	public SocksReply(final int socksversion, final int status, final int addressType) {
		this.socksversion = socksversion;
		this.status = status;
		this.addressType = addressType;
		serverResponse = ServerResponseCode.getResponseByCode(status);
		addressTypeE = AddressType.getAddressTypeByCode(addressType);
	}

	public AddressType getResponseAddressType() {
		return addressTypeE;
	}

	public ServerResponseCode getServerResponse() {
		return serverResponse;
	}

	public int getSocksversion() {
		return socksversion;
	}

	public int getStatus() {
		return status;
	}

	public int getAddressType() {
		return addressType;
	}
}
