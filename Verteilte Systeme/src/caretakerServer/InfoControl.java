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
public class InfoControl extends Thread {

	private static final String MYUSELESSERRORMESSAGE = "Eingabe unklar penis "
			+ "steckt im Monitor fest";

	private static XMLClient client;
	private static Listener listen;
	private static Publisher publisher;
	private static Vector<String> notifications = new Vector<String>();
	private static Scanner scanner;
	private static String houseData;

	public static void main(String[] args) {
		client = new XMLClient();
		client.setReady(args);
		client.start();
		listen = new Listener();
		publisher = new Publisher();
		scanner = new Scanner(System.in);

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
			System.out.println("0. Exit Programm");
			if (notifications.size() < 0) {
				System.out.println("4. Notifikationen abrufen");
			}

			choice = scanner.next();

			switch (choice) {
			case "0": {
				run = false;
				break;
			}
			case "1":{
				System.out.println(houseData);
				break;
			}
			case "2":
				break;
			case "3":
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
