/*
 * Dustin L. Warren
 * March 22, 2024
 * SDEV 460
 * Homework 1
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import javafx.application.Platform;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

// Class that runs all my JUnit Tests
public class loginApplicationTest extends loginApplication {

	// Setup the JavaFX environment
	@BeforeAll
    public static void setUpClass() throws Exception {
        // Initialize JavaFX environment
        Platform.startup(() -> {});
    }
	
	@Test
    void testSuccessfulAuthentication() {
        loginApplication app = new loginApplication();
        assertTrue(app.authenticate("admin", "password"));
    }

    @Test
    void testFailedAuthentication() {
        loginApplication app = new loginApplication();
        assertFalse(app.authenticate("admin", "wrongpassword"));
        assertFalse(app.authenticate("wronguser", "password"));
    }

    @Test
    void testEmptyCredentials() {
        loginApplication app = new loginApplication();
        assertFalse(app.authenticate("", ""));
        assertFalse(app.authenticate("admin", ""));
        assertFalse(app.authenticate("", "password"));
    }
    
    // Testing the Clear Fields button
    
    @Test
    public void testClearFields() {
        TextField usernameField = new TextField("username");
        PasswordField passwordField = new PasswordField();
        passwordField.setText("password");

        loginApplication app = new loginApplication();
        app.clearFields(usernameField, passwordField);
        
        assertTrue(usernameField.getText().isEmpty());
        assertTrue(passwordField.getText().isEmpty());
    }
}
