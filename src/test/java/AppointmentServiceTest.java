// CS 320 Project One — AppointmentServiceTest
// Implements or verifies the corresponding in-memory service requirements.

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppointmentServiceTest {

    private AppointmentService service;
    private Appointment appointment;

    private Date futureDate() {
        return new Date(System.currentTimeMillis() + 60_000);
    }

    @BeforeEach
    void setUp() {
        service = new AppointmentService();
        appointment =
                new Appointment("APT001", futureDate(), "Annual checkup");
    }

    @Test
    void testAddAppointment() {
        service.addAppointment(appointment);

        assertSame(
                appointment,
                service.getAppointment("APT001"));
    }

    @Test
    void testAddMultipleAppointmentsWithUniqueIds() {
        Appointment secondAppointment =
                new Appointment(
                        "APT002",
                        futureDate(),
                        "Dental appointment");

        service.addAppointment(appointment);
        service.addAppointment(secondAppointment);

        assertSame(
                appointment,
                service.getAppointment("APT001"));
        assertSame(
                secondAppointment,
                service.getAppointment("APT002"));
    }

    @Test
    void testAddDuplicateAppointmentId() {
        service.addAppointment(appointment);

        Appointment duplicate =
                new Appointment(
                        "APT001",
                        futureDate(),
                        "Different appointment");

        assertThrows(IllegalArgumentException.class,
                () -> service.addAppointment(duplicate));

        assertSame(
                appointment,
                service.getAppointment("APT001"));
    }

    @Test
    void testAddNullAppointment() {
        assertThrows(IllegalArgumentException.class,
                () -> service.addAppointment(null));
    }

    @Test
    void testDeleteAppointment() {
        service.addAppointment(appointment);

        service.deleteAppointment("APT001");

        assertThrows(IllegalArgumentException.class,
                () -> service.getAppointment("APT001"));
    }

    @Test
    void testDeleteAppointmentThatDoesNotExist() {
        assertThrows(IllegalArgumentException.class,
                () -> service.deleteAppointment("APT999"));
    }

    @Test
    void testDeleteAppointmentWithNullId() {
        assertThrows(IllegalArgumentException.class,
                () -> service.deleteAppointment(null));
    }
}
