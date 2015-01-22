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
	
	
	public Connection() {
		roomInformations = new Vector<Message>();
		roomInformations.clear();
	}
		
	public synchronized void put(String text) {
		String[] parts = text.split(" ");
		
		int temperature = Integer.parseInt(parts[2]);
		int powerUsage = Integer.parseInt(parts[3].trim());
		//..
		
		
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
		int highestTemperature = 0;	
		for(int i=0; i<roomInformations.size(); i++){
			if(highestTemperature < roomInformations.elementAt(i).temperature){
				highestTemperature = roomInformations.elementAt(i).temperature;
			}
		}	
		return highestTemperature;
	}
	
	public synchronized int getLowest(){
		int lowestTemperature = 0;
		for(int i=0; i<roomInformations.size(); i++){
			if(lowestTemperature < roomInformations.elementAt(i).temperature){
				lowestTemperature = roomInformations.elementAt(i).temperature;
			}
		}
		return lowestTemperature;
	}
	
	public synchronized int getMaxPowerUsage(){
		int maxPowerUsage = 0;
		for(int i=0; i<roomInformations.size(); i++){
			if(maxPowerUsage < roomInformations.elementAt(i).powerUsage){
				maxPowerUsage = roomInformations.elementAt(i).powerUsage;
			}
		}
		return maxPowerUsage;
	}
}
