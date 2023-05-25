package org.hcms.patient;

import org.hcms.data.Patient;
import org.hcms.data.Repository;

import java.util.List;

public interface PatientService {

    PatientService DEFAULT_INSTANCE = new PatientServiceImpl(Repository.getInstance());
    List<Patient> getPatients();

    Patient getPatient(int id);

    boolean savePatient(Patient patient);
}
