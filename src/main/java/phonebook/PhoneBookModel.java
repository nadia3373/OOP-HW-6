package phonebook;

import java.util.*;

public class PhoneBookModel {
    private Map<Integer, Contact> contacts = new TreeMap<>();
    protected void addContact(Contact contact) {
        contacts.put(contact.getId(), contact);
        Contact.counter++;
    }
    protected void addNumber(Contact contact, Number number) {contact.addPhoneNumber(number);}

    protected void clear() {
        contacts.clear();
    }

    protected void deleteContact(int id) {
        contacts.remove(id);
    }

    protected Map<Integer, Contact> getAllContacts() {
        return contacts;
    }

    protected Contact getContactById(int id) {
        return contacts.getOrDefault(id, null);
    }

    protected boolean isEmpty() {
        return contacts.isEmpty();
    }

    protected void removePhoneNumber(Contact contact, Number number) {
        contact.removePhoneNumber(number);
    }
}
