package utilities;

public interface View {
    default void info(String message) {
        ConsoleIO.info(message);
    }

    default void error(String message) {
        ConsoleIO.error(message);
    }

    default void success(String message) {
        ConsoleIO.success(message);
    }
}
