package phonebook;

import java.util.*;

public abstract class PhoneBook {
    private static Map<Integer, Contact> contacts = new TreeMap<>();
    protected static void addContact(Contact contact) {
        contacts.put(contact.getId(), contact);
    }

    protected static void clear() {
        contacts.clear();
    }

    protected static boolean contains(int id) {
        return contacts.containsKey(id);
    }

    protected static void deleteContact(int id) {
        contacts.remove(id);
    }

    protected static Map<Integer, Contact> getAllContacts() {
        return contacts;
    }

    protected static Contact getContactById(int id) {
        return contacts.get(id);
    }

    protected static int getMaxId() {
        return Collections.max(contacts.keySet());
    }

    protected static boolean isEmpty() {
        return contacts.isEmpty();
    }

    protected static void sortByAlphabet() {
        List<Contact> sortedContacts = new ArrayList<>(contacts.values());
        sortedContacts.sort(Comparator.comparing(Contact::getName));
        contacts.replaceAll((id, contact) -> sortedContacts.remove(0));
    }
}