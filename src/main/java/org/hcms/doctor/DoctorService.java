package org.hcms.doctor;

import org.hcms.data.Doctor;
import org.hcms.data.Repository;

import java.util.List;

public interface DoctorService {
    DoctorService DEFAULT_INSTANCE = new DoctorServiceImpl(Repository.getInstance());
    List<Doctor> getDoctors();

    boolean addDoctor(Doctor doctor);

    boolean removeDoctor(int id);
    List<Doctor> getDoctorsByType(String type);

    Doctor getDoctor(int id);
}
