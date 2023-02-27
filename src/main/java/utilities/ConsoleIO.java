package utilities;

import calculator.ComplexNumber;
import calculator.RationalNumber;

import java.math.BigInteger;
import java.util.Scanner;

public abstract class ConsoleIO {

    private static final String ERROR = "\u001B[31m";
    private static final String SUCCESS = "\u001B[32m";
    private static final String INFO = "\u001B[33m";
    private static final String RESET = "\u001B[0m";

    private static Scanner scan = new Scanner(System.in);

    public static BigInteger bigIntInput(String message) {
        while (true) {
            prompt(message);
            try {
                return new BigInteger(scan.nextLine());
            } catch (NumberFormatException e) {
                error("Введено не число");
            }
        }
    }

    public static void close() {
        scan.close();
    }

    public static ComplexNumber complexInput() {
        double real = doubleInput("Введите действительную часть: ");
        double imaginary = doubleInput("Введите мнимую часть: ");
        return new ComplexNumber(real, imaginary);
    }

    public static double doubleInput(String message) {
        while (true) {
            prompt(message);
            try {
                return Double.parseDouble(scan.nextLine());
            } catch (NumberFormatException e) {
                error("Введено не число");
            }
        }
    }

    public static void error(String text) {
        System.out.printf("%sERROR: %s%s\n", ERROR, text, RESET);
    }

    public static void info(String text) {
        System.out.printf("%s%s%s\n", INFO, text, RESET);
    }

    public static int intInput(String message) {
        while (true) {
            prompt(message);
            try {
                return Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                error("Введено не число");
            }
        }
    }

    public static void prompt (String text) {
        System.out.printf("%s", text);
    }

    public static RationalNumber rationalInput() {
        BigInteger numerator, denominator;
        numerator = bigIntInput("Введите числитель: ");
        do {
            denominator = bigIntInput("Введите знаменатель (!=0): ");
        } while (denominator.intValue() == 0);
        return new RationalNumber(numerator, denominator);
    }

    public static String stringInput(String message) {
        prompt(message);
        return scan.nextLine();
    }

    public static void success (String text) {
        System.out.printf("%s%s%s\n", SUCCESS, text, RESET);
    }
}
