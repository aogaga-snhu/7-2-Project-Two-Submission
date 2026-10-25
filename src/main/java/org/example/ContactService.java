// CS 320 Project One — ContactService
// Implements or verifies the corresponding in-memory service requirements.

import java.util.HashMap;
import java.util.Map;

/**
 * ContactService.java
 * CS 320 - Project One
 *
 * Keeps contacts in memory (no database) and supports:
 *  - adding a contact with a unique ID
 *  - deleting a contact by contact ID
 *  - updating firstName, lastName, phone (number) and address by contact ID
 */
public class ContactService {

    // Key = contact ID, value = the Contact. A map makes the uniqueness
    // check and every lookup by ID quick and simple.
    private final Map<String, Contact> contacts;

    public ContactService() {
        contacts = new HashMap<>();
    }

    /**
     * Adds a contact. The contact ID must be unique.
     *
     * @throws IllegalArgumentException if the contact is null or the ID already exists
     */
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null.");
        }

        if (contacts.containsKey(contact.getContactId())) {
            throw new IllegalArgumentException(
                    "A contact with this ID already exists.");
        }

        contacts.put(contact.getContactId(), contact);
    }

    /**
     * Deletes the contact with the given ID.
     *
     * @throws IllegalArgumentException if no contact has that ID
     */
    public void deleteContact(String contactId) {
        if (!contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Contact ID not found.");
        }

        contacts.remove(contactId);
    }

    /** Updates the first name of the contact with the given ID. */
    public void updateFirstName(String contactId, String firstName) {
        getContact(contactId).setFirstName(firstName);
    }

    /** Updates the last name of the contact with the given ID. */
    public void updateLastName(String contactId, String lastName) {
        getContact(contactId).setLastName(lastName);
    }

    /** Updates the phone number of the contact with the given ID. */
    public void updatePhone(String contactId, String phone) {
        getContact(contactId).setPhone(phone);
    }

    /** Updates the address of the contact with the given ID. */
    public void updateAddress(String contactId, String address) {
        getContact(contactId).setAddress(address);
    }

    /**
     * Returns the contact with the given ID.
     *
     * @throws IllegalArgumentException if no contact has that ID
     */
    public Contact getContact(String contactId) {
        Contact contact = contacts.get(contactId);

        if (contact == null) {
            throw new IllegalArgumentException("Contact ID not found.");
        }

        return contact;
    }
}
