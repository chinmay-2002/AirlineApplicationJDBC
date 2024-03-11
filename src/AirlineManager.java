import java.rmi.server.Operation;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.data.Passenger;
import com.operation.DatabaseManager;
import com.operation.Operations;

public class AirlineManager {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Operations op = new Operations();
		Scanner sc = new Scanner(System.in);
		DatabaseManager dm = new DatabaseManager();
		int choice = 0;
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		System.out.println("---------SHRI SHRI CHINMAY JI AIRLINE SERVICES-------");
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		while (true) {
            System.out.println("1. Insert Passenger Details");
            System.out.println("2. Insert Flight Details");
            System.out.println("3. Book Ticket");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. Display Vacancy Seat Details");
            System.out.println("6. Update Passenger Information");
            System.out.println("7. Update Flight Details");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
           
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                	op.insertPassengerDetails(dm);
                    break;
                case 2:
                    op.insertFlightDetails(dm);
                    break;
                case 3:
                    op.bookTicket(dm);
                    break;
                case 4:
                    op.cancelTicket(dm);
                    break;
                case 5:
                    op.displayVacant(dm);
                    break;
                case 6:
                    op.updatePassengerDetails(dm);
                    break;
                case 7:
                    op.updateFlightDetails(dm);
                    break;
                case 8:
                	op.closeConnection(dm);		
                    System.out.println("Exiting... Bye Bye");
                    System.out.println("I hope You loved SHRI SHRI CHINMAY JI's AIRLINE service!");
                    System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
		}

	}

}
