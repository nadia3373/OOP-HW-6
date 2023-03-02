package main;

import calculator.CalculatorController;
import phonebook.PhoneController;
import school.SchoolController;
import utilities.ConsoleIO;
import utilities.Controller;

public class MainController implements Controller {
    private final MainView view = new MainView();
    private final MainModel model = new MainModel();
    private final CalculatorController calculatorController = new CalculatorController();
    private final PhoneController phoneController = new PhoneController();
    private final SchoolController schoolController = new SchoolController();
    private final String menu = """
            Выберите, какое приложение запустить:
            1. Калькулятор
            2. Телефонный справочник
            3. Информационная система школы
            0. Выход
            
            Введите номер:\s""";

    @Override
    public void exit() {
        ConsoleIO.close();
        view.info("Программа завершила работу");
    }

    @Override
    public void option(int choice) {
        switch (choice) {
            case 1 -> calculatorController.start();
            case 2 -> phoneController.start();
            case 3 -> schoolController.start();
            case 0 -> System.exit(0);
            default -> view.error("Некорректный выбор");
        }
    }

    @Override
    public void start() {
        int choice;
        do {
            choice = view.getInt(menu);
            option(choice);
        } while (choice != 0);
    }
}
