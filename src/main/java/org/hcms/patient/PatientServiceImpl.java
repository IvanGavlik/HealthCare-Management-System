package org.hcms.patient;

import org.hcms.data.Patient;
import org.hcms.data.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

public class PatientServiceImpl implements PatientService {

    private Repository repository;

    public PatientServiceImpl(Repository repository) {
        this.repository = repository;
    }
    @Override
    public List<Patient> getPatients() {
        Function<ResultSet, Patient> dbRowToPatient = rs -> {
            try {
                Patient p = new Patient();
                p.setId(rs.getInt("PatientID"));
                p.setFirstName(rs.getString("First_Name"));
                p.setLastName(rs.getString("Last_Name"));
                p.setGender(rs.getString("Gender"));
                p.setContactNumber(rs.getString("ContactNumber"));
                p.setAge((short) rs.getInt("Age"));
                p.setAddress(rs.getString("Address"));
                p.setBloodGroup(rs.getString("BloodGroup"));
                p.setEmail(rs.getString("EmailID"));
                return p;
            } catch (Exception ex) {
                throw new RuntimeException(); // TODO
            }
        };
        return repository.executeQuery("SELECT * FROM Patients", dbRowToPatient); // TODO LIMIT " + maxRows;
    }

    @Override
    public Patient getPatient(int id) {
        Function<ResultSet, Patient> mapper = resultSet -> {
            try {
               Patient patient = new Patient();
               patient.setId(resultSet.getInt("PatientID"));
               patient.setFirstName(resultSet.getString("First_Name"));
               patient.setLastName(resultSet.getString("Last_Name"));
               patient.setGender(resultSet.getString("Gender"));
               patient.setContactNumber(resultSet.getString("ContactNumber"));
               patient.setAge((short)resultSet.getInt("Age"));
               patient.setEmail(resultSet.getString("EmailID"));
               patient.setBloodGroup(resultSet.getString("BloodGroup"));
               patient.setAddress(resultSet.getString("Address"));
               return patient;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        return Repository.getInstance()
                .executeQuery("Select * from Patients where PatientID="+id, mapper).get(0);
    }

    @Override
    public boolean savePatient(Patient patient) {
        patient.setId(autoPatientID());

        boolean done = Repository.getInstance()
                .executeUpdate("insert into Users values('"+patient.getId()+"','"+"Doctor"+"','"+ patient.getPassword()+"')");

        if(!done) {
            return false;
        }

        done = Repository.getInstance()
                .executeUpdate("INSERT INTO Patients VALUES ('"+patient.getId()+"','"+patient.getFirstName()+"','"
                        +patient.getLastName()+"','"+patient.getGender()+"','"+patient.getContactNumber()+"','"
                        +patient.getAge()+"','"+patient.getEmail()+"','"+patient.getBloodGroup()+"','"
                        +patient.getAddress()+"')");

        if (!done) {
            // TODO ROLL BACK USER
            return false;
        }
        return done;
    }

    private int autoPatientID()  {
        Function <ResultSet, Integer> mapper = resultSet -> {
            try {
                return resultSet.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        List<Integer> result = Repository.getInstance()
                .executeQuery("Select MAX(userID) as 'NextPatientID' from Users", mapper);

        if (result.isEmpty()) {
            return 1;
        }
        return result.get(0) + 1;
    }


}
