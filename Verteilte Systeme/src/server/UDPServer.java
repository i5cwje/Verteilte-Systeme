package server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

public class UDPServer extends Thread{
	Buffer buffer;
	
	public UDPServer(Buffer b){
		buffer = b;
	}
	@SuppressWarnings("resource")
	public void run(){
        int portUDP = 9999;
        int packetSize = 1024;
        
        DatagramSocket socketUDP = null;
		try {
			socketUDP = new DatagramSocket(portUDP);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        while (true){
            
            DatagramPacket packet = new DatagramPacket( new byte[packetSize], packetSize );
            try {
				socketUDP.receive(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			byte[] buf = new byte[packet.getData().length];
			buf=packet.getData();
			String receivedText = null;
			try {
				receivedText = new String(buf, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(receivedText);
			buffer.put(receivedText);
        }
	}
}
