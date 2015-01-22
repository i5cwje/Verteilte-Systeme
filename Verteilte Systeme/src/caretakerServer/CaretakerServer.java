package caretakerServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CaretakerServer {
	public static void main(String[] args) {
		List<String> ports = new ArrayList<String>();
		String IP = args[0];
		for (int i = 1; i < args.length; i++) {
			ports.add(args[i]);
			System.out.println(ports.get(i - 1));
		}
		Memory m = new Memory();
		XMLClient xml = new XMLClient();
		xml.setIPandPorts(IP, ports);
		xml.setMemory(m);
		xml.start();
		
		while(true){
			System.out.println("###### MENUE ##############");
			System.out.println("1. Geben sie mein Haus aus");
			System.out.println("2. Geben sie die aktuellen Häuser aus?");
			System.out.println("3. Sich als Listener anmelden");
			System.out.println("4. Sich als Listener abmelden");
			System.out.print("Eingabe: ");
			Scanner scanner = new Scanner(System.in);
			String in = scanner.nextLine().trim();
			switch(in){
			case "1": System.out.println(m.getMyhouse()+"\n");break;
			case "2": break;
			case "3": break;
			case "4": break;
			}
		}
	}
}
