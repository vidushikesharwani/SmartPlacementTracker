package model;

public class Assessment {
    private String assessmentId;
    private String studentId;
    private double dsaScore;
    private double javaScore;
    private double aptitudeScore;
    private double communicationScore;

    public Assessment(String assessmentId, String studentId,
                      double dsaScore, double javaScore,
                      double aptitudeScore, double communicationScore) {
        this.assessmentId = assessmentId;
        this.studentId = studentId;
        this.dsaScore = dsaScore;
        this.javaScore = javaScore;
        this.aptitudeScore = aptitudeScore;
        this.communicationScore = communicationScore;
    }

    public String getAssessmentId() {
        return assessmentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public double getDsaScore() {
        return dsaScore;
    }

    public double getJavaScore() {
        return javaScore;
    }

    public double getAptitudeScore() {
        return aptitudeScore;
    }

    public double getCommunicationScore() {
        return communicationScore;
    }

    public double getAverageScore() {
        return (dsaScore + javaScore + aptitudeScore + communicationScore) / 4;
    }

    public void setDsaScore(double dsaScore) {
        this.dsaScore = dsaScore;
    }

    public void setJavaScore(double javaScore) {
        this.javaScore = javaScore;
    }

    public void setAptitudeScore(double aptitudeScore) {
        this.aptitudeScore = aptitudeScore;
    }

    public void setCommunicationScore(double communicationScore) {
        this.communicationScore = communicationScore;
    }

    @Override
    public String toString() {
        return "DSA: " + dsaScore +
                ", Java: " + javaScore +
                ", Aptitude: " + aptitudeScore +
                ", Communication: " + communicationScore;
    }
}