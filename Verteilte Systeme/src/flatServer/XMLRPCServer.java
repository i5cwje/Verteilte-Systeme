package flatServer;

import java.util.Vector;

import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;

public class XMLRPCServer extends Thread{
	Connection connection;
	private static int PORT;
	private static String whereIAm;
	
	
	public XMLRPCServer(Connection c, int port, String name){
		connection = c;
		PORT = port;
		whereIAm = name;
	}
	
	public void run(){
		try {

			WebServer webServer = new WebServer(PORT);

			XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
			PropertyHandlerMapping phm = new PropertyHandlerMapping();
			
			phm.addHandler("XMLRPCServer", XMLRPCServer.class);
			xmlRpcServer.setHandlerMapping(phm);
			XmlRpcServerConfigImpl serverConfig = (XmlRpcServerConfigImpl) xmlRpcServer
					.getConfig();
			
			webServer.start();

			System.out.println("The " + whereIAm + " Server has been started...");

		} catch (Exception exception) {
			//System.err.println("JavaServer: " + exception);
		}
	}
	
	public String whereIAm(){
		return whereIAm;
	}
	
	public String updateMyTable(){
		Vector<Message> info = connection.get();
		String out = "Raum \t\t Temperatur \t PowerUsage \n";
    	out += "----------------------------------------------\n";
    	for(int i=0; i<info.size(); i++){
    		out += info.elementAt(i).room + " \t\t " + info.elementAt(i).temperature + " \t\t " + info.elementAt(i).powerUsage + "\n";
    	}
		return out;
	}
	
	public int getHighestTemperature(){
		return connection.getHighest();
	}
	public int  getLowestTemperature(){
		return connection.getLowest();
	}
	public long getMaxPowerUsage(){
		return connection.getMaxPowerUsage();
	}
}
