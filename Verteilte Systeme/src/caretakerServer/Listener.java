package caretakerServer;

import java.util.Vector;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;

public class Listener extends Thread {

	private Memory mem;
	private Vector<String> notifications;

	private String user = env("ACTIVEMQ_USER", "admin");
	private String password = env("ACTIVEMQ_PASSWORD", "password");
	private String host = env("ACTIVEMQ_HOST", "192.168.2.101");
	private int port = Integer.parseInt(env("ACTIVEMQ_PORT", "61616"));

	private Vector<String> destinations;
	private Boolean listen = true;

	/**
	 * Diese klasse hört publishern über Active MQ zu. Sollte dieser nicht an
	 * sein läuft da gar nix also brauchst du dich später nicht zu wundern wenn
	 * du ausprobierst obs klappt weils am Active MQ gedöns liegt!
	 * */
	public Listener() {
	}

	public void setReady(Memory memory, String args[]) {
		// destinations.add(arg(args, 0, "event"));
		mem = memory;
		notifications = null;
		destinations = new Vector<String>();

	}

	public void listenToSomeone() {
		/* Einrichten einer kommunikation mit active mq */
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(
				"tcp://" + host + ":" + port);

		Connection connection;
		String tmp = "Hans"; //Hans ist komisch ich weiß aber ich hab mir da was gedacht sie mem
		destinations.addElement(tmp);
		try {
			while (listen) {// Hier ist das effektive empfangen
				String body = "";
				try {
					Thread.sleep(1000); // 1000 milliseconds is one second.
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				if (!mem.doIHaveToChangeMyDestinations().equals(destinations)) {
					destinations = mem.doIHaveToChangeMyDestinations();
				}
				mem.setFlag(false);
				for (int i = 0; i < destinations.size(); i++) {
					if (destinations.get(i).equals(tmp)) {
						continue;
					}
					connection = factory.createConnection(user, password);
					connection.start();

					Session session = connection.createSession(false,
							Session.AUTO_ACKNOWLEDGE);

					Destination dest = new ActiveMQTopic(destinations.get(i));
					MessageProducer producer = session.createProducer(dest);
					producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
					MessageConsumer consumer = session.createConsumer(dest);

					Message msg = consumer.receive();
					if (msg instanceof TextMessage) {
						body += ((TextMessage) msg).getText();
						mem.setNotMyHouseData(body); // Funktioniert
					}

					connection.close();
				}
				mem.setFlag(true);
			}
		} catch (JMSException e) {
			// TODO logfile schreib kram
			// mach ich aber nicht ätsch ich weiß ist nicht sauber ...
			e.printStackTrace();
		}
	}

	private static String env(String key, String defaultValue) {
		String rc = System.getenv(key);
		if (rc == null)
			return defaultValue;
		return rc;
	}

	public void run() {
		listenToSomeone();
	}

	/**
	 * gibt bearbeiteten Arg string zurück wird benutzt um die lauschrichtung zu
	 * bestimmen.
	 * 
	 * @param args
	 * @param index
	 * @param defaultValue
	 * @return String
	 */

	private static String arg(String[] args, int index, String defaultValue) {
		if (index < args.length)
			return args[index];
		else
			return defaultValue;
	}

}
