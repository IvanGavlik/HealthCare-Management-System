package org.hcms.doctor;

import org.hcms.data.Report;

import java.util.List;

public interface DoctorReportOnAppointment {

    List<Report> getReport();

    List<Report> getReportByPatientId(int patientId);
}
