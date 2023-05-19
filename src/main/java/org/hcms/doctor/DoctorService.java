package org.hcms.doctor;

import org.hcms.data.Doctor;

import java.util.List;

public interface DoctorService {

    List<Doctor> getDoctors();

    void addDoctor(Doctor doctor);
}
