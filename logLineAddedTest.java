/*
 * Dustin L. Warren
 * March 22, 2024
 * SDEV 460
 * Homework 1
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class logLineAddedTest {

	    private final Path logFilePath = Paths.get("src/Log.txt");
	    private final loginApplication app = new loginApplication();

	    @BeforeEach
	    void setUp() throws IOException {
	        // Ensure the log file exists before each test; create if not present.
	        if (!Files.exists(logFilePath)) {
	            Files.createFile(logFilePath);
	        }
	    }

	    @Test
	    void testLogLineAdded() throws IOException {
	        long initialLineCount = Files.lines(logFilePath).count();

	        // Perform a logging operation
	        app.logLoginAttempt("testUser", true);

	        long newLineCount = Files.lines(logFilePath).count();

	        // Assert that the line count increased by 1
	        assertEquals(initialLineCount + 1, newLineCount, "A new line should have been added to the log file.");
	    }
	}

