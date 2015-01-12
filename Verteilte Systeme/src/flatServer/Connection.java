package flatServer;

/*
@author Pascal Bechtoldt
*/

import java.util.Vector;

class Message {
	public String room;
	public int temperature;
	public int powerUsage;
	
	public Message(String room, int temperature, int powerUsage){
		this.room = room;
		this.temperature = temperature;
		this.powerUsage = powerUsage;
	}
}
	
public class Connection {
	private Vector<Message> roomInformations;
	private int lowestTemperature = 100;
	private int highestTemperature = 0;
	private int maxPowerUsage = 0;
	
	public Connection() {
		roomInformations = new Vector<Message>();
		roomInformations.clear();
	}
		
	public synchronized void put(String text) {
		String[] parts = text.split(" ");
		
		int temperature = Integer.parseInt(parts[2]);
		int powerUsage = Integer.parseInt(parts[3].trim());
		//..
		
		//Variablen aktualisieren
		if(temperature>highestTemperature){
			highestTemperature = temperature;
		}
		if(temperature<lowestTemperature){
			lowestTemperature = temperature;
		}
		maxPowerUsage += powerUsage;
		
		boolean setInfo = false;
		for(int i=1; i<roomInformations.size(); i++)
		{
			if(roomInformations.elementAt(i).room.equals(parts[1]))
			{
				roomInformations.elementAt(i).temperature = temperature;
				roomInformations.elementAt(i).powerUsage = powerUsage;
				//..
				
				setInfo = true;
				break;
			}
		}
		if(!setInfo)
		{
			Message message = new Message(parts[1], temperature, powerUsage);
			roomInformations.add(message);
		}
	}
	
	public synchronized Vector<Message> get() {
		return roomInformations;
	}
	
	public synchronized int getHighest(){
		return highestTemperature;
	}
	
	public synchronized int getLowest(){
		return lowestTemperature;
	}
	
	public synchronized int getMaxPowerUsage(){
		return maxPowerUsage;
	}
}
