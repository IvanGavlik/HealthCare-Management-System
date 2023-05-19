package org.hcms.admin;

import org.hcms.data.Doctor;

import java.util.List;

public interface AdminService {
    List<Doctor> getDoctors();
}
