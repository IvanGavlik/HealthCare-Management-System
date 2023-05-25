package org.hcms.doctor;

import org.hcms.data.Report;
import org.hcms.data.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.function.Function;

public class DoctorReportOnAppointmentImpl implements DoctorReportOnAppointment {
    private Repository repository;

    private Function<ResultSet, Report> dbRowToReport = (rs) -> {
        try {
            Report r = new Report();
            r.setId(rs.getInt("ReportID"));
            r.setAppointmentID(rs.getInt("appointmentID"));
            r.setPatientID(rs.getInt("patientID"));
            r.setDoctorID(rs.getInt("DoctorID"));
            r.setMedicinePrescribed(rs.getString("MedicinePrescribed"));
            r.setDoctorComment(rs.getString("DoctorComment"));
            return r;
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    };

    public DoctorReportOnAppointmentImpl(Repository repository) {
        this.repository = repository;
    }
    @Override
    public List<Report> getReport() {
        return repository.executeQuery("SELECT * FROM Reports", dbRowToReport);
    }

    @Override
    public List<Report> getReportByPatientId(int patientId) {
        return repository.executeQuery("select * from Reports where PatientID =" + patientId, dbRowToReport);
    }

    @Override
    public boolean save(Report report) {
        report.setId(autoReportID());

        boolean done = Repository.getInstance()
                .executeUpdate("INSERT INTO Reports VALUES ('"+report.getId()+"','"+report.getAppointmentID()+"','"
                        +report.getPatientID()+"','"+report.getDoctorID()+"','"+report.getMedicinePrescribed()+"','"+
                        report.getDoctorComment()+"')");
        if (!done) {
            return false;
        }

        done = Repository.getInstance()
                .executeUpdate("UPDATE Appointments SET Appointment_Status='Completed' WHERE AppointmentID="
                        +report.getAppointmentID());
        if (!done) {
            // TODO ROOLBACK INSERT VALUES
            return false;
        }

        return done;
    }

    private int autoReportID() {
        Function<ResultSet, Integer> mapper = (rs) -> {
            try {
                return rs.getInt(1);
            } catch (Exception ex) {
                throw new RuntimeException();
            }
        };
        List<Integer> result =  Repository.getInstance()
                .executeQuery("Select MAX(ReportId) as NextUserID from Reports", mapper);

        if (result.isEmpty()) {
            return 1;
        }
        return result.get(0) + 1;
    }

}
