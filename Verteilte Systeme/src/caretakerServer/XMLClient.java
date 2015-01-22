package caretakerServer;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.util.*;

public class XMLClient extends Thread{
	private Vector<String> notifies;
	private int xmlPort;
	private List<String> ports;
	private String IP;
	private Memory mem;

	public void setReady(String[] args, Memory memory) {
		mem=memory;
		xmlPort = 0;
		ports = new ArrayList<String>();
		if(args.length>0)
			IP = args[0];
		for (int i = 1; i < args.length; i++) {
			ports.add(args[i]);
		//	System.out.println(ports.get(i - 1));
		}
	}

	public void iDoWhatIDo() {

		String housdata = null;
		while (true) {
			
			for (int i = 0; i < ports.size(); i++) {//wenn keine ports exisistieren geht er hier nicht rein und Papapau tritt nicht auf
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
				XmlRpcClient client = new XmlRpcClient();
				client.setConfig(config);

				Object[] params = new Object[] {};
				try {
					housdata = (String) client
							.execute("XML.getData", params);
				} catch (XmlRpcException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//housdata="PAPAPAU";//housdata // Funktioniert mit Constantem String funktioniert also auch mit Variablen der schon letztes mal funktioniert hat sonst suizid
				mem.setHouseData(housdata);
			}
		}
	}
	public void run (){
		iDoWhatIDo();
	}
}
