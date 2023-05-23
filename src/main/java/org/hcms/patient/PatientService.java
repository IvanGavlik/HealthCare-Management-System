package org.hcms.patient;

import org.hcms.data.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> getPatients();

    Patient getPatient(int id);

    boolean savePatient(Patient patient);
}
