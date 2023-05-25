package org.hcms.appointment;

import org.hcms.data.Appointment;
import org.hcms.data.Repository;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    AppointmentService DEFAULT_INSTANCE = new AppointmentServiceImpl(Repository.getInstance());
    List<Appointment> getAppointments();
    boolean saveAppointment(Appointment appointment);

    List<Appointment> getAppointmentByPatientId(int patientId);

    List<Appointment> getAppointmentPayedPendingByDoctorId(int doctorId);

    Optional<Appointment> getAppointmentByIdAndPayedPendingByDoctorId(int appId, int doctorId);

}
