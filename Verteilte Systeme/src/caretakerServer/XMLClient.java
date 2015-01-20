package caretakerServer;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.util.*;

public class XMLClient extends Thread{
	Vector<String> notifies;
	private int xmlPort;
	List<String> ports;
	String IP;

	public void setReady(String[] args) {
		xmlPort = 0;
		ports = new ArrayList<String>();
		if(args.length<0)
		IP = args[0];
		for (int i = 1; i < args.length; i++) {
			ports.add(args[i]);
		//	System.out.println(ports.get(i - 1));
		}
	}

	public void iDoWhatIDo(String housedata) {

		while (true) {
			for (int i = 1; i < ports.size(); i++) {
				xmlPort = Integer.parseInt(ports.get(i));
				try {
					Thread.sleep(1000); // 1000 milliseconds is one second.
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}

				XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

				try {
					config.setServerURL(new URL("http://" + IP + ":" + xmlPort
							+ "/xmlrpc"));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				System.out.println(xmlPort);
				XmlRpcClient client = new XmlRpcClient();
				client.setConfig(config);

				Object[] params = new Object[] {};
				String housdata = null;
				try {
					housdata = (String) client
							.execute("XML.getData", params);
				} catch (XmlRpcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				housedata=housdata;
			}
		}
	}
	public void run (String housedata){
		iDoWhatIDo(housedata);
	}
}
