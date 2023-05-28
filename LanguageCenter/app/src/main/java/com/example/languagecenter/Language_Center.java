package com.example.languagecenter;

import java.io.Serializable;

public class Language_Center implements Serializable {
    private int LangID, OTP;
    private String Username, PWD, Gmail;

    public Language_Center(int langID, int OTP, String username, String PWD, String gmail) {
        this.LangID = langID;
        this.OTP = OTP;
        this.Username = username;
        this.PWD = PWD;
        this.Gmail = gmail;
    }

    public int getLangID() {
        return LangID;
    }

    public int getOTP() {
        return OTP;
    }

    public void setOTP(int OTP) {
        this.OTP = OTP;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPWD() {
        return PWD;
    }

    public void setPWD(String PWD) {
        this.PWD = PWD;
    }

    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String gmail) {
        Gmail = gmail;
    }

    @Override
    public String toString() {
        return "Language_Center{" +
                "LangID=" + LangID +
                ", OTP=" + OTP +
                ", Username='" + Username + '\'' +
                ", PWD='" + PWD + '\'' +
                ", Gmail='" + Gmail + '\'' +
                '}';
    }
}
