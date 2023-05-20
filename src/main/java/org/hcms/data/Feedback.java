package org.hcms.data;

import java.util.Objects;

public class Feedback {

    private int id;
    private short points;
    private String docNature;
    private String location;
    private String patientComment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getPoints() {
        return points;
    }

    public void setPoints(short points) {
        this.points = points;
    }

    public String getDocNature() {
        return docNature;
    }

    public void setDocNature(String docNature) {
        this.docNature = docNature;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPatientComment() {
        return patientComment;
    }

    public void setPatientComment(String patientComment) {
        this.patientComment = patientComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return id == feedback.id && points == feedback.points && Objects.equals(docNature, feedback.docNature) && Objects.equals(location, feedback.location) && Objects.equals(patientComment, feedback.patientComment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, points, docNature, location, patientComment);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", points=" + points +
                ", docNature='" + docNature + '\'' +
                ", location='" + location + '\'' +
                ", patientComment='" + patientComment + '\'' +
                '}';
    }
}
