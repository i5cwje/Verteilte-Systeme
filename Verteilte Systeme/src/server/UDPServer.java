package server;
/*
	@author Pascal Bechtoldt, Can Kedik
*/
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

public class UDPServer extends Thread{
	Connection connection;
	private final static int PACKETSIZE = 1024;
	private int PORT;
	
	public UDPServer(Connection c, int port){
		connection = c;
		PORT = port;
	}
	@SuppressWarnings("resource")
	public void run(){
        
        DatagramSocket socketUDP = null;
		try {
			socketUDP = new DatagramSocket(PORT);
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        while (true){
            
            DatagramPacket packet = new DatagramPacket( new byte[PACKETSIZE], PACKETSIZE );
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
			connection.put(receivedText);
        }
	}
}
