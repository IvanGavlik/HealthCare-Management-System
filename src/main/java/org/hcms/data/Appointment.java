package org.hcms.data;

import java.util.Objects;

public final class Appointment {
    private int id;
    private String problem;
    private int patientId;
    private int doctorId;
    private String doctorName;
    private String doctorType;
    private String doctorQualification;
    private double doctorFees;
    private String paymentStatus;
    private String appointmentStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(String doctorType) {
        this.doctorType = doctorType;
    }

    public String getDoctorQualification() {
        return doctorQualification;
    }

    public void setDoctorQualification(String doctorQualification) {
        this.doctorQualification = doctorQualification;
    }

    public double getDoctorFees() {
        return doctorFees;
    }

    public void setDoctorFees(double doctorFees) {
        this.doctorFees = doctorFees;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return id == that.id && patientId == that.patientId && doctorId == that.doctorId && Double.compare(that.doctorFees, doctorFees) == 0 && Objects.equals(problem, that.problem) && Objects.equals(doctorName, that.doctorName) && Objects.equals(doctorType, that.doctorType) && Objects.equals(doctorQualification, that.doctorQualification) && Objects.equals(paymentStatus, that.paymentStatus) && Objects.equals(appointmentStatus, that.appointmentStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, problem, patientId, doctorId, doctorName, doctorType, doctorQualification, doctorFees, paymentStatus, appointmentStatus);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", problem='" + problem + '\'' +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", doctorName='" + doctorName + '\'' +
                ", doctorType='" + doctorType + '\'' +
                ", doctorQualification='" + doctorQualification + '\'' +
                ", doctorFees=" + doctorFees +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", appointmentStatus='" + appointmentStatus + '\'' +
                '}';
    }
}


