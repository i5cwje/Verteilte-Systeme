package server;

import java.util.Vector;

class Message {
	public String room;
	public int temperature;
	public int powerUsage;
}
	
public class Buffer {
	Vector<Message> roomInformations;
	
	public Buffer() {
		roomInformations = new Vector<Message>();
	}
		
	public synchronized void put(String text) {
		String[] parts = text.split(" ");
		
		int temp = Integer.parseInt(parts[1]);
		int pow = Integer.parseInt(parts[2].trim());
		boolean setInfo = false;
		for(int i=0; i<roomInformations.size(); i++){
			if(roomInformations.elementAt(i).room.equals(parts[0])){
				roomInformations.elementAt(i).temperature = temp;
				roomInformations.elementAt(i).powerUsage = pow;
				setInfo = true;
				break;
			}
		}
		if(!setInfo){
			Message mes = new Message();
			mes.room = parts[0];
			mes.temperature = temp;
			mes.powerUsage = pow;
			roomInformations.add(mes);
		}
	}
	
	public synchronized Vector<Message> get() {
		return roomInformations;
	}
}
