package utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private final String path;
    private PrintWriter writer;

    public Logger(String path) {
        this.path = path;
        try {
            this.writer = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
        } catch (IOException e) {
            ConsoleIO.error("Не удалось открыть файл " + path + ": " + e.getMessage());
            this.writer = null;
        }
    }

    public void info(String message) {
        write("INFO", message);
    }

    public void error(String message) {
        write("ERROR", message);
    }

    private void write(String level, String message) {
        if (writer == null) {
            return;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDate = dateFormat.format(new Date());
        String logMessage = String.format("[%s] %s %s", formattedDate, level, message);
        writer.println(logMessage);
        writer.flush();
    }

    public void close() {
        if (writer != null) {
            writer.close();
        }
    }
}
