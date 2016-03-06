package com.jsitarski.networking.implementation.socks;

import java.net.Socket;

import com.jsitarski.networking.wrappers.socks.SocksProxy;

public class SocksSocket extends Socket {

	/**
	 * Not implemented yet
	 */
	protected SocksProxy socksproxy;

	public SocksSocket(final SocksProxy socksproxy) {
		this.socksproxy = socksproxy;
	}
}
