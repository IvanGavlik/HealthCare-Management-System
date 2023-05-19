package org.hcms.admin;

import org.hcms.data.Doctor;
import org.hcms.data.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.function.Function;

public class AdminServiceImpl implements AdminService {

    private Repository repository;

    public AdminServiceImpl(Repository repository) {
        this.repository = repository;
    }
    @Override
    public List<Doctor> getDoctors() {
        Function<ResultSet, Doctor> dbRowToDoctor = rs -> {
            try {
                Doctor d = new Doctor();
                d.setId(rs.getInt("DoctorID"));
                d.setFirstName(rs.getString("First_Name"));
                d.setLastName(rs.getString("Last_Name"));
                d.setGender(rs.getString("Gender"));
                d.setContactNumber(rs.getString("ContactNumber"));
                d.setAge((short) rs.getInt("Age"));
                d.setEntryCharge(rs.getDouble("Entry_Charge"));
                d.setQualification(rs.getString("Qualification"));
                d.setDoctorType(rs.getString("Doctor_Type"));
                d.setEmail(rs.getString("Email_Id"));
                return d;
            } catch (Exception ex) {
                throw new RuntimeException(); // TODO
            }
        };
        return repository.executeQuery("SELECT * FROM Doctors", dbRowToDoctor); // TODO LIMIT " + maxRows;
    }
}


