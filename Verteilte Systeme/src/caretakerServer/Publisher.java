package caretakerServer;

import java.util.Vector;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;

public class Publisher extends Thread {

	private String user = env("ACTIVEMQ_USER", "admin");
	private String password = env("ACTIVEMQ_PWD", "password");
	private String host = env("ACTIVEMQ_HOST", "192.168.2.101");
	private int port = Integer.parseInt(env("ACTIVEMQ_PORT", "61616"));
	private String destination = null;

	private final int MESSAGES = 10000;
	private final int SIZE = 256;

	private Memory mem;
	private String body = null;

	public Publisher(){

	}

	public void setReady(Memory memory, String[] args) {

		destination = arg(args, 0, "event");
		mem = memory;
	}

	public void publish() {

		while (true) {
			ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(
					"tcp://" + host + ":" + port);

			Connection connection;

			body = mem.getHouseData();
			body = "eventblablabla";
			if (body != null) {
				try {
					connection = factory.createConnection(user, password);

					connection.start();
					Session session = connection.createSession(false,
							Session.AUTO_ACKNOWLEDGE);
					Destination dest = new ActiveMQTopic(destination);
					MessageProducer producer = session.createProducer(dest);
					producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

					for (int i = 1; i <= MESSAGES; i++) {
						TextMessage msg = session.createTextMessage(body);
						msg.setIntProperty("id", i);
						producer.send(msg);
						
					}

					producer.send(session.createTextMessage("SHUTDOWN"));
					connection.close();
				} catch (JMSException e) {
					// TODO logfile schreib kram
					// mach ich aber nicht ätsch ich weiß ist nicht sauber ...
					e.printStackTrace();
				}
			}

		}
	}

	private Vector<String> makeNotifications() {
		/*
		 * // Notification feature implementierung low Prio String
		 * allTheHouseData = mem.getData();
		 * 
		 * for (alltTheHouseData){
		 * 
		 * }
		 */
		return null;
	}
	
	public void run(){
		publish();
	}

	private String env(String key, String defaultValue) {
		String rc = System.getenv(key);
		if (rc == null)
			return defaultValue;
		return rc;
	}

	private String arg(String[] args, int index, String defaultValue) {
		if (index < args.length)
			return args[index];
		else
			return defaultValue;
	}

}
