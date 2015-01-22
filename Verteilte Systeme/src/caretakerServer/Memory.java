package caretakerServer;

import java.util.Vector;

public class Memory {
	private String houseData;
	private String notMyHouseData;

	private Vector<String> destinations;

	public Memory() {
		houseData = null;
		destinations = new Vector<String>();
		destinations.addElement("Hans"); //hans ists komisch ich weiß schau in listener
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

	/**
	 * 
	 * @return Vector String, with all destinations
	 */
	public Vector<String> doIHaveToChangeMyDestinations() {
		return destinations;
	}
}
