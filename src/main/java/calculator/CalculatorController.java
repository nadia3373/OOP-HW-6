package calculator;

import utilities.Controller;
import utilities.Logger;

import java.math.BigInteger;

public class CalculatorController implements Controller {
    private final CalculatorView view = new CalculatorView();
    private final CalculatorModel model = new CalculatorModel();
    private final Logger logger =  new Logger("src/main/java/calculator/logs/calculator.log");
    CalculatorType type = CalculatorType.COMPLEX;
    private String complexMenu = """
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
            
            Введите номер:\s""";
    private String rationalMenu = """
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
            
            Введите номер:\s""";
    private String menu = complexMenu;

    @Override
    public void exit() {
        logger.close();
        view.info("Главное меню");
    }

    @Override
    public void option(int choice) {
        if (type.equals(CalculatorType.RATIONAL)) {
            switch (choice) {
                case 1 -> rationalAdd();
                case 2 -> rationalSubtract();
                case 3 -> rationalMultiply();
                case 4 -> rationalDivide();
                case 5 -> rationalPower();
                case 6 -> rationalSqrt();
                case 7 -> rationalGcd();
                case 8 -> rationalLcm();
                case 9 -> switchMode();
                case 0 -> exit();

                default -> view.error("Некорректный выбор");
            }
        } else {
            switch (choice) {
                case 1 -> complexAdd();
                case 2 -> complexSubtract();
                case 3 -> complexMultiply();
                case 4 -> complexDivide();
                case 5 -> complexPower();
                case 6 -> complexSqrt();
                case 7 -> complexModulus();
                case 8 -> complexArgument();
                case 9 -> switchMode();
                case 0 -> exit();

                default -> view.error("Некорректный выбор");
            }
        }
    }

    private void complexSqrt() {
        ComplexNumber num = view.getComplexNumber();
        ComplexNumber result = model.complexSqrt(num);
        logger.info(String.format("Извлечение корня из комплексного числа %s -> %s", num, result));
        view.info(String.format("Результат извлечения корня из комплексного числа %s равен %s", num, result));
    }

    private void complexPower() {
        ComplexNumber num = view.getComplexNumber();
        int power = view.getInt("Введите степень: ");
        ComplexNumber result = model.complexPower(num, power);
        logger.info(String.format("Возведение комплексного числа %s в степень %s -> %s", num, power, result));
        view.info(String.format("Результат возведения %s в степень %s равен %s", num, power, result));
    }

    private void complexDivide() {
        ComplexNumber num1 = view.getComplexNumber();
        ComplexNumber num2 = view.getComplexNumber();
        ComplexNumber result = model.complexDivide(num1, num2);
        logger.info(String.format("Деление комплексных чисел %s и %s -> %s", num1, num2, result));
        view.info(String.format("Результат деления %s и %s равен %s", num1, num2, result));
    }

    private void complexMultiply() {
        ComplexNumber num1 = view.getComplexNumber();
        ComplexNumber num2 = view.getComplexNumber();
        ComplexNumber result = model.complexMultiply(num1, num2);
        logger.info(String.format("Умножение комплексных чисел %s и %s -> %s", num1, num2, result));
        view.info(String.format("Результат умножения %s и %s равен %s", num1, num2, result));
    }

    private void complexSubtract() {
        ComplexNumber num1 = view.getComplexNumber();
        ComplexNumber num2 = view.getComplexNumber();
        ComplexNumber result = model.complexSubtract(num1, num2);
        logger.info(String.format("Вычитание комплексных чисел %s и %s -> %s", num1, num2, result));
        view.info(String.format("Результат вычитания %s и %s равен %s", num1, num2, result));
    }

    private void complexAdd() {
        ComplexNumber num1 = view.getComplexNumber();
        ComplexNumber num2 = view.getComplexNumber();
        ComplexNumber result = model.complexAdd(num1, num2);
        logger.info(String.format("Сложение комплексных чисел %s и %s -> %s", num1, num2, result));
        view.info(String.format("Результат сложения %s и %s равен %s", num1, num2, result));
    }

    private void complexModulus() {
        ComplexNumber num = view.getComplexNumber();
        double result = model.complexModulus(num);
        logger.info(String.format("Модуль комплексного числа %s -> %s", num, result));
        view.info(String.format("Модуль %s равен %.2f", num, result));
    }

    private void complexArgument() {
        ComplexNumber num = view.getComplexNumber();
        double result = model.complexArgument(num);
        logger.info(String.format("Аргумент комплексного числа %s -> %s", num, result));
        view.info(String.format("Аргумент %s равен %.2f", num, result));
    }

    private void rationalLcm() {
        RationalNumber num1 = view.getRationalNumber();
        RationalNumber num2 = view.getRationalNumber();
        BigInteger result = model.rationalLcm(num1, num2);
        logger.info(String.format("Наименьшее общее кратное рациональных чисел %s и %s -> %s", num1, num2, result));
        view.info(String.format("Наименьшее общее кратное %s и %s равно %s", num1, num2, result));
    }

    private void rationalGcd() {
        RationalNumber num1 = view.getRationalNumber();
        RationalNumber num2 = view.getRationalNumber();
        RationalNumber result = model.rationalGcd(num1, num2);
        logger.info(String.format("Приведение к общему знаменателю рациональных чисел %s и %s -> %s", num1, num2, result));
        view.info(String.format("Результат приведения к общему знаменателю %s и %s равен %s", num1, num2, result));
    }

    private void rationalSqrt() {
        RationalNumber num = view.getRationalNumber();
        RationalNumber result = model.rationalSqrt(num);
        logger.info(String.format("Извлечение квадратного корня из рационального числа %s -> %s", num, result));
        view.info(String.format("Результат извлечения квадратного корня из %s равен %s", num, result));
    }

    private void rationalPower() {
        RationalNumber num = view.getRationalNumber();
        int power = view.getInt("Введите степень: ");
        RationalNumber result = model.rationalPower(num, power);
        logger.info(String.format("Возведение рационального числа %s в степень %s -> %s", num, power, result));
        view.info(String.format("Результат возведения %s в степень %s равен %s", num.toString(), power, result.toString()));
    }

    private void rationalDivide() {
        RationalNumber num1 = view.getRationalNumber();
        RationalNumber num2 = view.getRationalNumber();
        RationalNumber result = model.rationalDivide(num1, num2);
        logger.info(String.format("Деление рациональных чисел %s и %s -> %s", num1, num2, result));
        view.info(String.format("Результат деления %s и %s равен %s", num1, num2, result));
    }

    private void rationalMultiply() {
        RationalNumber num1 = view.getRationalNumber();
        RationalNumber num2 = view.getRationalNumber();
        RationalNumber result = model.rationalMultiply(num1, num2);
        logger.info(String.format("Умножение рациональных чисел %s и %s -> %s", num1, num2, result));
        view.info(String.format("Результат умножения %s и %s равен %s", num1, num2, result));
    }

    private void rationalSubtract() {
        RationalNumber num1 = view.getRationalNumber();
        RationalNumber num2 = view.getRationalNumber();
        RationalNumber result = model.rationalSubtract(num1, num2);
        logger.info(String.format("Вычитание рациональных чисел %s и %s -> %s", num1, num2, result));
        view.info(String.format("Результат вычитания %s и %s равен %s", num1, num2, result));
    }

    private void rationalAdd() {
        RationalNumber num1 = view.getRationalNumber();
        RationalNumber num2 = view.getRationalNumber();
        RationalNumber result = model.rationalAdd(num1, num2);
        logger.info(String.format("Сложение рациональных чисел %s и %s -> %s", num1, num2, result));
        view.info(String.format("Результат сложения %s и %s равен %s", num1, num2, result));
    }

    @Override
    public void start() {
        int choice;
        do {
            choice = view.getInt(menu);
            option(choice);
        } while (choice != 0);
    }

    private void switchMode() {
        type = type.equals(CalculatorType.COMPLEX) ? CalculatorType.RATIONAL : CalculatorType.COMPLEX;
        menu = menu.equals(complexMenu) ? rationalMenu : complexMenu;
    }
}
