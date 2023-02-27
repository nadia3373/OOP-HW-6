package utilities;

import calculator.CalcApp;
import phonebook.PhoneApp;
import school.SchoolApp;

public class MainApp extends App {
    CalcApp calcApp;
    PhoneApp phoneApp;
    SchoolApp schoolApp;

    public MainApp() {
        super("""
            Выберите, какое приложение запустить:
            1. Калькулятор
            2. Телефонный справочник
            3. Информационная система школы
            0. Выход
                                
            Введите число:\s""");
        calcApp = new CalcApp(this);
        phoneApp = new PhoneApp(this);
        schoolApp = new SchoolApp(this);
     }
    public void option(int choice) {
        switch (choice) {
            case 1 -> calcApp.start();
            case 2 -> phoneApp.start();
            case 3 -> schoolApp.start();
            case 0 -> {
                ConsoleIO.info("Программа завершила работу");
                ConsoleIO.close();
            }

            default -> ConsoleIO.error("Некорректный выбор");
        }
    }
}
