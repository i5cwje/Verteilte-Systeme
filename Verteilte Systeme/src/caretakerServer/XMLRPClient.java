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
	static String result; 
	
	public static void main(String[] args) throws MalformedURLException, XmlRpcException {

		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

		config.setServerURL(new URL("http://127.0.0.1:8080/xmlrpc")); //Diese Zeile wirft die MalformedURL exception
		XmlRpcClient client = new XmlRpcClient();
		client.setConfig(config);
		
		while (true) {
			sendTime += getDelta();
			if (sendTime >= 2) {
				sendTime = 0;
				result = (String)client.execute("Hausverwalter.updateMyTable",params); // Das hier führt scheinbar den RPC aus.
			}
			System.out.println("Du wolltest ne Antwort hier hast du sie du Hurensohn: " + result);
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
