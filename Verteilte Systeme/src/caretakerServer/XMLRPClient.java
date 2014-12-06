package caretakerServer;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class XMLRPClient {
	static float lastTime=0;
	static float sendTime=0;
	static Object params[];
	static String whereIAm;
	static String table; 
	static int highestTemperature;
	static int lowestTemperature;
	static int maxPowerUsage;
	
	public static void main(String[] args) throws MalformedURLException, XmlRpcException {

		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

		config.setServerURL(new URL("http://127.0.0.1:8988/xmlrpc")); //Diese Zeile wirft die MalformedURL exception
		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);
		
		while (true) {
			sendTime += getDelta();
			if (sendTime >= 2) {
				sendTime = 0;
				whereIAm = (String)client.execute("XMLRPCServer.whereIAm",params);
				table = (String)client.execute("XMLRPCServer.updateMyTable",params); // Das hier führt scheinbar den RPC aus.
				highestTemperature = (Integer)client.execute("XMLRPCServer.getHighestTemperature",params);
				lowestTemperature = (Integer)client.execute("XMLRPCServer.getLowestTemperature",params);
				maxPowerUsage = (Integer)client.execute("XMLRPCServer.getMaxPowerUsage",params);
			}
			System.out.println(whereIAm);
			System.out.println(table);
			System.out.println(highestTemperature);
			System.out.println(lowestTemperature);
			System.out.println(maxPowerUsage);
		}
	}

	public static long getTime() {
		return (System.currentTimeMillis());
	}

	public static long getDelta() {
		long time = getTime();
		long delta = (long) (time - lastTime);
		lastTime = time;
		return delta;
	}
}
