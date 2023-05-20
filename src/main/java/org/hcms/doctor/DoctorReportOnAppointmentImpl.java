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


}
