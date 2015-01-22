package caretakerServer;

import java.util.Vector;

public class Memory {
	private String houseData;
	private String notMyHouseData;
	private Boolean flag;

	private Vector<String> destinations;

	public Memory() {
		houseData = null;
		destinations = new Vector<String>();
		destinations.addElement("Hans"); //hans ists komisch ich weiß schau in listener
	}
	
	public void setFlag(Boolean value){
		flag = value;
	}

	public String getNotMyHouseData() { // Funktion die InfoControll
		return notMyHouseData;
	}

	public void setNotMyHouseData(String notMyHouseData) { // Funktion die von
															// Listener
															// aufgerufen wird
		this.notMyHouseData = notMyHouseData;
	}

	/**
	 * diese funktion übermittel die Hausdaten es ist ein fertig bearbeiteter.
	 * Der nur noch ausgegeben werden muss.
	 * 
	 * @return String
	 */
	public String getHouseData() { // Funktion die Infocontroll und Publisher
									// aufgerufen wird
		return houseData;
	}

	/**
	 * Diese funktion gibt die Hausdaten zurück
	 */
	public void setHouseData(String data) { // Funktion die vom XMCLient
											// aufgerufen wird.
		this.houseData = data;
	}

	public void addAnDestination(String dest) { // jaja ich weiß wäre 'a' aber
												// // mit 'an' sieht mans
												// CamelCase // besser und man
												// liests ja // dochrichtig.
		destinations.add(dest);
	}
	
	public void deleteDestination(String dest) {
		while(true){
			if(flag){
				int position = -1;
				for(int i=0; i<destinations.size(); i++){
					if(destinations.elementAt(i).equals(dest)){
						position = i;
					}
				}
				if(position != -1){
					destinations.remove(position);
					notMyHouseData = "";
				}
				break;
			}else{
				try {
					Thread.sleep(30); // 1000 milliseconds is one second.
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}

	/**
	 * 
	 * @return Vector String, with all destinations
	 */
	public Vector<String> doIHaveToChangeMyDestinations() {
		return destinations;
	}
}
