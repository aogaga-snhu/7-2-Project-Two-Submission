// CS 320 Project One — AppointmentTest
// Implements or verifies the corresponding in-memory service requirements.

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import org.junit.jupiter.api.Test;

class AppointmentTest {

    private Date futureDate() {
        return new Date(System.currentTimeMillis() + 60_000);
    }

    @Test
    void testAppointmentCreation() {
        Date date = futureDate();
        Appointment appointment =
                new Appointment("APT001", date, "Annual checkup");

        assertEquals("APT001", appointment.getAppointmentId());
        assertEquals(date, appointment.getAppointmentDate());
        assertEquals("Annual checkup", appointment.getDescription());
    }

    @Test
    void testAppointmentIdExactlyTenCharacters() {
        Appointment appointment =
                new Appointment("1234567890", futureDate(), "Valid description");

        assertEquals("1234567890", appointment.getAppointmentId());
    }

    @Test
    void testAppointmentIdTooLong() {
        assertThrows(IllegalArgumentException.class, () ->
                new Appointment(
                        "12345678901",
                        futureDate(),
                        "Valid description"));
    }

    @Test
    void testAppointmentIdNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Appointment(
                        null,
                        futureDate(),
                        "Valid description"));
    }

    @Test
    void testFutureAppointmentDateAccepted() {
        Date date = futureDate();
        Appointment appointment =
                new Appointment("APT001", date, "Valid description");

        assertEquals(date, appointment.getAppointmentDate());
    }

    @Test
    void testAppointmentDateInPast() {
        Date pastDate = new Date(System.currentTimeMillis() - 60_000);

        assertThrows(IllegalArgumentException.class, () ->
                new Appointment(
                        "APT001",
                        pastDate,
                        "Valid description"));
    }

    @Test
    void testAppointmentDateNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Appointment(
                        "APT001",
                        null,
                        "Valid description"));
    }

    @Test
    void testDescriptionExactlyFiftyCharacters() {
        String description =
                "12345678901234567890123456789012345678901234567890";

        Appointment appointment =
                new Appointment("APT001", futureDate(), description);

        assertEquals(description, appointment.getDescription());
    }

    @Test
    void testDescriptionTooLong() {
        String description =
                "123456789012345678901234567890123456789012345678901";

        assertThrows(IllegalArgumentException.class, () ->
                new Appointment(
                        "APT001",
                        futureDate(),
                        description));
    }

    @Test
    void testDescriptionNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Appointment(
                        "APT001",
                        futureDate(),
                        null));
    }

    @Test
    void testAppointmentIdCannotBeUpdated() {
        Appointment appointment =
                new Appointment("APT001", futureDate(), "Valid description");

        assertThrows(NoSuchMethodException.class,
                () -> Appointment.class.getDeclaredMethod(
                        "setAppointmentId", String.class));

        assertEquals("APT001", appointment.getAppointmentId());
    }

    @Test
    void testDateIsDefensivelyCopied() {
        Date originalDate = futureDate();
        Appointment appointment =
                new Appointment("APT001", originalDate, "Valid description");

        originalDate.setTime(System.currentTimeMillis() - 60_000);

        assertFalse(appointment.getAppointmentDate().before(new Date()));
    }
}
