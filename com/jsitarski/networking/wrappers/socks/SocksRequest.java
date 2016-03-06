package com.jsitarski.networking.wrappers.socks;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class SocksRequest {

	private String destinationAddress;
	private int addressType;
	private int cmd;
	private int port;

	public SocksRequest(final int cmd, final int addressType, final String destinationAddress, final int port) {
		this.cmd = cmd;
		this.addressType = addressType;
		this.destinationAddress = destinationAddress;
		this.port = port;
	}

	public byte[] encode() {
		final ArrayList<Byte> byteList = new ArrayList<Byte>();
		byteList.add((byte) 5); // version numbeer
		byteList.add((byte) cmd);// command level
		byteList.add((byte) 0); // reserve
		byteList.add((byte) addressType); // address type
		InetAddress ip = null; // only for ips
		if (AddressType.DOMAIN_NAME.getCode() == addressType) {
			byteList.add((byte) destinationAddress.length()); // string length
		} else {
			try {
				ip = InetAddress.getByName(destinationAddress);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}

		if (addressType == AddressType.DOMAIN_NAME.getCode())
			byteList.add((byte) destinationAddress.length());

		// either domain or ip either way you just write the ip across
		byte[] bytes = addressType == AddressType.DOMAIN_NAME.getCode() ? destinationAddress.getBytes()
				: ip.getAddress();
		for (final byte b : bytes) {
			byteList.add(b); // ip or domain bytes
		}

		// port not encoded properly..
		final byte[] portBytes = SocksUtils.encodePort(port);
		for (final byte b : portBytes) {
			byteList.add(b);
		}
		final byte[] array = new byte[byteList.size()];
		for (int index = 0; index < array.length; index++) {
			array[index] = (byte) byteList.get(index);
		}
		return array;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + addressType;
		result = prime * result + cmd;
		result = prime * result + ((destinationAddress == null) ? 0 : destinationAddress.hashCode());
		result = prime * result + port;
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
		SocksRequest other = (SocksRequest) obj;
		if (addressType != other.addressType)
			return false;
		if (cmd != other.cmd)
			return false;
		if (destinationAddress == null) {
			if (other.destinationAddress != null)
				return false;
		} else if (!destinationAddress.equals(other.destinationAddress))
			return false;
		if (port != other.port)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SocksRequest [destinationAddress=" + destinationAddress + ", addressType=" + addressType + ", cmd="
				+ cmd + ", port=" + port + "]";
	}

}
