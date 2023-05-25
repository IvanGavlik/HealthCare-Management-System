package org.hcms.doctor;

import org.hcms.data.Doctor;
import org.hcms.data.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

final class DoctorServiceImpl implements DoctorService {
    private Repository repository;
    private Function<ResultSet,Doctor> dbRowToDoctor = rs -> {
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

    DoctorServiceImpl(Repository repository) {
        this.repository = repository;
    }
    @Override
    public List<Doctor> getDoctors() {
        return repository.executeQuery("SELECT * FROM Doctors", dbRowToDoctor); // TODO LIMIT " + maxRows;
    }

    @Override
    public boolean addDoctor(Doctor doctor) {
        int docid = autoDoctorID();

        boolean done = Repository.getInstance()
                .executeUpdate("insert into Users values('"+docid+"','"+"Doctor"+"','"+ doctor.getPassword()+"')");

        if(!done) {
            return false;
        }

        done = Repository.getInstance()
                .executeUpdate("INSERT INTO Doctors VALUES ('"+docid+"','"+doctor.getFirstName()+"','"+doctor.getLastName()+"','"+doctor.getGender()+"','"+doctor.getContactNumber()+"','"+doctor.getAge()+"','"+doctor.getEntryCharge()+"','"+doctor.getQualification()+"','"+doctor.getDoctorType()+"','"+doctor.getEmail()+"')");
        if (!done) {
            // TODO roolback added user
//            Repository.getInstance().executeUpdate("delete  from Doctors where DoctorID = "+docid " and ");
        }

        return done;
    }
    private int autoDoctorID()  {
        final String query = "Select MAX(UserID) as NextUserID from Users where userType='Doctor'";
        final Function<ResultSet, Integer> mapper = (r) -> {
            try {
                return r.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };
        final List<Integer> i = Repository.getInstance()
                .executeQuery(query, mapper);
        if(i.isEmpty()) {
            return 1;
        }
        return i.get(0) + 1;
    }

    @Override
    public boolean removeDoctor(int id) {
        return Repository.getInstance().executeUpdate("delete from Doctors where DoctorID = "+id);
    }

    @Override
    public List<Doctor> getDoctorsByType(String type) {
        return Repository.getInstance()
                .executeQuery(String.format("select * from Doctors where Doctor_Type='%s'", type), dbRowToDoctor);
    }

    @Override
    public Doctor getDoctor(int id) {
        return Repository.getInstance()
                .executeQuery("select * from Doctors where DoctorID="+id, dbRowToDoctor)
                .get(0);
    }


}
