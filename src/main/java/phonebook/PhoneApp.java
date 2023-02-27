package phonebook;

import utilities.*;

import java.util.Collection;
import java.util.List;

public class PhoneApp extends SubApp {
    private final String source, result;
    public PhoneApp(MainApp app) {
        super(app, """
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
                                
            Введите число:\s""");
        source = "src/main/java/phonebook/source.json";
        result = "src/main/java/phonebook/result.json";
    }

    private void addContact() {
        Contact contact = new Contact(ConsoleIO.stringInput("Введите имя контакта: "));
        addPhoneNumber(contact);
        PhoneBook.addContact(contact);
        ConsoleIO.success("Контакт успешно добавлен");
    }

    private void addPhoneNumber(Contact contact) {
        if (contact == null) {
            if (contactChoices()) {
                int choice = ConsoleIO.intInput("Введите id контакта: ");
                if (PhoneBook.contains(choice)) {
                    contact = PhoneBook.getAllContacts().get(choice);
                }
            }
        }
        if (contact != null) {
            String type = ConsoleIO.stringInput("Введите название телефона (домашний, рабочий, мобильный...): ");
            String number = ConsoleIO.stringInput("Введите номер телефона: ");
            contact.addPhoneNumber(new Number(type, number));
            ConsoleIO.success("Телефон добавлен");
        }
    }

    private void clear() {
        PhoneBook.clear();
        ConsoleIO.success("Телефонная книга успешно очищена");
    }

    private boolean errorIfEmpty() {
        if (PhoneBook.isEmpty()) {
            ConsoleIO.error("Список контактов пуст");
            return false;
        }
        return true;
    }

    protected void option(int choice) {
        switch(choice) {
            case 1 -> read();
            case 2 -> addContact();
            case 3 -> addPhoneNumber(null);
            case 4 -> removePhoneNumber();
            case 5 -> removeContact();
            case 6 -> save();
            case 7 -> printAllContacts();
            case 8 -> printContact();
            case 9 -> clear();
            case 0 -> ConsoleIO.info("Главное меню");

            default -> ConsoleIO.error("Некорректный выбор");
        }
    }

    private void printAllContacts() {
        if (errorIfEmpty()) {
            PhoneBook.getAllContacts().values().forEach(contact -> ConsoleIO.info(contact.toString()));
        }
    }

    private void printContact() {
        if (contactChoices()) {
            int choice = ConsoleIO.intInput("Введите id контакта: ");
            if (PhoneBook.contains(choice)) {
                Contact contact = PhoneBook.getAllContacts().get(choice);
                ConsoleIO.info(contact.toString());
            } else {
                ConsoleIO.error("Некорректный выбор");
            }
        }
    }

    protected void read() {
        Serializer<Contact> serializer = new JsonSerializer<>();
        FileManager<Contact> fileManager = new FileManager<>();
        try {
            Collection<Contact> contacts = fileManager.read(source, serializer, Contact.class);
            contacts.forEach(PhoneBook::addContact);
            ConsoleIO.success(String.format("Контакты успешно загружены из файла %s", source));
        } catch (Exception e) {
            e.printStackTrace();
            ConsoleIO.error(String.format("Произошла ошибка при чтении файла %s", source));
        }
    }

    private void removeContact(){
        if (contactChoices()) {
            int choice = ConsoleIO.intInput("Введите id контакта: ");
            if (PhoneBook.contains(choice)) {
                PhoneBook.deleteContact(choice);
                ConsoleIO.success("Контакт успешно удалён.");
            } else {
                ConsoleIO.error("Некорректный выбор");
            }
        }
    }

    private void removePhoneNumber() {
        if (contactChoices()) {
            int choice = ConsoleIO.intInput("Введите id контакта: ");
            if (PhoneBook.contains(choice)) {
                Contact contact = PhoneBook.getAllContacts().get(choice);
                List<Number> nums = contact.getPhoneNumbers();
                for (int i = 0; i < nums.size(); i++) {
                    ConsoleIO.info((i + 1) + ". " + nums.get(i).getType() + ": " + nums.get(i).getNumber());
                }
                choice = ConsoleIO.intInput("Какой номер телефона вы хотите удалить? Введите номер: ") - 1;
                if (choice < 1 || choice > nums.size()) {
                    ConsoleIO.error("Некорректный выбор");
                    return;
                }
                contact.removePhoneNumber(nums.get(choice));
                ConsoleIO.success("Номер телефона успешно удалён.");
            } else {
                ConsoleIO.error("Некорректный выбор");
            }
        }
    }

    public void save() {
        Collection<Contact> contacts = PhoneBook.getAllContacts().values();
        Serializer<Contact> serializer = new JsonSerializer<>();
        FileManager<Contact> fileManager = new FileManager<>();
        try {
            fileManager.write(result, contacts, serializer);
            ConsoleIO.success(String.format("Результат успешно записан в файл %s", result));
        } catch (Exception e) {
            e.printStackTrace();
            ConsoleIO.error(String.format("Произошла ошибка при записи в файл %s", result));
        }
    }

    public boolean contactChoices() {
        if (errorIfEmpty()) {
            PhoneBook.getAllContacts().values().forEach(contact -> ConsoleIO.info(String.format("%d. %s", contact.getId(), contact.getName())));
            return true;
        }
        return false;
    }
}
