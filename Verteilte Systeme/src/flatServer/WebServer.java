package flatServer;

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
	private int PORT;
	static int xmlPort = 0;
	private static XMLServer cs = null;

	public WebServer(Connection c, int port){
		connection = c;
		PORT = port;
	}
	
	public void run(){
		HttpServer server = null;
		try {
			server = HttpServer.create(new InetSocketAddress(PORT), 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        server.createContext("/rooms", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
	}
	//TODO Ich muss auf einzelne räume zugreifen können
    static class MyHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
        	Vector<Message> info = connection.get();
        	String out = "Raum \t\t Temperatur \t PowerUsage \n";
        	out += "----------------------------------------------\n";
        	for(int i=0; i<info.size(); i++){
        		out += info.elementAt(i).room + " \t\t " + info.elementAt(i).temperature + " \t\t " + info.elementAt(i).powerUsage + "\n";
        	}
            String response = out;
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
