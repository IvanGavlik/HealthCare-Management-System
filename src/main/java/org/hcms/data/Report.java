package org.hcms.data;

import java.util.Objects;

public final class Report {
    private int id;
    private int appointmentID;
    private int patientID;
    private int doctorID;
    private String medicinePrescribed;
    private String doctorComment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public String getMedicinePrescribed() {
        return medicinePrescribed;
    }

    public void setMedicinePrescribed(String medicinePrescribed) {
        this.medicinePrescribed = medicinePrescribed;
    }

    public String getDoctorComment() {
        return doctorComment;
    }

    public void setDoctorComment(String doctorComment) {
        this.doctorComment = doctorComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return id == report.id && appointmentID == report.appointmentID && patientID == report.patientID && doctorID == report.doctorID && Objects.equals(medicinePrescribed, report.medicinePrescribed) && Objects.equals(doctorComment, report.doctorComment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, appointmentID, patientID, doctorID, medicinePrescribed, doctorComment);
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", appointmentID=" + appointmentID +
                ", patientID=" + patientID +
                ", doctorID=" + doctorID +
                ", medicinePrescribed='" + medicinePrescribed + '\'' +
                ", doctorComment='" + doctorComment + '\'' +
                '}';
    }
}
