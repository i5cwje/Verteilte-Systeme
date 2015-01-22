package flatServer;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.webserver.WebServer;
import java.util.Vector;



public class XMLServer extends Thread {
   private Connection connection = null;
   private  static String ausgabe;
   static int port = 0;
   static String whereIAm = "";
   Vector<Message> info;
   static int maxPower = 0;
   static int minTemp = 0;
   static int maxTemp = 0;
 
  
  
	public void setConnection(Connection c){
	    this.connection = c;
	}
	  
	public void setWhereIAm(String w){
		this.whereIAm = w;
	}
	  
	public void setPort(int newPort){
		this.port = newPort;
	}
	
	public String getData(){
	    return ausgabe;
	}
  
    public void buildString(){
        while(true){
        	info = connection.get();
        	maxPower = connection.getMaxPowerUsage();
        	minTemp = connection.getLowest();
        	maxTemp = connection.getHighest();
            try {
                Thread.sleep(1000);                 //1000 milliseconds is one second.
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            ausgabe = "########################\n";
            ausgabe += "Haus: " + whereIAm;
            ausgabe += "\nRaum \t\t Temperatur \t Stromverbrauch \n";
            ausgabe += "................................................\n";
            for(int i=1; i<info.size(); i++){
                ausgabe += info.elementAt(i).room + " \t\t " + info.elementAt(i).temperature + " \t\t " + info.elementAt(i).powerUsage + "\n";
            }
            ausgabe += "Stromverbrauch " + maxPower + "\n" 
                    + "Minimale Temperatur " + minTemp + "\n"
                    + "Maximale Temperatur " + maxTemp + "\n";
            //System.out.println(ausgabe);
        }
    }
 

  public void run(){
    try {

      WebServer webServer = new WebServer(port);

      XmlRpcServer xmlRpcServer = webServer.getXmlRpcServer();
      PropertyHandlerMapping phm = new PropertyHandlerMapping();

      phm.addHandler( "XML", XMLServer.class);
      xmlRpcServer.setHandlerMapping(phm);

     XmlRpcServerConfigImpl serverConfig =
              (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
     // serverConfig.setEnabledForExtensions(true);
     // serverConfig.setContentLengthOptional(false);

      webServer.start();
       

      System.out.println("The XML Server has been started..." );

    } catch (Exception exception) {
       System.err.println("JavaServer: " + exception);
    }
  }
}
