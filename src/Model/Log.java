package Model;

public class Log {
    private static Log instance;
    private StringBuilder log;

    private Log() {
        log = new StringBuilder();
    }

    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void addEntry(String entry) {
        log.append(entry).append("\n");
    }

    public void writeLogToFile(String filename) {
        try (java.io.FileWriter writer = new java.io.FileWriter(filename)) {
            writer.write(log.toString());
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
