package server;

/*
@author Pascal Bechtoldt
*/

public class Server {
	public static void main(String[] args) {
		Buffer b = new Buffer();
		WebServer ws = new WebServer(b);
		UDPServer us = new UDPServer(b);
		us.start();
		ws.start();
	}
}
