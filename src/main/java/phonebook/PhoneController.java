package phonebook;

import utilities.*;

import java.util.ArrayList;
import java.util.Collection;

public class PhoneController implements Controller {
    private final PhoneBookView view = new PhoneBookView();
    private final PhoneBookModel model = new PhoneBookModel();
    private final String source = "src/main/java/phonebook/source.json";
    private final String result = "src/main/java/phonebook/result.json";
    private final String menu = """
            Выберите действие:
            1. Импортировать контакты
            2. Добавить контакт
            3. Добавить номер телефона
            4. Удалить номер телефона
            5. Удалить контакт
            6. Экспортировать контакты
            7. Показать список контактов
            8. Просмотреть контакт
            9. Очистить телефонную книгу
            0. Выход

            Введите число:\s""";

    private void addContact() {
        Contact contact = view.getContact();
        model.addNumber(contact, view.getNumber());
        model.addContact(contact);
        view.success("Контакт успешно добавлен");
    }

    private void addNumber() {
        if (!model.isEmpty()) {
            view.printContacts(model.getAllContacts().values());
            Contact contact = model.getContactById(view.getInt("Введите id контакта: "));
            if (contact != null) {
                model.addNumber(contact, view.getNumber());
                view.success("Телефон добавлен");
            } else {
                view.error("Некорректный выбор");
            }
        } else {
            view.error("Список контактов пуст");
        }
    }

    private void clear() {
        model.clear();
        view.success("Телефонная книга успешно очищена");
    }

    @Override
    public void exit() {
        view.info("Главное меню");
    }

    public void option(int choice) {
        switch(choice) {
            case 1 -> read();
            case 2 -> addContact();
            case 3 -> addNumber();
            case 4 -> removePhoneNumber();
            case 5 -> removeContact();
            case 6 -> save();
            case 7 -> view.printContacts(model.getAllContacts().values());
            case 8 -> printContact();
            case 9 -> clear();
            case 0 -> exit();

            default -> view.error("Некорректный выбор");
        }
    }

    private void printContact() {
        if (!model.isEmpty()) {
            view.printContacts(model.getAllContacts().values());
            Contact contact = model.getContactById(view.getInt("Введите id контакта: "));
            if (contact != null) {
                view.info(contact.toString());
            } else {
                ConsoleIO.error("Некорректный выбор");
            }
        } else {
            view.error("Список контактов пуст");
        }
    }

    protected void read() {
        Serializer<Contact> serializer = new JsonSerializer<>();
        FileManager<Contact> fileManager = new FileManager<>();
        try {
            Collection<Contact> contacts = fileManager.read(source, serializer, Contact.class);
            contacts.forEach(model::addContact);
            view.success(String.format("Контакты успешно загружены из файла %s", source));
        } catch (Exception e) {
            view.error(String.format("Произошла ошибка при чтении файла %s", source));
        }
    }

    private void removeContact(){
        if (!model.isEmpty()) {
            view.printContacts(model.getAllContacts().values());
            int choice = view.getInt("Введите id контакта: ");
            Contact contact = model.getContactById(choice);
            if (contact != null) {
                model.deleteContact(choice);
                view.success("Контакт успешно удалён.");
            } else {
                view.error("Некорректный выбор");
            }
        } else {
            view.error("Список контактов пуст");
        }
    }

    private void removePhoneNumber() {
        if (!model.isEmpty()) {
            view.printContacts(model.getAllContacts().values());
            int choice = view.getInt("Введите id контакта: ");
            Contact contact = model.getContactById(choice);
            if (contact != null) {
                ArrayList<Number> numbers = contact.getPhoneNumbers();
                view.printNumbers(numbers);
                choice = view.getInt("Какой номер телефона вы хотите удалить? Введите номер: ") - 1;
                if (choice < 0 || choice > numbers.size()) {
                    view.error("Некорректный выбор");
                    return;
                }
                model.removePhoneNumber(contact, numbers.get(choice));
                view.success("Номер телефона успешно удалён.");
            } else {
                view.error("Некорректный выбор");
            }
        } else {
            view.error("Список контактов пуст");
        }
    }

    public void save() {
        Collection<Contact> contacts = model.getAllContacts().values();
        Serializer<Contact> serializer = new JsonSerializer<>();
        FileManager<Contact> fileManager = new FileManager<>();
        try {
            fileManager.write(result, contacts, serializer);
            view.success(String.format("Результат успешно записан в файл %s", result));
        } catch (Exception e) {
            view.error(String.format("Произошла ошибка при записи в файл %s", result));
        }
    }

    public void start() {
        int choice;
        do {
            choice = ConsoleIO.intInput(menu);
            option(choice);
        } while (choice != 0);
    }
}
