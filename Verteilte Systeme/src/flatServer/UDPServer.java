package flatServer;
/*
	@author Pascal Bechtoldt, Can Kedik
*/
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
		/*Logger logger = Logger.getLogger("MyLog");  
	    FileHandler fh;  

	    try {  

	        // This block configure the logger with handler and formatter  
	        fh = new FileHandler("/home/can/Schreibtisch/MyLogFile.log");  
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  

	        // the following statement is used to log any messages  
	        logger.info("Start");  

	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } */
        
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
		    //logger.info(receivedText.trim()); 
        }
	}
}
