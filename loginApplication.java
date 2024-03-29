/*
 * Dustin L. Warren
 * March 22, 2024
 * SDEV 460
 * Homework 1
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

// Logging functionality imports
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class loginApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login Application");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER); // Center the GridPane itself within the scene
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label usernameLabel = new Label("Username:");
        TextField usernameTextField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        
        Button submitButton = new Button("Submit");
        Button resetButton = new Button("Reset");

        // Add components to gridPane
        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameTextField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(submitButton, 0, 2);
        gridPane.add(resetButton, 1, 2);

        // Center components within their cells
        GridPane.setHalignment(usernameLabel, HPos.CENTER);
        GridPane.setHalignment(usernameTextField, HPos.CENTER);
        GridPane.setHalignment(passwordLabel, HPos.CENTER);
        GridPane.setHalignment(passwordField, HPos.CENTER);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setHalignment(resetButton, HPos.CENTER);
        
        
        // Set Actions
        submitButton.setOnAction(event -> {
        	boolean isAuthenticated = authenticate(usernameTextField.getText(), passwordField.getText());
            logLoginAttempt(usernameTextField.getText(), isAuthenticated);
            if (isAuthenticated) {
                new MainContent().start(new Stage());
                primaryStage.close();
            } else {
                System.out.println("Login Failed");
            }
        });

        resetButton.setOnAction(event -> {
        	clearFields(usernameTextField, passwordField);
        });

        Scene scene = new Scene(gridPane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    protected boolean authenticate(String username, String password) {
        return "admin".equals(username) && "password".equals(password);
    }
    
    public void logLoginAttempt(String username, boolean isSuccess) {
        String logMessage = String.format("%s - Username: %s - Login Attempt: %s\n",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                username,
                isSuccess ? "Successful" : "Failed");

        // Define the path to the log file. Adjust the path as necessary.
        String logFilePath = "src/Log.txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, true))) {
            writer.write(logMessage);
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

	public void clearFields(TextField usernameField, PasswordField passwordField) {
		usernameField.clear();
        passwordField.clear();
	}
}
