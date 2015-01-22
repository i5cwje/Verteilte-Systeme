package flatServer;

/**
@author Pascal Bechtoldt
*/



public class Server {
	public static void main(String[] args) {	
		int PortWebServer = 0;
		int PortUDPServer = 0;
		int PortXML = 0;
		String whereIAm = "";
		System.out.println("Bis hier hin und nicht weiter");
		try{
			PortUDPServer = Integer.parseInt(args[0]);
			PortWebServer = Integer.parseInt(args[1]);
			PortXML = Integer.parseInt(args[2]);
			whereIAm = args[3];
			
		}catch(Exception e){
			e.getStackTrace();
		}
		Connection b = new Connection();
		WebServer ws = new WebServer(b, PortWebServer);
		UDPServer us = new UDPServer(b, PortUDPServer);
		us.start();
		//ws.start();
		
		XMLServer cs = new XMLServer();//Initalisiere  des XML Server
        cs.setPort(PortXML);

        cs.start(); //Starten des XML Server
        cs.setConnection(b);//datenvektor an xmlserver uebermitteln
        cs.setWhereIAm(whereIAm);
        cs.buildString();
	}
}
