package clients;

/*
 * 23.10.14
 */

/**
 *
 * @author Can Kedik
 * Computer Science Dept.                   Fachbereich Informatik
 * Darmstadt Univ. of Applied Sciences      Hochschule Darmstadt
 */

import java.io.*;
import java.net.*;

public class UDPClient {

	private final static int PACKETSIZE = 1024;
	private static long lastTime;
	private static long sendTime;
	private static String line; 
	
	private static InetAddress destination;
	private static DatagramSocket toSocket;
	private static DatagramPacket packet;
	private static int port = 9998;
	private static String IP = "192.168.2.104";
	private static String whereAmI;

	public static void main(String[] args) throws Exception {
		destination = InetAddress.getByName(IP);
		packet = new DatagramPacket(giveRandomNumbers(), 2, destination, port);
		toSocket = new DatagramSocket();
		sendTime = 0;
		whereAmI = args[0];
		System.out.println(whereAmI);

		while (true) {
			sendTime += (int) (getDelta());
			if (sendTime >= 5000) {
				sendTime = 0;
				packet.setData(giveRandomNumbers());
				toSocket.send(packet);
			}
		}
	}

	// Als erstes Ort, Temperatur und dann Stromverbrauch
	private static byte[] giveRandomNumbers(){	  
	 byte[] data = new byte[PACKETSIZE];
	 String informationDump;
	 String buf;
	 
	 informationDump=whereAmI;
	 int temperature=(int)(Math.random()*30);
	 int powerUsage=(int)(Math.random()*2000);
	 buf=informationDump +" "+ String.valueOf(temperature)
			 +" "+String.valueOf(powerUsage);
	 System.out.println(buf);
	 
	 for (int i=0;i<buf.length();i++){
		 data[i]=(byte) buf.charAt(i);
	 }
	 
	 return data;
  }

	public static long getTime() {
		return (System.currentTimeMillis());
	}

	public static int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastTime);
		lastTime = (int) time;
		return delta;
	}
}