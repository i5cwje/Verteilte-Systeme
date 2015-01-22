package caretakerServer;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import flatServer.Connection;

import java.util.*;

public class XMLClient extends Thread{
	private static String IP;
	private static Memory mem = null;
	private static List<String> ports = null;
	
	public void setMemory(Memory m){
		mem = m;
	}
	
	public void setIPandPorts(String ip, List<String> p){
		IP = ip;
		ports = p;
	}

	public void run(){
		int xmlPort = 0;
		while (true) {
			for (int i = 0; i < ports.size(); i++) {
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println(xmlPort);
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
				//System.out.println(housdata);
				mem.setMyhouse(housdata);
			}
		}
	}
}
