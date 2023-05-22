package org.hcms.patient;

import org.hcms.data.Feedback;
import org.hcms.data.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.function.Function;

public class PatientFeedbackImpl implements PatientFeedback {

    private Repository repository;

    public PatientFeedbackImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public List<Feedback> getFeedbacks() {
        Function<ResultSet, Feedback> mapToFeedback = (rs) -> {
            try {
                Feedback f = new Feedback();
                f.setId(rs.getInt("PatientID"));
                f.setPoints((short) rs.getInt("points"));
                f.setDocNature(rs.getString("Doc_Nature"));
                f.setLocation(rs.getString("Location"));
                f.setPatientComment(rs.getString("PatientComment"));
                return f;
            } catch (Exception ex) {
                throw new RuntimeException();
            }
        };
        return repository.executeQuery("SELECT * FROM feedback", mapToFeedback);
    }

    @Override
    public boolean addFeedback(Feedback feedback) {
        return Repository.getInstance()
                .executeUpdate("INSERT INTO feedback VALUES ('"+feedback.getId()+"','"+feedback.getPoints()+"','"
                        +feedback.getDocNature()+"','"+feedback.getLocation()+"','"+feedback.getPatientComment()+"')");
    }


}
