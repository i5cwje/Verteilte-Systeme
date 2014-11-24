package clients;

/*
 * 23.10.14
 */

/**
 *
 * @author Pascal Bechtoldt
 */

import java.io.*;
import java.net.*;

public class UDPClient {

	private final static int PACKETSIZE = 1024;
	private static long lastTime = 0;
	private static long sendTime;
	private static InetAddress destination;
	private static DatagramSocket toSocket;
	private static DatagramPacket packet;
	private static int port = 9997;
	private static String IP = "192.168.2.104";
	private static String whereAmI;
	private static int packetNumber = 0;

	public static void main(String[] args) throws Exception {
	//public UDPClient(String name) throws Exception{
		destination = InetAddress.getByName(IP);
		packet = new DatagramPacket(giveRandomNumbers(), 2, destination, port);
		toSocket = new DatagramSocket();
		sendTime = 0;
		whereAmI = args[0];
		System.out.println(whereAmI);

		System.out.println(sendTime + " " + packetNumber);
		while (true) {
			sendTime += getDelta();
			System.out.println(sendTime + " " + packetNumber);
			if (sendTime >= 104.1f) {
				sendTime = 0;
				packet.setData(giveRandomNumbers());
				toSocket.send(packet);
			}
		}
	}

	// Als erstes Ort, Temperatur und dann Stromverbrauch
	private static byte[] giveRandomNumbers() {
		byte[] data = new byte[PACKETSIZE];
		String informationDump;
		String buf;

		informationDump = whereAmI;
		int temperature = (int) (Math.random() * 30);
		int powerUsage = (int) (Math.random() * 2000);
		buf = packetNumber + " " + informationDump + " "
				+ String.valueOf(temperature) + " "
				+ String.valueOf(powerUsage);
		System.out.println(buf);
		packetNumber++;

		for (int i = 0; i < buf.length(); i++) {
			data[i] = (byte) buf.charAt(i);
		}
		return data;
	}

	public static long getTime() {
		return (System.currentTimeMillis());
	}

	public static long getDelta() {
		long time = getTime();
		long delta = time - lastTime;
		lastTime = time;
		return delta;
	}
}
