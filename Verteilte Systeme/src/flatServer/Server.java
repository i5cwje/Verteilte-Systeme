package flatServer;

/*
@author Pascal Bechtoldt
*/

public class Server {
	public static void main(String[] args) {
		int PortWebServer = 2000;
		int PortUDPServer = 9998;
		Connection b = new Connection();
		WebServer ws = new WebServer(b, PortWebServer);
		UDPServer us = new UDPServer(b, PortUDPServer);
		us.start();
		ws.start();
	}
}
