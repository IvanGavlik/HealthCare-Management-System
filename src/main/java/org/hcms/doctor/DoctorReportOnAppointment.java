package org.hcms.doctor;

import org.hcms.data.Report;
import org.hcms.data.Repository;

import java.util.List;

public interface DoctorReportOnAppointment {
    DoctorReportOnAppointment DEFAULT_INSTANCE = new DoctorReportOnAppointmentImpl(Repository.getInstance());
    List<Report> getReport();

    List<Report> getReportByPatientId(int patientId);

    boolean save(Report report);
}
