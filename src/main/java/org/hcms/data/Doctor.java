package org.hcms.data;

import java.util.Objects;

public class Doctor {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String contactNumber;
    private short age;
    private double entryCharge;
    private String qualification;
    private String doctorType;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public double getEntryCharge() {
        return entryCharge;
    }

    public void setEntryCharge(double entryCharge) {
        this.entryCharge = entryCharge;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(String doctorType) {
        this.doctorType = doctorType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id == doctor.id && age == doctor.age && Double.compare(doctor.entryCharge, entryCharge) == 0 && Objects.equals(firstName, doctor.firstName) && Objects.equals(lastName, doctor.lastName) && Objects.equals(email, doctor.email) && Objects.equals(gender, doctor.gender) && Objects.equals(contactNumber, doctor.contactNumber) && Objects.equals(qualification, doctor.qualification) && Objects.equals(doctorType, doctor.doctorType) && Objects.equals(password, doctor.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, gender, contactNumber, age, entryCharge, qualification, doctorType, password);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", age=" + age +
                ", entryCharge=" + entryCharge +
                ", qualification='" + qualification + '\'' +
                ", doctorType='" + doctorType + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

