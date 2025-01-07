package Model;

import java.io.FileWriter;
import java.io.IOException;

public class Log {
    private static Log instance;
    private StringBuilder logData = new StringBuilder();

    private Log() {}

    public static synchronized Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void addEntry(String entry) {
        logData.append(entry).append("\n");
    }

    public void writeToFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(logData.toString());
        } catch (IOException e) {
            System.out.println("Error writing log file: " + e.getMessage());
        }
    }
}
