/*
 * Dustin L. Warren
 * March 22, 2024
 * SDEV 460
 * Homework 1
 */

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainContent {

    public void start(Stage stage) {
        stage.setTitle("Main Content");

        VBox vbox = new VBox(new Label("Welcome! You are now logged in."));
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 300, 200);
        stage.setScene(scene);
        stage.show();
    }
}
