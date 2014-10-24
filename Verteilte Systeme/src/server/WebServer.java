package server;

/*
@author Pascal Bechtoldt
*/

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Vector;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class WebServer extends Thread{
	static Connection connection = null;
	private static int port = 2000;
	
	
	
	public WebServer(Connection b){
		connection = b;
	}
	
	public void run(){
		HttpServer server = null;
		try {
			server = HttpServer.create(new InetSocketAddress(port), 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        server.createContext("/room", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
	}
	//TODO Ich muss auf einzelne räume zugreifen können
    static class MyHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
        	Vector<Message> info = connection.get();
        	String out = "";
        	for(int i=0; i<info.size(); i++){
        		out += info.elementAt(i).room + " " + info.elementAt(i).temperature + " " + info.elementAt(i).powerUsage + "\n";
        	}
            String response = out;
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
