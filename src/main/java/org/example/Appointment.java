// CS 320 Project One — Appointment
// Implements or verifies the corresponding in-memory service requirements.

import java.util.Date;

public class Appointment {

    private final String appointmentId;
    private final Date appointmentDate;
    private final String description;

    public Appointment(String appointmentId, Date appointmentDate, String description) {
        validateAppointmentId(appointmentId);
        validateAppointmentDate(appointmentDate);
        validateDescription(description);

        this.appointmentId = appointmentId;
        this.appointmentDate = new Date(appointmentDate.getTime());
        this.description = description;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Date getAppointmentDate() {
        return new Date(appointmentDate.getTime());
    }

    public String getDescription() {
        return description;
    }

    private static void validateAppointmentId(String appointmentId) {
        if (appointmentId == null || appointmentId.length() > 10) {
            throw new IllegalArgumentException(
                    "Appointment ID must not be null or longer than 10 characters.");
        }
    }

    private static void validateAppointmentDate(Date appointmentDate) {
        if (appointmentDate == null || appointmentDate.before(new Date())) {
            throw new IllegalArgumentException(
                    "Appointment date must not be null or in the past.");
        }
    }

    private static void validateDescription(String description) {
        if (description == null || description.length() > 50) {
            throw new IllegalArgumentException(
                    "Description must not be null or longer than 50 characters.");
        }
    }
}
