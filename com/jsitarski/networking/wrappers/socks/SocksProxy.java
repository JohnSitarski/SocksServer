package com.jsitarski.networking.wrappers.socks;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class SocksProxy {

	private InetAddress address;
	private int port;
	private String username;
	private String password;

	public SocksProxy(final InetAddress address, final int port, final String username, final String password)
			throws UnknownHostException {
		this.address = address;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	public SocksProxy(final InetAddress address, final int port) throws UnknownHostException {
		this(address, port, null, null);
	}

	public InetAddress getAddress() {
		return address;
	}

	/**
	 * Gets the inital greeting/handshake required by the proxy
	 * 
	 * @return
	 */
	public byte[] getGreeting() {
		return new byte[] { 5, 1, (byte) (hasAuth() ? AuthenticationType.USERNAME_AND_PASSWORD.getCode()
				: AuthenticationType.NO_AUTHENTICATION.getCode()) };
	}

	public boolean hasAuth() {
		return username != null && password != null;
	}

	public int getPort() {
		return port;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "SocksProxy [address=" + address + ", port=" + port + ", username=" + username + ", password=" + password
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + port;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SocksProxy other = (SocksProxy) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (port != other.port)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public byte[] getAuthentication() {
		final ArrayList<Byte> byteList = new ArrayList<Byte>();
		byteList.add((byte) 1);
		byteList.add((byte) username.length());
		for (byte b : username.getBytes()) {
			byteList.add(b);
		}
		byteList.add((byte) password.length());
		for (byte b : password.getBytes()) {
			byteList.add(b);
		}
		final byte[] array = new byte[byteList.size()];
		for (int index = 0; index < array.length; index++) {
			array[index] = (byte) byteList.get(index);
		}
		return array;
	}
}
