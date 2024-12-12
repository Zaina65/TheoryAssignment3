package com.example.sp24bse126;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class AdminPanel extends Application {

    private FlightDatabase flightDatabase;
    private TableView<Flight> flightTable;
    @Override
    public void start(Stage primaryStage) {
        flightDatabase = new FlightDatabase();
        flightDatabase.loadFlights(getClass().getResource("/flights.txt").getPath());


        Button addButton = new Button("Add Flight");
        Button deleteButton = new Button("Delete Flight");
        Button viewButton = new Button("View Flights");


        HBox buttonBox = new HBox(10, addButton, deleteButton, viewButton);
        buttonBox.setStyle("-fx-padding: 10; -fx-alignment: center;");

        //  dynamic content
        StackPane contentArea = new StackPane();

        // Initialize the flight table
        flightTable = createFlightTable();
        // Initialize with the loaded flights
        ObservableList<Flight> flightObservableList = FXCollections.observableArrayList(flightDatabase.getFlights());
        flightTable.setItems(flightObservableList);

        // Display flight table(View Flights)
        contentArea.getChildren().add(flightTable);

        // Set actions for buttons
        addButton.setOnAction(e -> {
            contentArea.getChildren().clear();
            contentArea.getChildren().add(createFlightForm());
        });

        deleteButton.setOnAction(e -> {
            contentArea.getChildren().clear();
            contentArea.getChildren().add(createDeleteForm());
        });

        viewButton.setOnAction(e -> {
            contentArea.getChildren().clear();
            contentArea.getChildren().add(flightTable); // Use the instance flightTable
        });

        // Main layout
        VBox layout = new VBox(10, buttonBox, contentArea);
        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setTitle("Admin Panel");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createFlightForm() {
        VBox formLayout = new VBox(10);
        Label flightIDLabel = new Label("Flight ID:");
        TextField flightIDField = new TextField();
        Label fromLabel = new Label("From:");
        ComboBox<String> fromComboBox = new ComboBox<>();
        fromComboBox.getItems().addAll("Karachi", "Lahore", "Islamabad", "Peshawar");
        Label toLabel = new Label("To:");
        ComboBox<String> toComboBox = new ComboBox<>();
        toComboBox.getItems().addAll("Karachi", "Lahore", "Islamabad", "Peshawar");
        Label departureLabel = new Label("Departure Time:");
        TextField departureField = new TextField();
        Label arrivalLabel = new Label("Arrival Time:");
        TextField arrivalField = new TextField();

        Button addFlightButton = new Button("Add Flight");

        // Add Flight action
        addFlightButton.setOnAction(e -> {
            try {
                String flightID = flightIDField.getText();
                String fromCity = fromComboBox.getValue();
                String toCity = toComboBox.getValue();
                String departureTime = departureField.getText();
                String arrivalTime = arrivalField.getText();

                Flight newFlight = new Flight(flightID, fromCity, toCity, departureTime, arrivalTime );
                flightDatabase.addFlight(newFlight); // Add and save flight
                // Refresh the table view
                flightTable.setItems(FXCollections.observableArrayList(flightDatabase.getFlights()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Flight added successfully!", ButtonType.OK);
                alert.showAndWait();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input. Please try again.", ButtonType.OK);
                alert.showAndWait();
            }
        });

        formLayout.getChildren().addAll(flightIDLabel, flightIDField, fromLabel, fromComboBox, toLabel, toComboBox,
                departureLabel, departureField, arrivalLabel, arrivalField, addFlightButton);
        return formLayout;
    }

    private VBox createDeleteForm() {
        VBox deleteFormLayout = new VBox(10);
        Label deleteFlightLabel = new Label("Enter Flight ID to delete:");
        TextField deleteFlightIDField = new TextField();
        Button deleteFlightButton = new Button("Delete Flight");

        // Delete Flight action
        deleteFlightButton.setOnAction(e -> {
            String flightID = deleteFlightIDField.getText();
            boolean success = flightDatabase.deleteFlight(flightID);
            if (success) {
                flightTable.setItems(FXCollections.observableArrayList(flightDatabase.getFlights())); // Refresh table
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Flight Deleted");
                alert.setContentText("Flight with ID " + flightID + " has been deleted.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Flight Not Found");
                alert.setContentText("No flight found with ID " + flightID + ".");
                alert.showAndWait();
            }
        });

        deleteFormLayout.getChildren().addAll(deleteFlightLabel, deleteFlightIDField, deleteFlightButton);
        return deleteFormLayout;
    }

    private TableView<Flight> createFlightTable() {
        TableView<Flight> table = new TableView<>();

        // Define columns for the flight details
        TableColumn<Flight, String> flightIDColumn = new TableColumn<>("Flight ID");
        flightIDColumn.setCellValueFactory(cellData -> cellData.getValue().flightIDProperty());

        TableColumn<Flight, String> fromColumn = new TableColumn<>("From");
        fromColumn.setCellValueFactory(cellData -> cellData.getValue().fromCityProperty());

        TableColumn<Flight, String> toColumn = new TableColumn<>("To");
        toColumn.setCellValueFactory(cellData -> cellData.getValue().toCityProperty());

        TableColumn<Flight, String> departureColumn = new TableColumn<>("Departure");
        departureColumn.setCellValueFactory(cellData -> cellData.getValue().departureTimeProperty());

        TableColumn<Flight, String> arrivalColumn = new TableColumn<>("Arrival");
        arrivalColumn.setCellValueFactory(cellData -> cellData.getValue().arrivalTimeProperty());

        // Add columns to the table
        table.getColumns().addAll(flightIDColumn, fromColumn, toColumn, departureColumn, arrivalColumn);

        return table;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
