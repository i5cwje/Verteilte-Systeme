package caretakerServer;

import java.util.Scanner;
import java.util.Vector;

/*
 * This class is the control Class of Listener Publisher and XMLClient.
 * This Class will be the Interface between the User
 * and Listener Publisher and XMLClient.
 * 
 * 
 * */

/**
 * IP des ActiveMQ Incubator 
 *@see 192.168.2.101*/
public class InfoControl {

	private static final String MYUSELESSERRORMESSAGE = "Eingabe unklar penis "
			+ "steckt im Monitor fest";

	private static XMLClient client;
	private static Listener listen;
	private static Publisher publisher;
	private static Vector<String> notifications = new Vector<String>();
	private static Scanner scanner;
	private static Memory mem;

	public static void main(String[] args) {
		mem=new Memory();
		listen = new Listener();
		listen.setReady(mem, args);
		publisher = new Publisher();
		publisher.setReady(mem, args);
		scanner = new Scanner(System.in);
		client = new XMLClient();
		client.setReady(args, mem);
		client.start();
		listen.start();
		publisher.start();

		menue();
	}

	public static void menue() {
		Boolean run = true;
		String choice = null;
		while (run) {
			System.out.println("Optionen wählen");
			System.out.println("1. Informationen ausgeben");
			System.out.println("2. Anderes Haus abonnieren");
			System.out.println("3. Abonnement beenden");
			if (notifications.size() < 0) {
				System.out.println("4. Notifikationen abrufen");
			}
			System.out.println("0. Exit Programm");

			choice = scanner.next();

			switch (choice) {
			case "0": {
				run = false;
				break;
			}
			case "1":{
				System.out.println(mem.getHouseData());
				System.out.println("Nicht meine Häuser");
				System.out.println(mem.getNotMyHouseData());
				break;
			}
			case "2":
			{
				System.out.println("Gib die ID, eines Publishers ein");
				mem.addAnDestination(scanner.next());
			}
				break;
			case "3":
			{
				System.out.println("Gib die ID, eines Publishers ein");
				mem.deleteDestination(scanner.next());
			}
				break;
			case "4":
				if (notifications.size() == 0) {
					System.out.println(MYUSELESSERRORMESSAGE);
					break;
				} else {
					for (String tmp : notifications) {
						System.out.println(tmp);
					}
				}
				break;
			default:
				System.out.println(MYUSELESSERRORMESSAGE);
				break;
			}
		}
		scanner.close();
	}

}
