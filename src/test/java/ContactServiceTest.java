// CS 320 Project One — ContactServiceTest
// Implements or verifies the corresponding in-memory service requirements.

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * ContactServiceTest.java
 * CS 320 - Project One
 *
 * Verifies the three Contact Service requirements:
 *   1. Add contacts with a unique ID
 *   2. Delete contacts per contact ID
 *   3. Update contact fields (firstName, lastName, phone, address) per contact ID
 */
class ContactServiceTest {

    private ContactService service;
    private Contact contact;

    @BeforeEach
    void setUp() {
        // A new service and contact for every test keeps the tests independent.
        service = new ContactService();
        contact = new Contact(
                "12345", "John", "Smith", "1234567890", "123 Main Street");
    }

    // -------------------------------- Requirement 1: add with a unique ID

    @Test
    void testAddContact() {
        service.addContact(contact);
        assertSame(contact, service.getContact("12345"));
    }

    @Test
    void testAddMultipleContactsWithUniqueIds() {
        Contact second = new Contact(
                "67890", "James", "Brown", "0987654321", "456 Oak Street");

        service.addContact(contact);
        service.addContact(second);

        assertSame(contact, service.getContact("12345"));
        assertSame(second, service.getContact("67890"));
    }

    @Test
    void testAddDuplicateContactId() {
        service.addContact(contact);

        Contact duplicate = new Contact(
                "12345", "James", "Brown", "0987654321", "456 Oak Street");

        assertThrows(IllegalArgumentException.class,
                () -> service.addContact(duplicate));

        // The original contact must not have been replaced.
        assertSame(contact, service.getContact("12345"));
    }

    @Test
    void testAddNullContact() {
        assertThrows(IllegalArgumentException.class,
                () -> service.addContact(null));
    }

    // ----------------------------- Requirement 2: delete per contact ID

    @Test
    void testDeleteContact() {
        service.addContact(contact);
        service.deleteContact("12345");

        assertThrows(IllegalArgumentException.class,
                () -> service.getContact("12345"));
    }

    @Test
    void testDeleteOnlyRemovesRequestedContact() {
        Contact second = new Contact(
                "67890", "James", "Brown", "0987654321", "456 Oak Street");
        service.addContact(contact);
        service.addContact(second);

        service.deleteContact("12345");

        assertThrows(IllegalArgumentException.class,
                () -> service.getContact("12345"));
        assertSame(second, service.getContact("67890"));
    }

    @Test
    void testDeleteContactThatDoesNotExist() {
        assertThrows(IllegalArgumentException.class,
                () -> service.deleteContact("99999"));
    }

    @Test
    void testDeleteNullContactId() {
        assertThrows(IllegalArgumentException.class,
                () -> service.deleteContact(null));
    }

    @Test
    void testContactIdCanBeReusedAfterDelete() {
        service.addContact(contact);
        service.deleteContact("12345");

        Contact replacement = new Contact(
                "12345", "James", "Brown", "0987654321", "456 Oak Street");

        assertDoesNotThrow(() -> service.addContact(replacement));
        assertSame(replacement, service.getContact("12345"));
    }

    // ------------------- Requirement 3: update fields per contact ID

    @Test
    void testUpdateFirstName() {
        service.addContact(contact);
        service.updateFirstName("12345", "James");
        assertEquals("James", service.getContact("12345").getFirstName());
    }

    @Test
    void testUpdateLastName() {
        service.addContact(contact);
        service.updateLastName("12345", "Brown");
        assertEquals("Brown", service.getContact("12345").getLastName());
    }

    @Test
    void testUpdatePhone() {
        service.addContact(contact);
        service.updatePhone("12345", "0987654321");
        assertEquals("0987654321", service.getContact("12345").getPhone());
    }

    @Test
    void testUpdateAddress() {
        service.addContact(contact);
        service.updateAddress("12345", "456 Oak Street");
        assertEquals("456 Oak Street", service.getContact("12345").getAddress());
    }

    @Test
    void testUpdateDoesNotChangeOtherFields() {
        service.addContact(contact);
        service.updateFirstName("12345", "James");

        Contact updated = service.getContact("12345");
        assertEquals("Smith", updated.getLastName());
        assertEquals("1234567890", updated.getPhone());
        assertEquals("123 Main Street", updated.getAddress());
    }

    @Test
    void testUpdateOnlyAffectsRequestedContact() {
        Contact second = new Contact(
                "67890", "James", "Brown", "0987654321", "456 Oak Street");
        service.addContact(contact);
        service.addContact(second);

        service.updateFirstName("12345", "Johnny");

        assertEquals("Johnny", service.getContact("12345").getFirstName());
        assertEquals("James", service.getContact("67890").getFirstName());
    }

    @Test
    void testContactIdUnchangedAfterUpdates() {
        service.addContact(contact);

        service.updateFirstName("12345", "James");
        service.updateLastName("12345", "Brown");
        service.updatePhone("12345", "0987654321");
        service.updateAddress("12345", "456 Oak Street");

        assertEquals("12345", service.getContact("12345").getContactId());
    }

    @Test
    void testUpdateContactThatDoesNotExist() {
        assertThrows(IllegalArgumentException.class,
                () -> service.updateFirstName("99999", "James"));
        assertThrows(IllegalArgumentException.class,
                () -> service.updateLastName("99999", "Brown"));
        assertThrows(IllegalArgumentException.class,
                () -> service.updatePhone("99999", "0987654321"));
        assertThrows(IllegalArgumentException.class,
                () -> service.updateAddress("99999", "456 Oak Street"));
    }

    @Test
    void testUpdateWithInvalidFirstName() {
        service.addContact(contact);
        assertThrows(IllegalArgumentException.class,
                () -> service.updateFirstName("12345", "ABCDEFGHIJK"));
    }

    @Test
    void testUpdateWithInvalidLastName() {
        service.addContact(contact);
        assertThrows(IllegalArgumentException.class,
                () -> service.updateLastName("12345", "ABCDEFGHIJK"));
    }

    @Test
    void testUpdateWithInvalidPhone() {
        service.addContact(contact);
        assertThrows(IllegalArgumentException.class,
                () -> service.updatePhone("12345", "12345"));
    }

    @Test
    void testUpdateWithInvalidAddress() {
        service.addContact(contact);
        assertThrows(IllegalArgumentException.class,
                () -> service.updateAddress(
                        "12345", "1234567890123456789012345678901"));
    }

    @Test
    void testFailedUpdatesKeepOriginalValues() {
        service.addContact(contact);

        assertThrows(IllegalArgumentException.class,
                () -> service.updateFirstName("12345", "ABCDEFGHIJK"));
        assertThrows(IllegalArgumentException.class,
                () -> service.updateLastName("12345", null));
        assertThrows(IllegalArgumentException.class,
                () -> service.updatePhone("12345", "12345"));
        assertThrows(IllegalArgumentException.class,
                () -> service.updateAddress(
                        "12345", "1234567890123456789012345678901"));

        Contact stored = service.getContact("12345");
        assertEquals("John", stored.getFirstName());
        assertEquals("Smith", stored.getLastName());
        assertEquals("1234567890", stored.getPhone());
        assertEquals("123 Main Street", stored.getAddress());
    }
}
