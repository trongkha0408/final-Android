package com.example.languagecenter;

import java.io.Serializable;

public class Students implements Serializable {
    private int StudentID, OTP;
    private String Username, PWD, Gmail;

    public Students(int studentID, int OTP, String username, String PWD, String Gmail) {
        this.StudentID = studentID;
        this.OTP = OTP;
        this.Username = username;
        this.PWD = PWD;
        this.Gmail = Gmail;
    }

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String gmail) {
        Gmail = gmail;
    }

    public int getStudentID() {
        return this.StudentID;
    }


    public int getOTP() {
        return OTP;
    }

    public void setOTP(int OTP) {
        this.OTP = OTP;
    }

    public String getUsername() {
        return this.Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getPWD() {
        return PWD;
    }

    public void setPWD(String PWD) {
        this.PWD = PWD;
    }

    @Override
    public String toString() {
        return "Students{" +
                "StudentID=" + StudentID +
                ", OTP=" + OTP +
                ", Username='" + Username + '\'' +
                ", PWD='" + PWD + '\'' +
                ", Gmail='" + Gmail + '\'' +
                '}';
    }
}
