package phonebook;

import utilities.ConsoleIO;
import utilities.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.IntStream;

public class PhoneBookView implements View {
    protected Contact getContact() {
        String name = ConsoleIO.stringInput("Введите имя контакта: ");
        return new Contact(name);
    }

    protected int getInt(String message) {
        return ConsoleIO.intInput(message);
    }

    protected Number getNumber() {
        String type = ConsoleIO.stringInput("Введите название телефона (домашний, рабочий, мобильный...): ");
        String number = ConsoleIO.stringInput("Введите номер телефона: ");
        return new Number(type, number);
    }

    protected void printContacts(Collection<Contact> contacts) {
        ConsoleIO.info("Контакты:");
        contacts.forEach(contact -> ConsoleIO.info(String.format("%d. %s", contact.getId(), contact.getName())));
    }

    protected void printNumbers(ArrayList<Number> numbers) {
        ConsoleIO.info("Номера телефонов:");
        IntStream.range(0, numbers.size()).forEach(i -> {
            Number number = numbers.get(i);
            ConsoleIO.info(String.format("%d. %s: %s", i + 1, number.getType(), number.getNumber()));
        });
    }
}
