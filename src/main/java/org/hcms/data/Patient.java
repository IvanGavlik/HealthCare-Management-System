package org.hcms.data;

import java.util.Objects;

public class Patient {
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private String contactNumber;
    private short age;
    private String email;
    private String bloodGroup;
    private String address;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id && age == patient.age && Objects.equals(firstName, patient.firstName) && Objects.equals(lastName, patient.lastName) && Objects.equals(gender, patient.gender) && Objects.equals(contactNumber, patient.contactNumber) && Objects.equals(email, patient.email) && Objects.equals(bloodGroup, patient.bloodGroup) && Objects.equals(address, patient.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, gender, contactNumber, age, email, bloodGroup, address);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
