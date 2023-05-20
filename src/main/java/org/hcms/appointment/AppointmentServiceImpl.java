package org.hcms.appointment;

import org.hcms.data.Appointment;
import org.hcms.data.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.function.Function;

public class AppointmentServiceImpl implements AppointmentService {

    private Repository repository;

    public AppointmentServiceImpl(Repository repository) {
        this.repository = repository;
    }


    @Override
    public List<Appointment> getAppointments() {

        Function<ResultSet, Appointment> mapToAppointment = (rs) -> {
            try {
                Appointment a = new Appointment();
                a.setId(rs.getInt("AppointmentID"));
                a.setProblem(rs.getString("Problem"));
                a.setPatientId(rs.getInt("PatientId"));
                a.setDoctorName(rs.getString("DoctorName"));
                a.setDoctorId(rs.getInt("DoctorID"));
                a.setDoctorType(rs.getString("DoctorType"));
                a.setDoctorQualification(rs.getString("Qualification"));
                a.setDoctorFees(rs.getDouble("DoctorFees"));
                a.setPaymentStatus(rs.getString("PaymentStatus"));
                a.setAppointmentStatus(rs.getString("Appointment_Status"));
                return a;
            } catch (Exception ex) {
                throw new RuntimeException();
            }
        };

        return repository.executeQuery("SELECT * FROM Appointments", mapToAppointment);
    }
}

