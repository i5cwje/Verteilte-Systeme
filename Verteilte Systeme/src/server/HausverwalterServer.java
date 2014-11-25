package server;

import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

import sun.applet.Main;

public class HausverwalterServer {

	private static final int PORT = 8080;
	
	public String updateMyTable(){
		String antwort = "Can ist der King auf dem Planeten, den ihr könnt mich am Arsch lecken";
		return antwort;
	}

	public static void main(String[] args) {
		try {

			WebServer webServer = new WebServer(PORT);

			XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
			PropertyHandlerMapping phm = new PropertyHandlerMapping();
			
			phm.addHandler("Hausverwalter", HausverwalterServer.class);
			xmlRpcServer.setHandlerMapping(phm);
			XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer
					.getConfig();
			
			webServer.start();

			System.out.println("The String Server has been started...");

		} catch (Exception exception) {
			//System.err.println("JavaServer: " + exception);
		}
	}
}
