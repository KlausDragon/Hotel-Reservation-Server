
/**
 * This class represents a one room hotel that accepts reservations for the month of May.
 */
import java.util.ArrayList;

public class Hotel {
	// Instance Data
	private ArrayList<String> booking;

	/**
	 * This is a constructor that initializes instance data.
	 */
	public Hotel() {
		booking = new ArrayList<String>();
		for (int i = 0; i < 31; i++) {
			booking.add("default");
		}
	}

	/**
	 * This method makes a reservation for a client from firstDay to lastDay. This
	 * method is locked.
	 * 
	 * @param name     - name of the client
	 * @param firstDay - start date of reservation
	 * @param lastDay  - end date of reservation
	 * @return - returns a string giving description of reservation made
	 */
	public synchronized String makeReservation(String name, int firstDay, int lastDay) {
		String s = "";
		int count = -1;
		if (booking.contains(name)) {
			s += name + " already has a reservation.";
		} else if (firstDay < 1 || lastDay > 31) {
			s += "Invalid Date";
		} else {
			for (int i = firstDay - 1; i < lastDay; i++) {

				if (booking.get(i) != "default") {
					count = 0;
					break;
				} else {
					booking.set(i, name);
					count = 1;
				}
			}
		}
		if (count == 0) {
			s += "The days are unavailable";
		} else if (count == 1) {
			s += "The reservation was made for " + name + " from: " + firstDay + " to " + lastDay + " of May";

		}
		return s;
	}

	/**
	 * This method cancels all the reservations for a client. This method is locked.
	 * 
	 * @param name - name of client
	 * @return returns a string confirmation message depicting cancellation
	 */
	public synchronized String cancelReservation(String name) {
		String s = "";
		boolean count = false;
		;
		for (int i = 0; i < 31; i++) {
			if (booking.get(i).equalsIgnoreCase(name)) {
				booking.set(i, "default");
				count = true;
				;
			}
		}
		if (count) {
			s += "All reservations cancelled for " + name;
		} else {
			s += "There were no bookings for " + name;

		}
		return s;
	}

	/**
	 * This method prints all the information about booking made in the hotel for
	 * month of may displaying if the days are available or booked.
	 * 
	 * @return returns a string giving information about all booking of month of
	 *         May.
	 */
	public String reservationInformation() {
		System.out.println("Reservation information for month of May");
		String s = "";
		for (int i = 0; i < 31; i++) {
			int index = i + 1;
			s += (index) + ": ";
			if (booking.get(i) == "default") {
				s += "Available\n";
			} else {
				s += "Booked for: " + booking.get(i) + "\n";
			}
			index++;
		}
		return s;
	}
}
