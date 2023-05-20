package org.hcms.doctor;

import org.hcms.data.Doctor;

import java.util.List;

public interface DoctorService {

    List<Doctor> getDoctors();

    boolean addDoctor(Doctor doctor);

    boolean removeDoctor(int id);
}
