package com.jsitarski;

import java.io.DataInputStream;
import java.net.InetAddress;
import java.net.Socket;

import com.jsitarski.networking.wrappers.socks.AddressType;
import com.jsitarski.networking.wrappers.socks.ClientCommandCode;
import com.jsitarski.networking.wrappers.socks.ServerResponseCode;
import com.jsitarski.networking.wrappers.socks.SocksProxy;
import com.jsitarski.networking.wrappers.socks.SocksRequest;

public class ServerLoader {
	private static SocksProxy proxy1 = null;

	// testing only
	public static void main(String[] args) throws Exception {

		proxy1 = new SocksProxy(InetAddress.getByName("127.0.0.1"), 2012);
		final Socket socket = new Socket(proxy1.getAddress().getHostAddress(), proxy1.getPort());
		socket.getOutputStream().write(proxy1.getGreeting());
		if (proxy1.hasAuth())
			socket.getOutputStream().write(proxy1.getAuthentication());
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		System.out.println("Socks version: " + dis.readUnsignedByte());
		final int reply = dis.readUnsignedByte();
		if (reply == 0) {
			System.out.println("Connected to socks5 proxy");
			System.out.println("!---------------------------------------!");

			final SocksRequest SR = new SocksRequest(ClientCommandCode.ESTABLISH_PORT_BINDING.getCode(),
					AddressType.IPV4_ADDRESS.getCode(), "150.101.60.237", 80);
			System.out.println("Attempting to Establish port binding!");

			System.out.println("!---------------------------------------!");
			System.out.println("Socks5 Response Below:");
			socket.getOutputStream().write(SR.encode());
			System.out.println("Version: " + dis.readUnsignedByte()); // version
			System.out.println("Status: " + ServerResponseCode.getResponseByCode(dis.readUnsignedByte()).getMessage()); // status
			System.out.println("Reserve byte: " + dis.readUnsignedByte()); // reserve

			final int atype = dis.readUnsignedByte();
			System.out.println("Address type: " + AddressType.getAddressTypeByCode(atype).getDescription()); // address type
			String requestIp = "";
			if (atype == AddressType.IPV4_ADDRESS.getCode()) {

				for (int i = 0; i < 4; i++) {
					requestIp += dis.readUnsignedByte() + (i != 3 ? "." : "");
				}
				System.out.println("Request IP: " + requestIp);
			}
			final int port = dis.readUnsignedShort();
			System.out.println("Port: " + port);
			System.out.println("WTF IS THIS THEN???? " + requestIp + " / " + port);
			final Socket s1 = new Socket(requestIp, port);
			for (int i = 0; i < 100; i++) {
				System.out.println(s1.getInputStream().read());
			}
			socket.close();

		} else {
			socket.close();
		}
	}

}
