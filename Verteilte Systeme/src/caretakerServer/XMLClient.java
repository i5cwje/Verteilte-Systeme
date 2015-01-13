package caretakerServer;
import java.net.URL;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import java.util.*;


public class XMLClient {
    public static void main(String[] args) throws Exception {
        int xmlPort = 0;
        List<String> ports = new ArrayList<String>();
        String IP = args[0];
           for(int i=1;i<=args.length;i++){
                ports.add(args[i]);
                System.out.println(ports.get(i));
            }
               
            
            while(true) {
                for (int i= 0; i<ports.size();i++){
                    xmlPort = Integer.parseInt(ports.get(i));
                    try {
                          Thread.sleep(1000);                 //1000 milliseconds is one second.
                        } catch(InterruptedException ex) {
                          Thread.currentThread().interrupt();
                        }
                    
                    XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

                    config.setServerURL(new URL("http://"+IP+":"+xmlPort+"/xmlrpc"));
                    System.out.println(xmlPort);
                    XmlRpcClient client = new XmlRpcClient();
                    client.setConfig(config);
                 
                    Object[] params = new Object[]{ };
                    String housdata = (String) client.execute("XML.getData",params);
                    System.out.println(housdata);
                    }
            }
    }


}

