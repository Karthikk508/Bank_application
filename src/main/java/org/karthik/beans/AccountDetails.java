package org.karthik.beans;


import java.sql.Timestamp;
import java.util.Date;

public class AccountDetails {

    private int acntId;
    private Integer userId;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String emailId;
    private String address;
    private String acntNo;
    private Date dob;
    private String acntType;
    private String ifsc;
    private String branch;
    private Timestamp doi;

    public int getAcntId() {
        return acntId;
    }

    public void setAcntId(int acntId) {
        this.acntId = acntId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAcntNo() {
        return acntNo;
    }

    public void setAcntNo(String acntNo) {
        this.acntNo = acntNo;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAcntType() {
        return acntType;
    }

    public void setAcntType(String acntType) {
        this.acntType = acntType;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Timestamp getDoi() {
        return doi;
    }

    public void setDoi(Timestamp doi) {
        this.doi = doi;
    }


    @Override
    public String toString() {
        return "AccountDetails{" +
                "acntId=" + acntId +
                ", userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", emailId='" + emailId + '\'' +
                ", address='" + address + '\'' +
                ", acntNo='" + acntNo + '\'' +
                ", dob=" + dob +
                ", acntType='" + acntType + '\'' +
                ", ifsc='" + ifsc + '\'' +
                ", branch='" + branch + '\'' +
                ", doi=" + doi +
                '}';
    }
}
