
package com.example.sp24bse126;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDatabase {
    private List<Flight> flights;

    // Constructor
    public FlightDatabase() {
        flights = new ArrayList<>();
    }

    // Load flights from a file
    public void loadFlights(String fileName) {
        flights.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Loading: " + line); // Debugging
                String[] flightData = line.split(",");
                if (flightData.length == 5) {
                    String flightID = flightData[0].trim();
                    String fromCity = flightData[1].trim();
                    String toCity = flightData[2].trim();
                    String departureTime = flightData[3].trim();
                    String arrivalTime = flightData[4].trim();

                    Flight flight = new Flight(flightID, fromCity, toCity, departureTime, arrivalTime);
                    flights.add(flight);
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save flights to a file
    public void saveFlights(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Flight flight : flights) {
                writer.write(flight.getFlightID() + "," + flight.getFromCity() + "," + flight.getToCity() + ","
                        + flight.getDepartureTime() + "," + flight.getArrivalTime() );
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add a flight
    public void addFlight(Flight flight) {
        flights.add(flight);
        saveFlights(getClass().getResource("/flights.txt").getPath());
    }

    // Delete a flight by ID
    public boolean deleteFlight(String flightID) {
        boolean removed = flights.removeIf(flight -> flight.getFlightID().equals(flightID));
        if (removed) {
            saveFlights(getClass().getResource("/flights.txt").getPath()); // Save changes to the file
        }
        return removed;
    }
    // Get all flights
    public List<Flight> getFlights() {
        return flights;
    }

    // Search for flights based on "From" and "To" cities
    public List<Flight> searchFlights(String fromCity, String toCity) {
        List<Flight> availableFlights = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getFromCity().equalsIgnoreCase(fromCity) && flight.getToCity().equalsIgnoreCase(toCity)) {
                availableFlights.add(flight);
            }
        }
        return availableFlights;
    }
}

