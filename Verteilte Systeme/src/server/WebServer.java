package server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Vector;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class WebServer extends Thread{
	static Buffer buffer = null;
	
	public WebServer(Buffer b){
		buffer = b;
	}
	
	public void run(){
		HttpServer server = null;
		try {
			server = HttpServer.create(new InetSocketAddress(9000), 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        server.createContext("/virus", new MyHandler());
        server.createContext("/killer", new MySecondHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
	}

    static class MyHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
        	Vector<Message> info = buffer.get();
        	String out = "";
        	for(int i=0; i<info.size(); i++){
        		out += info.elementAt(i).room + " " + info.elementAt(i).temperature + " " + info.elementAt(i).powerUsage + "<br>";
        	}
            String response = out;
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
    static class MySecondHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String response = "kuck kuck who is there me i Kill your mom";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
