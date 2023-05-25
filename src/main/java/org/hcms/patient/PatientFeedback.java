package org.hcms.patient;

import org.hcms.data.Feedback;
import org.hcms.data.Repository;

import java.util.List;

public interface PatientFeedback {
    PatientFeedback DEFAULT_INSTANCE = new PatientFeedbackImpl(Repository.getInstance());
    List<Feedback> getFeedbacks();
    boolean addFeedback(Feedback feedback);
}
