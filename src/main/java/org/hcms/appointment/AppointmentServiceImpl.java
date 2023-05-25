package org.hcms.appointment;

import org.hcms.data.Appointment;
import org.hcms.data.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class AppointmentServiceImpl implements AppointmentService {
    private Repository repository;

    private Function<ResultSet, Appointment> mapDbRowToAppointment = (rs) -> {
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
    public AppointmentServiceImpl(Repository repository) {
        this.repository = repository;
    }
    @Override
    public List<Appointment> getAppointments() {
        return repository.executeQuery("SELECT * FROM Appointments", mapDbRowToAppointment);
    }

    @Override
    public boolean saveAppointment(Appointment a) {
        int appointmentID = autoAppointmentID();
        return Repository.getInstance()
                .executeUpdate("INSERT INTO Appointments VALUES ('"+appointmentID+"','"+a.getProblem()+"','"
                        +a.getPatientId()+"','"+a.getDoctorName()+"','"+a.getDoctorId()+"','"+a.getDoctorType()+"','"
                        +a.getDoctorQualification()+"','"+a.getDoctorFees()+"','"+a.getPaymentStatus()+"','"+a.getAppointmentStatus()+"')");
    }

    @Override
    public List<Appointment> getAppointmentByPatientId(int patientId) {
        return Repository.getInstance()
                .executeQuery("Select * from  Appointments where PatientID="+patientId, mapDbRowToAppointment);
    }

    @Override
    public List<Appointment> getAppointmentPayedPendingByDoctorId(int doctorId) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("Select * from  Appointments ")
                .append("where DoctorID = ").append(doctorId).append(" ")
                .append("and PaymentStatus = 'Payed'").append(" ")
                .append("and Appointment_Status = 'Pending'").append(";");
        return Repository.getInstance()
                .executeQuery(sqlBuilder.toString(), mapDbRowToAppointment);
    }

    @Override
    public Optional<Appointment> getAppointmentByIdAndPayedPendingByDoctorId(int appId, int doctorId) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("Select * from  Appointments ")
                .append("where AppointmentID = ").append(appId).append(" ")
                .append("and DoctorID = ").append(doctorId).append(" ")
                .append("and PaymentStatus = 'Payed'").append(" ")
                .append("and Appointment_Status = 'Pending'").append(";");
        List<Appointment> appointmentLis = Repository.getInstance()
                .executeQuery(sqlBuilder.toString(), mapDbRowToAppointment);

        if (appointmentLis.size() == 1) {
            return Optional.of(appointmentLis.get(0));
        } else {
            return Optional.empty();
        }
    }

    private int autoAppointmentID() {
        Function<ResultSet, Integer> mapper = (rs) -> {
            try {
                return rs.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };
        List<Integer> appID = Repository.getInstance()
                .executeQuery("Select MAX(AppointmentID) from Appointments", mapper);
        if(appID.isEmpty()) {
            return 1;
        }
        return appID.get(0) + 1;
    }



}

