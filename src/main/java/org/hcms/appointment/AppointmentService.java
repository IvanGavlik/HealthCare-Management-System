package org.hcms.appointment;

import org.hcms.data.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAppointments();
    boolean saveAppointment(Appointment appointment);

    List<Appointment> getAppointmentByPatientId(int patientId);

    List<Appointment> getAppointmentPayedPendingByDoctorId(int doctorId);
}
