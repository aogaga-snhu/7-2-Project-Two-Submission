/**
 * Contact.java
 * CS 320 - Project One
 *
 * Represents a single contact. All fields are validated, so an invalid
 * Contact object can never be created or left in an invalid state.
 *
 * Requirements enforced:
 *  - contactId : required, not null, no longer than 10 characters, NOT updatable
 *  - firstName : required, not null, no longer than 10 characters
 *  - lastName  : required, not null, no longer than 10 characters
 *  - phone     : required, not null, exactly 10 digits
 *  - address   : required, not null, no longer than 30 characters
 */
public class Contact {

    // The ID is final: it is set once in the constructor and has no setter,
    // which is how the "not updatable" requirement is met.
    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    /**
     * Creates a contact. Throws IllegalArgumentException if any value
     * breaks the rules listed above.
     */
    public Contact(String contactId, String firstName, String lastName,
                   String phone, String address) {

        validateContactId(contactId);
        validateFirstName(firstName);
        validateLastName(lastName);
        validatePhone(phone);
        validateAddress(address);

        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    // ---------------------------------------------------------------- Getters

    public String getContactId() {
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    // ---------------------------------------------------------------- Setters
    // There is intentionally NO setContactId(): the contact ID cannot be updated.
    // Each setter validates first, so a failed update leaves the old value intact.

    public void setFirstName(String firstName) {
        validateFirstName(firstName);
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        validateLastName(lastName);
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        validatePhone(phone);
        this.phone = phone;
    }

    public void setAddress(String address) {
        validateAddress(address);
        this.address = address;
    }

    // ------------------------------------------------------------- Validation

    private static void validateContactId(String contactId) {
        if (contactId == null || contactId.length() > 10) {
            throw new IllegalArgumentException(
                    "Contact ID must not be null or longer than 10 characters.");
        }
    }

    private static void validateFirstName(String firstName) {
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException(
                    "First name must not be null or longer than 10 characters.");
        }
    }

    private static void validateLastName(String lastName) {
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException(
                    "Last name must not be null or longer than 10 characters.");
        }
    }

    private static void validatePhone(String phone) {
        // Exactly 10 characters and every one of them a digit.
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException(
                    "Phone number must contain exactly 10 digits.");
        }
    }

    private static void validateAddress(String address) {
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException(
                    "Address must not be null or longer than 30 characters.");
        }
    }
}
