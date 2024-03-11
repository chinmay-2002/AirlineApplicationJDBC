package com.operation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.data.Flight;
import com.data.Passenger;

public class DatabaseManager {
    private Connection connection;

    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/chinmayairline", "root", "");
            System.out.println("Connected to database!");
        } catch (SQLException e) {
        	System.out.println("Exception occurs in Connection!");
            e.printStackTrace();
        }
    }
    
    
    public void insertPassenger(Passenger passenger) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO passengers VALUES (?, ?, ?, ?, ?, ?)");
            statement.setLong(1, passenger.getAdhar());
            statement.setString(2, passenger.getName());
            statement.setString(3, passenger.getSex());
            statement.setString(4, passenger.getDob());
            statement.setString(5, passenger.getAddress());
            statement.setInt(6, passenger.getFlightid());
            statement.executeUpdate();
            System.out.println("Passenger Added Successfully!");
            System.out.println("--------------------------------------------");
            System.out.println();
            statement.close();
        } catch (SQLException e) {
        	System.out.println("Exception occurs in Passenger Insertion!");
            e.printStackTrace();
        }
    }

    public void insertFlight(Flight flight) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO flights values (?, ?, ?)");
            statement.setInt(1, flight.getFlightid());
            statement.setString(2, flight.getName());
            statement.setInt(3, flight.getSeats());
            statement.executeUpdate();
            System.out.println("Flight Added Successfully!");
            System.out.println("--------------------------------------------");
            statement.close();
        } catch (SQLException e) {
        	System.out.println("Exception occurs in Flights Insertion!");
            e.printStackTrace();
        }
    }
    
    public void bookTicket(long passengerId, int flightId) {
    	try {
    	String checkSeatsQuery = "SELECT seats FROM flights WHERE id = ?";
    	PreparedStatement preparedStatement = connection.prepareStatement(checkSeatsQuery);
        preparedStatement.setInt(1, flightId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int availableSeats = resultSet.getInt("seats");
            if (availableSeats > 0) {
                // Book the ticket
                String bookTicketQuery = "UPDATE passengers set flightid = ? where adhar = ?";
                preparedStatement = connection.prepareStatement(bookTicketQuery);
                preparedStatement.setInt(1, flightId);
                preparedStatement.setLong(2, passengerId);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    // Update available seats
                    String updateSeatsQuery = "UPDATE flights SET seats = ? WHERE id = ?";
                    preparedStatement = connection.prepareStatement(updateSeatsQuery);
                    preparedStatement.setInt(1, availableSeats - 1);
                    preparedStatement.setInt(2, flightId);
                    int updatedRows = preparedStatement.executeUpdate();
                    if (updatedRows > 0) {
                        System.out.println("Opration Done!");
                    }
                    System.out.println("Ticket Booked Perfectly!");
                    System.out.println("-----------------------------------------------------");
                }
            } else {
                System.out.println("Sorry, no available seats for this flight.");
            }
        } else {
            System.out.println("Invalid flight ID.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    } 
    }
    
    public void cancelTickett(long passengerId) {
    	PreparedStatement preparedStatement = null;
    	try {

            // Get the flight ID of the passenger
            String getFlightIdQuery = "SELECT flightid FROM passengers WHERE adhar = ?";
            preparedStatement = connection.prepareStatement(getFlightIdQuery);
            preparedStatement.setLong(1, passengerId);
            int flightId = 0;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    flightId = resultSet.getInt("flightid");
                }
            }

            // Cancel the passenger's ticket
            String cancelTicketQuery = "UPDATE passengers SET flightid = 0 WHERE adhar = ?";
            preparedStatement = connection.prepareStatement(cancelTicketQuery);
            preparedStatement.setLong(1, passengerId);
            int rowsAffectedPassenger = preparedStatement.executeUpdate();

            // Increment available seats for the flight
            String incrementSeatsQuery = "UPDATE flights SET seats = seats + 1 WHERE id = ?";
            preparedStatement = connection.prepareStatement(incrementSeatsQuery);
            preparedStatement.setInt(1, flightId);
            int rowsAffectedFlight = preparedStatement.executeUpdate();

            // If both updates were successful, set success flag to true
            if (rowsAffectedPassenger > 0 && rowsAffectedFlight > 0) {
                System.out.println("Ticket Canceled Successfully!!!");
                System.out.println("------------------------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Got exception bro 1");
        } 
    }
    
    public void displayVacantSeatsandFlights() {
    	PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
    	try {
            String query = "SELECT id, flightname, seats FROM flights";
            preparedStatement = connection.prepareStatement(query);
            resultSet= preparedStatement.executeQuery();

            // Print table header
            System.out.println("------------------------------------------------------");
            System.out.printf("%-10s %-20s %-10s%n", "Flight ID", "Flight Name", "Vacant Seats");
            System.out.println("------------------------------------------------------");
            // Print table rows
            while (resultSet.next()) {
                int flightId = resultSet.getInt("id");
                String flightName = resultSet.getString("flightname");
                int vacantSeats = resultSet.getInt("seats");
                System.out.printf("%-10d %-20s %-10d%n", flightId, flightName, vacantSeats);
            }
            System.out.println("------------------------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void updatePassenger(long adhar, String columnName, String newValue) {
    	PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
    	try {
            String updateQuery = "UPDATE passengers SET " + columnName + " = ? WHERE adhar = ?";
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, newValue);
            preparedStatement.setLong(2, adhar);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Passenger Details Updated Successfully!");
                System.out.println("------------------------------------------------------");
            } else {
                System.out.println("No rows were updated. Please check the provided Adhar number.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    
    public void updateFlight(int flightId, String columnName, String newValue) {
    	PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
    	try {

            String updateQuery = "UPDATE flights SET " + columnName + " = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, newValue);
            preparedStatement.setInt(2, flightId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Flights details Updated Successfully!");
            } else {
                System.out.println("No rows were updated. Please check the provided flight ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    
    public void close() {
    	try {
    		connection.close();
    	}
    	catch(Exception e) {
    		System.out.println("Failed to close connection!");
    	}
    }

}
