// CS 320 Project One — ContactTest
// Implements or verifies the corresponding in-memory service requirements.

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * ContactTest.java
 * CS 320 - Project One
 *
 * Verifies the five Contact class requirements:
 *   1. contactId - required, not null, max 10 characters, NOT updatable
 *   2. firstName - required, not null, max 10 characters
 *   3. lastName  - required, not null, max 10 characters
 *   4. phone     - required, not null, exactly 10 digits
 *   5. address   - required, not null, max 30 characters
 */
class ContactTest {

    private static final String THIRTY_CHARS = "123456789012345678901234567890";
    private static final String THIRTY_ONE_CHARS = "1234567890123456789012345678901";

    // ------------------------------------------------------------- Creation

    @Test
    void testContactCreation() {
        Contact contact = new Contact(
                "12345", "John", "Smith", "1234567890", "123 Main Street");

        assertEquals("12345", contact.getContactId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Smith", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 Main Street", contact.getAddress());
    }

    // ------------------------------------------- Requirement 1: contactId

    @Test
    void testContactIdExactlyTenCharacters() {
        Contact contact = new Contact(
                "1234567890", "John", "Smith", "1234567890", "123 Main Street");
        assertEquals("1234567890", contact.getContactId());
    }

    @Test
    void testContactIdTooLong() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("12345678901", "John", "Smith",
                        "1234567890", "123 Main Street"));
    }

    @Test
    void testContactIdNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact(null, "John", "Smith",
                        "1234567890", "123 Main Street"));
    }

    @Test
    void testContactIdCannotBeUpdated() {
        Contact contact = createValidContact();

        // 1) The class must not offer any method that changes the ID.
        for (java.lang.reflect.Method method : Contact.class.getMethods()) {
            assertNotEquals("setcontactid", method.getName().toLowerCase(),
                    "Contact must not provide a way to update the contact ID");
        }

        // 2) Updating every other field must leave the ID unchanged.
        contact.setFirstName("James");
        contact.setLastName("Brown");
        contact.setPhone("0987654321");
        contact.setAddress("456 Oak Street");
        assertEquals("12345", contact.getContactId());
    }

    // ------------------------------------------ Requirement 2: firstName

    @Test
    void testFirstNameExactlyTenCharacters() {
        Contact contact = new Contact(
                "12345", "ABCDEFGHIJ", "Smith", "1234567890", "123 Main Street");
        assertEquals("ABCDEFGHIJ", contact.getFirstName());
    }

    @Test
    void testFirstNameTooLong() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("12345", "ABCDEFGHIJK", "Smith",
                        "1234567890", "123 Main Street"));
    }

    @Test
    void testFirstNameNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("12345", null, "Smith",
                        "1234567890", "123 Main Street"));
    }

    // ------------------------------------------- Requirement 3: lastName

    @Test
    void testLastNameExactlyTenCharacters() {
        Contact contact = new Contact(
                "12345", "John", "ABCDEFGHIJ", "1234567890", "123 Main Street");
        assertEquals("ABCDEFGHIJ", contact.getLastName());
    }

    @Test
    void testLastNameTooLong() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("12345", "John", "ABCDEFGHIJK",
                        "1234567890", "123 Main Street"));
    }

    @Test
    void testLastNameNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("12345", "John", null,
                        "1234567890", "123 Main Street"));
    }

    // ---------------------------------------------- Requirement 4: phone

    @Test
    void testPhoneExactlyTenDigits() {
        Contact contact = createValidContact();
        assertEquals("1234567890", contact.getPhone());
    }

    @Test
    void testPhoneTooShort() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("12345", "John", "Smith",
                        "123456789", "123 Main Street"));
    }

    @Test
    void testPhoneTooLong() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("12345", "John", "Smith",
                        "12345678901", "123 Main Street"));
    }

    @Test
    void testPhoneContainsLetters() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("12345", "John", "Smith",
                        "12345ABCDE", "123 Main Street"));
    }

    @Test
    void testPhoneContainsSymbols() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("12345", "John", "Smith",
                        "555-123-45", "123 Main Street"));
    }

    @Test
    void testPhoneNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("12345", "John", "Smith",
                        null, "123 Main Street"));
    }

    // -------------------------------------------- Requirement 5: address

    @Test
    void testAddressExactlyThirtyCharacters() {
        Contact contact = new Contact(
                "12345", "John", "Smith", "1234567890", THIRTY_CHARS);
        assertEquals(THIRTY_CHARS, contact.getAddress());
    }

    @Test
    void testAddressTooLong() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("12345", "John", "Smith", "1234567890",
                        THIRTY_ONE_CHARS));
    }

    @Test
    void testAddressNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Contact("12345", "John", "Smith", "1234567890", null));
    }

    // ------------------------------------------------- Updating via setters

    @Test
    void testUpdateContactFields() {
        Contact contact = createValidContact();

        contact.setFirstName("James");
        contact.setLastName("Brown");
        contact.setPhone("0987654321");
        contact.setAddress("456 Oak Street");

        assertEquals("James", contact.getFirstName());
        assertEquals("Brown", contact.getLastName());
        assertEquals("0987654321", contact.getPhone());
        assertEquals("456 Oak Street", contact.getAddress());
    }

    @Test
    void testInvalidFirstNameUpdate() {
        Contact contact = createValidContact();
        assertThrows(IllegalArgumentException.class,
                () -> contact.setFirstName("ABCDEFGHIJK"));
        assertThrows(IllegalArgumentException.class,
                () -> contact.setFirstName(null));
        assertEquals("John", contact.getFirstName());
    }

    @Test
    void testInvalidLastNameUpdate() {
        Contact contact = createValidContact();
        assertThrows(IllegalArgumentException.class,
                () -> contact.setLastName("ABCDEFGHIJK"));
        assertThrows(IllegalArgumentException.class,
                () -> contact.setLastName(null));
        assertEquals("Smith", contact.getLastName());
    }

    @Test
    void testInvalidPhoneUpdate() {
        Contact contact = createValidContact();
        assertThrows(IllegalArgumentException.class,
                () -> contact.setPhone("12345"));
        assertThrows(IllegalArgumentException.class,
                () -> contact.setPhone("12345ABCDE"));
        assertThrows(IllegalArgumentException.class,
                () -> contact.setPhone(null));
        assertEquals("1234567890", contact.getPhone());
    }

    @Test
    void testInvalidAddressUpdate() {
        Contact contact = createValidContact();
        assertThrows(IllegalArgumentException.class,
                () -> contact.setAddress(THIRTY_ONE_CHARS));
        assertThrows(IllegalArgumentException.class,
                () -> contact.setAddress(null));
        assertEquals("123 Main Street", contact.getAddress());
    }

    // ---------------------------------------------------------------- Helper

    private Contact createValidContact() {
        return new Contact(
                "12345", "John", "Smith", "1234567890", "123 Main Street");
    }
}
