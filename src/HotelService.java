
/**
 * This class implements Runnable interface and contains all the protocols to interact 
 * with client's input.
 */
import java.io.*;
import java.net.Socket;

public class HotelService implements Runnable {
	// Instance Data
	private Socket sock;
	private DataInputStream in;
	private DataOutputStream out;
	private Hotel hotel;
	String name;

	/**
	 * This is a constructor that initialized the instance data
	 * 
	 * @param sock  - Socket object
	 * @param hotel - Hotel object
	 */
	public HotelService(Socket sock, Hotel hotel) {
		this.sock = sock;
		this.hotel = hotel;
	}

	/**
	 * This method is an abstract method from Runnable interface this method has all
	 * the protocols to take care of user input.
	 */
	public void run() {
		try {
			try {
				in = new DataInputStream(sock.getInputStream());
				out = new DataOutputStream(sock.getOutputStream());
				doService();
			} finally {
				sock.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method reads command sent by client and does the required server side
	 * computation
	 * 
	 * @throws IOException
	 */
	public void doService() throws IOException {
		out.writeUTF("Client has connected to the server");
		out.flush();
		while (true) {
			String command = in.readUTF();
			if (command.equals("QUIT")) {
				System.out.println("Closing connection..!");
			} else
				executeCommand(command);
		}

	}

	/**
	 * This method handles input from client class and uses that user input to do
	 * the necessary computations and sends the appropriate response back to client.
	 * 
	 * @param command - a string client side input.
	 * @throws IOException
	 */

	public void executeCommand(String command) throws IOException {
		if (command.equals("USER")) {
			name = in.readUTF();
			out.writeUTF("Hello " + name);
			out.flush();
		} else if (command.equals("RESERVE")) {
			int first = in.readInt();
			int last = in.readInt();
			out.writeUTF(hotel.makeReservation(name, first, last));
			out.flush();
		} else if (command.equals("CANCEL")) {
			out.writeUTF(hotel.cancelReservation(name));
			out.flush();
		} else if (command.equals("STATUS")) {
			out.writeUTF(hotel.reservationInformation());
			out.flush();
		} else {
			out.writeUTF("Invalid Command: Closing connection");
			out.flush();
		}
	}

}
