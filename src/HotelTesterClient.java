
/**
 * This is a client class that sends commands to server class and interacts with user.
 */
import java.io.*;
import java.net.*;

public class HotelTesterClient {

	public static void main(String[] args) throws IOException {
		final int PORT = 1181;
		try (Socket s = new Socket("localhost", PORT)) {

			InputStream instream = s.getInputStream();
			OutputStream outstream = s.getOutputStream();

			DataInputStream in = new DataInputStream(instream);
			DataOutputStream out = new DataOutputStream(outstream);

			System.out.println(in.readUTF());

			String user = "Ali";

			out.writeUTF("USER");
			out.writeUTF(user);
			out.flush();
			System.out.println(in.readUTF());

			int first = 5;
			System.out.println("First Day: " + first);
			int last = 10;
			System.out.println("Last Date: " + last);
			out.writeUTF("RESERVE");
			out.writeInt(first);
			out.writeInt(last);
			out.flush();
			System.out.println("\n" + in.readUTF());

			out.writeUTF("STATUS");
			out.flush();
			System.out.println("\n" + in.readUTF());

			out.writeUTF("CANCEL");
			out.flush();
			System.out.println("\n" + in.readUTF());

			out.writeUTF("STATUS");
			out.flush();
			System.out.println("\n" + in.readUTF());

			out.writeUTF("QUIT");
			System.out.println("Client quitted");
			out.flush();
			System.out.println("\n " + in.readUTF());

		}
	}
}