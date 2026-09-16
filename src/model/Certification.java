package model;

public class Certification {
    private String certificationId;
    private String name;
    private String issuingOrganization;
    private int year;

    public Certification(String certificationId, String name,
                          String issuingOrganization, int year) {
        this.certificationId = certificationId;
        this.name = name;
        this.issuingOrganization = issuingOrganization;
        this.year = year;
    }

    public String getCertificationId() {
        return certificationId;
    }

    public String getName() {
        return name;
    }

    public String getIssuingOrganization() {
        return issuingOrganization;
    }

    public int getYear() {
        return year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIssuingOrganization(String issuingOrganization) {
        this.issuingOrganization = issuingOrganization;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return name + " - " + issuingOrganization + " (" + year + ")";
    }
}