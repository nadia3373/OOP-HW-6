package utilities;

import java.math.BigInteger;
import java.util.Scanner;

public interface ConsoleIO extends ConsoleColors {
    Scanner scan = new Scanner(System.in);
    static BigInteger bigIntInput(String message) {
        while (true) {
            prompt(message);
            try {
                return new BigInteger(scan.nextLine());
            } catch (NumberFormatException e) {
                error("Введено не число");
            }
        }
    }

    static void close() {
        scan.close();
    }

    static double doubleInput(String message) {
        while (true) {
            prompt(message);
            try {
                return Double.parseDouble(scan.nextLine());
            } catch (NumberFormatException e) {
                error("Введено не число");
            }
        }
    }

    static void error(String text) {
        System.out.printf("%sERROR: %s%s\n", ERROR, text, RESET);
    }

    static void info(String text) {
        System.out.printf("%s%s%s\n", INFO, text, RESET);
    }

    static int intInput(String message) {
        while (true) {
            prompt(message);
            try {
                return Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                error("Введено не число");
            }
        }
    }

    static void prompt (String text) {
        System.out.printf("%s", text);
    }

    static String stringInput(String message) {
        prompt(message);
        return scan.nextLine();
    }

    static void success (String text) {
        System.out.printf("%s%s%s\n", SUCCESS, text, RESET);
    }
}
