package com.jsitarski.networking.wrappers.socks;

import java.nio.ByteBuffer;

public class SocksUtils {

	// network order byte

	// test case 201,137 == 49859
	public static int decodePort(final byte high, final byte low) {
		return (((high) << 8) | (low) & 0xff); // this shit aint
	}

	public static byte[] encodePort(final int port) {
		return new byte[] { (byte) (port >> 8), (byte) (port & 0xff) };
	}

	public static void main(String[] args) {

		System.out.println(decodePort((byte) 201, (byte) 137));

	}

	private static short convertntohs(byte[] value) {
		ByteBuffer buf = ByteBuffer.wrap(value);
		return buf.getShort();
	}

	public static int networkByteOrderToInt(byte[] buf, int start, int count) {
		if (count > 4) {
			throw new IllegalArgumentException("Cannot handle more than 4 bytes");
		}

		int result = 0;

		for (int i = 0; i < count; i++) {
			result <<= 8;
			result |= (buf[start + i] & 0xff);
		}

		return result;
	}

}
