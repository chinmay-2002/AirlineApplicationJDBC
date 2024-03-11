package com.operation;

import java.sql.DriverManager;
import java.util.Scanner;

import com.data.Flight;
import com.data.Passenger;

public class Operations {
	
	
	public void insertPassengerDetails(DatabaseManager dm) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Enter Passenger Adhar ID :");
		long adhar = sc.nextLong();
		
		System.out.println("Enter Passenger Name :");
		sc.nextLine(); 
		String name = sc.nextLine();
		
		System.out.println("Enter Passenger Gender :");
		
		String gender = sc.nextLine();
//		sc.nextLine(); 
		
		System.out.println("Enter Passenger Date of Birth in dd-mm-yyyy :");
		 
		String dob = sc.nextLine();
//		sc.nextLine();
		System.out.println("Enter Passenger Address :");
		 
		String address = sc.nextLine();
		
		Passenger ps = new Passenger(adhar, name, dob,gender, address, 0);
		dm.insertPassenger(ps);
	
	}
	
	public void insertFlightDetails(DatabaseManager dm) {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("Enter Flight ID :");
		int id = sc.nextInt();
		
		System.out.println("Enter Flight Name :");
		String name = sc.next();
		
		System.out.println("Enter Availaible Seats :");
		int seats = sc.nextInt();
		
		Flight f = new Flight(id, name, seats);
		dm.insertFlight(f);
	}
	
	public void bookTicket(DatabaseManager dm) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Passenger Adhar :");
		long adhar = sc.nextLong();
		
		System.out.println("Enter Flight ID :");
		int flightId = sc.nextInt();
		
		dm.bookTicket(adhar, flightId);
	}
	
	public void cancelTicket(DatabaseManager dm) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Passenger Adhar to cancel Current Flight :");
		long adhar = sc.nextLong();
		dm.cancelTickett(adhar);
	}
	
	public void displayVacant(DatabaseManager dm) {
		dm.displayVacantSeatsandFlights();
		System.out.println("------------------------------------------------------");
	}
	
	public void updatePassengerDetails(DatabaseManager dm) {
		Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Adhar number of the passenger: ");
        long adhar = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Select the column to update:");
        System.out.println("1. Name");
        System.out.println("2. Sex");
        System.out.println("3. Date of Birth");
        System.out.println("4. Address");
        int choice = scanner.nextInt();
        scanner.nextLine();
        String columnName = "";
        switch (choice) {
            case 1:
                columnName = "name";
                break;
            case 2:
                columnName = "sex";
                break;
            case 3:
                columnName = "dob";
                break;
            case 4:
                columnName = "address";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("Enter the new value for " + columnName + ": ");
        String newValue = scanner.nextLine();

        dm.updatePassenger(adhar, columnName, newValue);
        
    }
	
	public void updateFlightDetails(DatabaseManager dm) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the flight ID: ");
        int flightId = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Select the column to update:");
        System.out.println("1. Flight Name");
        System.out.println("2. Seats");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        String columnName = "";
        switch (choice) {
            case 1:
                columnName = "flightname";
                break;
            case 2:
                columnName = "seats";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("Enter the new value for " + columnName + ": ");
        String newValue = scanner.nextLine();

        dm.updateFlight(flightId, columnName, newValue);
        System.out.println("------------------------------------------------------");
	}
	
	public void closeConnection(DatabaseManager dm) {
		dm.close();
	}
}
