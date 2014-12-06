package flatServer;

/*
@author Pascal Bechtoldt
*/

public class Server {
	public static void main(String[] args) {	
		int PortWebServer = 0;
		int PortUDPServer = 0;
		int PortXMLRPCServer = 0;
		String whereIAm = null;
		try{
			whereIAm = args[0];
			PortXMLRPCServer = Integer.parseInt(args[1]);
			PortUDPServer = Integer.parseInt(args[2]);
			PortWebServer = Integer.parseInt(args[3]);
		}catch(Exception e){
			e.getStackTrace();
		}
		Connection b = new Connection();
		WebServer ws = new WebServer(b, PortWebServer);
		UDPServer us = new UDPServer(b, PortUDPServer);
		XMLRPCServer xs = new XMLRPCServer(b, PortXMLRPCServer, whereIAm);
		us.start();
		ws.start();
		xs.start();
	}
}
