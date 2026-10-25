// CS 320 Project One — AppointmentService
// Implements or verifies the corresponding in-memory service requirements.

import java.util.HashMap;
import java.util.Map;

public class AppointmentService {

    private final Map<String, Appointment> appointments;

    public AppointmentService() {
        appointments = new HashMap<>();
    }

    public void addAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment cannot be null.");
        }

        String appointmentId = appointment.getAppointmentId();

        if (appointments.containsKey(appointmentId)) {
            throw new IllegalArgumentException(
                    "An appointment with this ID already exists.");
        }

        appointments.put(appointmentId, appointment);
    }

    public void deleteAppointment(String appointmentId) {
        if (appointmentId == null || !appointments.containsKey(appointmentId)) {
            throw new IllegalArgumentException("Appointment ID not found.");
        }

        appointments.remove(appointmentId);
    }

    public Appointment getAppointment(String appointmentId) {
        Appointment appointment = appointments.get(appointmentId);

        if (appointment == null) {
            throw new IllegalArgumentException("Appointment ID not found.");
        }

        return appointment;
    }
}
