package org.karthik.beans;

public class User {

    private String firstName;
    private String DateOfBirth;
    private String ContactNumber;
    private String EmailAddress;
    private String ResidentialAddress;
    private String IdentificationProof;


    public User(String firstName, String dateOfBirth, String contactNumber, String emailAddress, String residentialAddress, String identificationProof) {
        this.firstName = firstName;
        this.DateOfBirth = dateOfBirth;
        this.ContactNumber = contactNumber;
        this.EmailAddress = emailAddress;
        this.ResidentialAddress = residentialAddress;
        this.IdentificationProof = identificationProof;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getResidentialAddress() {
        return ResidentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        ResidentialAddress = residentialAddress;
    }

    public String getIdentificationProof() {
        return IdentificationProof;
    }

    public void setIdentificationProof(String identificationProof) {
        IdentificationProof = identificationProof;
    }


    @Override
    public String toString() {
        return "Banking_Application.User{" +
                "DateOfBirth='" + DateOfBirth + '\'' +
                ", ContactNumber='" + ContactNumber + '\'' +
                ", EmailAddress='" + EmailAddress + '\'' +
                ", ResidentialAddress='" + ResidentialAddress + '\'' +
                ", IdentificationProof='" + IdentificationProof + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }


}
