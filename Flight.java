
package com.example.sp24bse126;
import javafx.beans.property.*;

public class Flight {
    private StringProperty flightID;
    private StringProperty fromCity;
    private StringProperty toCity;
    private StringProperty departureTime;
    private StringProperty arrivalTime;


    // Constructor
    public Flight(String flightID, String fromCity, String toCity, String departureTime, String arrivalTime) {
        this.flightID = new SimpleStringProperty(flightID);
        this.fromCity = new SimpleStringProperty(fromCity);
        this.toCity = new SimpleStringProperty(toCity);
        this.departureTime = new SimpleStringProperty(departureTime);
        this.arrivalTime = new SimpleStringProperty(arrivalTime);

    }

    // Getter methods for properties
    public StringProperty flightIDProperty() {
        return flightID;
    }

    public StringProperty fromCityProperty() {
        return fromCity;
    }

    public StringProperty toCityProperty() {
        return toCity;
    }

    public StringProperty departureTimeProperty() {
        return departureTime;
    }

    public StringProperty arrivalTimeProperty() {
        return arrivalTime;
    }



    // Regular getter methods
    public String getFlightID() {
        return flightID.get();
    }

    public String getFromCity() {
        return fromCity.get();
    }

    public String getToCity() {
        return toCity.get();
    }

    public String getDepartureTime() {
        return departureTime.get();
    }

    public String getArrivalTime() {
        return arrivalTime.get();
    }



    // Regular setter methods
    public void setFlightID(String flightID) {
        this.flightID.set(flightID);
    }

    public void setFromCity(String fromCity) {
        this.fromCity.set(fromCity);
    }

    public void setToCity(String toCity) {
        this.toCity.set(toCity);
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime.set(departureTime);
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime.set(arrivalTime);
    }



    @Override
    public String toString() {
        return flightID.get() + " from " + fromCity.get() + " to " + toCity.get() + " at " + departureTime.get();
    }
}
