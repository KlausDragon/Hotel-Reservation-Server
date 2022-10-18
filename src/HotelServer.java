
/**
 * This class is a server that creates a HotelService object and it's port is connected to 
 * HotelClient for user interface.
 */
import java.io.*;
import java.net.*;

public class HotelServer {

	public static void main(String[] args) throws IOException {

		final int HOTEL_PORT = 1181;
		Hotel hotel = new Hotel();
		ServerSocket server = new ServerSocket(HOTEL_PORT);
		System.out.println("Waiting for client to connect..!");
		
		while (true) {
			Socket sock = server.accept();
			System.out.println("WELCOME..! Client");
			HotelService service = new HotelService(sock, hotel);
			Thread t = new Thread(service);
			t.start();
			server.close();
		}
	}

}
