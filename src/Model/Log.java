package Model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Log {
    private static Log instance;
    private StringBuilder logBuffer;

    private Log() {
        logBuffer = new StringBuilder();
    }

    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void addEntry(String entry) {
        logBuffer.append(entry).append("\n");
    }

    public void writeToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.write(logBuffer.toString());
        } catch (Exception e) {
            System.out.println("Error writing log to file: " + e.getMessage());
        }
    }
}
