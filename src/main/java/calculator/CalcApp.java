package calculator;

import utilities.ConsoleIO;
import utilities.MainApp;
import utilities.SubApp;

import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class CalcApp extends SubApp {
    private static final Logger logger = Logger.getLogger(CalcApp.class.getName());
    private final ComplexCalculator complexCalc = new ComplexCalculator();
    private final RationalCalculator rationalCalc = new RationalCalculator();
    private Calculator currentCalc = complexCalc;
    public CalcApp(MainApp app) {
            super(app, "");
            menu = getMenu();
            try {
                logger.addHandler(new FileHandler("src/main/java/calculator/logs/calculator.log"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    protected String getMenu() {
        return currentCalc.equals(rationalCalc) ?
                """
                Режим работы с рациональными числами:
                1. Сложение
                2. Вычитание
                3. Умножение
                4. Деление
                5. Возведение в степень
                6. Извлечение корня
                7. Приведение к общему знаменателю
                8. НОК
                9. Переключить режим работы
                0. Выход
                                    
                Введите число:\s"""
                :
                """
                Режим работы с комплексными числами:
                1. Сложение
                2. Вычитание
                3. Умножение
                4. Деление
                5. Возведение в степень
                6. Извлечение корня
                7. Модуль комплексного числа
                8. Аргумент комплексного числа
                9. Переключить режим работы
                0. Выход
                                    
                Введите число:\s""";
    }

    protected void option(int choice) {
        if (currentCalc.equals(rationalCalc)) {
            switch (choice) {
                case 1 -> rationalAdd(ConsoleIO.rationalInput(), ConsoleIO.rationalInput());
                case 2 -> rationalSubtract(ConsoleIO.rationalInput(), ConsoleIO.rationalInput());
                case 3 -> rationalMultiply(ConsoleIO.rationalInput(), ConsoleIO.rationalInput());
                case 4 -> rationalDivide(ConsoleIO.rationalInput(), ConsoleIO.rationalInput());
                case 5 -> rationalPower(ConsoleIO.rationalInput(), ConsoleIO.intInput("Введите степень: "));
                case 6 -> rationalSqrt(ConsoleIO.rationalInput());
                case 7 -> rationalGcd(ConsoleIO.rationalInput(), ConsoleIO.rationalInput());
                case 8 -> rationalLcm(ConsoleIO.rationalInput(), ConsoleIO.rationalInput());
                case 9 -> switchMode();
                case 0 -> ConsoleIO.info("Главное меню");

                default -> ConsoleIO.error("Некорректный выбор");
            }
        } else {
            if (currentCalc.equals(complexCalc)) {
                switch (choice) {
                    case 1 -> complexAdd(ConsoleIO.complexInput(), ConsoleIO.complexInput());
                    case 2 -> complexSubtract(ConsoleIO.complexInput(), ConsoleIO.complexInput());
                    case 3 -> complexMultiply(ConsoleIO.complexInput(), ConsoleIO.complexInput());
                    case 4 -> complexDivide(ConsoleIO.complexInput(), ConsoleIO.complexInput());
                    case 5 -> complexPower(ConsoleIO.complexInput(), ConsoleIO.intInput("Введите степень: "));
                    case 6 -> complexSqrt(ConsoleIO.complexInput());
                    case 7 -> complexModulus(ConsoleIO.complexInput());
                    case 8 -> complexArgument(ConsoleIO.complexInput());
                    case 9 -> switchMode();
                    case 0 -> ConsoleIO.info("Главное меню");

                    default -> ConsoleIO.error("Некорректный выбор");
                }
            }
        }
    }

    private void complexSqrt(ComplexNumber num) {
        ComplexNumber result = complexCalc.sqrt(num);
        logger.info(String.format("Извелечение квадратного корня из комплексного числа %s -> %s", num, result));
        ConsoleIO.info(String.format("Результат извлечения квадратного корня из %s равен %s", num, result));
    }

    private void complexPower(ComplexNumber num1, int power) {
        ComplexNumber result = complexCalc.power(num1, power);
        logger.info(String.format("Возведение комплексного числа %s в степень %s -> %s", num1, power, result));
        ConsoleIO.info(String.format("Результат возведения %s в степень %s равен %s", num1, power, result));
    }

    private void complexDivide(ComplexNumber num1, ComplexNumber num2) {
        ComplexNumber result = complexCalc.divide(num1, num2);
        logger.info(String.format("Деление комплексных чисел %s и %s -> %s", num1, num2, result));
        ConsoleIO.info(String.format("Результат деления %s и %s равен %s", num1, num2, result));
    }

    private void complexMultiply(ComplexNumber num1, ComplexNumber num2) {
        ComplexNumber result = complexCalc.multiply(num1, num2);
        logger.info(String.format("Умножение комплексных чисел %s и %s -> %s", num1, num2, result));
        ConsoleIO.info(String.format("Результат умножения %s и %s равен %s", num1, num2, result));
    }

    private void complexSubtract(ComplexNumber num1, ComplexNumber num2) {
        ComplexNumber result = complexCalc.subtract(num1, num2);
        logger.info(String.format("Вычитание комплексных чисел %s и %s -> %s", num1, num2, result));
        ConsoleIO.info(String.format("Результат вычитания %s и %s равен %s", num1, num2, result));
    }

    private void complexAdd(ComplexNumber num1, ComplexNumber num2) {
        ComplexNumber result = complexCalc.add(num1, num2);
        logger.info(String.format("Сложение комплексных чисел %s и %s -> %s", num1, num2, result));
        ConsoleIO.info(String.format("Результат сложения %s и %s равен %s", num1, num2, result));
    }

    private void complexModulus(ComplexNumber num) {
        logger.info(String.format("Модуль комплексного числа %s -> %s", num, num.modulus()));
        ConsoleIO.info(String.format("Модуль %s равен %.2f", num, num.modulus()));
    }

    private void complexArgument(ComplexNumber num) {
        logger.info(String.format("Аргумент комплексного числа %s -> %s", num, num.argument()));
        ConsoleIO.info(String.format("Аргумент %s равен %.2f", num, num.argument()));
    }

    private void rationalLcm(RationalNumber num1, RationalNumber num2) {
        BigInteger result = rationalCalc.lcm(num1, num2);
        logger.info(String.format("Наименьшее общее кратное рациональных чисел %s и %s -> %s", num1, num2, result));
        ConsoleIO.info(String.format("Наименьшее общее кратное %s и %s равно %s", num1, num2, result));
    }

    private void rationalGcd(RationalNumber num1, RationalNumber num2) {
        RationalNumber result = rationalCalc.commonDenominator(num1, num2);
        logger.info(String.format("Приведение к общему знаменателю рациональных чисел %s и %s -> %s", num1, num2, result));
        ConsoleIO.info(String.format("Результат приведения к общему знаменателю %s и %s равен %s", num1, num2, result));
    }

    private void rationalSqrt(RationalNumber num) {
        RationalNumber result = rationalCalc.sqrt(num);
        logger.info(String.format("Извлечение квадратного корня из рационального числа %s -> %s", num, result));
        ConsoleIO.info(String.format("Результат извлечения квадратного корня из %s равен %s", num, result));
    }

    private void rationalPower(RationalNumber num1, int power) {
        RationalNumber result = rationalCalc.power(num1, power);
        logger.info(String.format("Возведение рационального числа %s в степень %s -> %s", num1, power, result));
        ConsoleIO.info(String.format("Результат возведения %s в степень %s равен %s", num1.toString(), power, result.toString()));
    }

    private void rationalDivide(RationalNumber num1, RationalNumber num2) {
        RationalNumber result = rationalCalc.divide(num1, num2);
        logger.info(String.format("Деление рациональных чисел %s и %s -> %s", num1, num2, result));
        ConsoleIO.info(String.format("Результат деления %s и %s равен %s", num1, num2, result));
    }

    private void rationalMultiply(RationalNumber num1, RationalNumber num2) {
        RationalNumber result = rationalCalc.multiply(num1, num2);
        logger.info(String.format("Умножение рациональных чисел %s и %s -> %s", num1, num2, result));
        ConsoleIO.info(String.format("Результат умножения %s и %s равен %s", num1, num2, result));
    }

    private void rationalSubtract(RationalNumber num1, RationalNumber num2) {
        RationalNumber result = rationalCalc.subtract(num1, num2);
        logger.info(String.format("Вычитание рациональных чисел %s и %s -> %s", num1, num2, result));
        ConsoleIO.info(String.format("Результат вычитания %s и %s равен %s", num1, num2, result));
    }

    private void rationalAdd(RationalNumber num1, RationalNumber num2) {
        RationalNumber result = rationalCalc.add(num1, num2);
        logger.info(String.format("Сложение рациональных чисел %s и %s -> %s", num1, num2, result));
        ConsoleIO.info(String.format("Результат сложения %s и %s равен %s", num1, num2, result));
    }

    public void read() {
    }

    public void save() {
    }

    public void switchMode() {
        currentCalc = currentCalc.equals(complexCalc) ? rationalCalc : complexCalc;
        menu = getMenu();
    }
}