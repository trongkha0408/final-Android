package com.example.languagecenter;

public class Post {
    private String postID, name, image, price, descr, qty, phone, address, fb, timeUpload, langID, dayID, certID;

    public Post(String postID, String name, String image, String price, String descr, String qty, String phone, String address, String fb, String timeUpload, String langID, String dayID, String certID) {
        this.postID = postID;
        this.name = name;
        this.image = image;
        this.price = price;
        this.descr = descr;
        this.qty = qty;
        this.phone = phone;
        this.address = address;
        this.fb = fb;
        this.timeUpload = timeUpload;
        this.langID = langID;
        this.dayID = dayID;
        this.certID = certID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public String getTimeUpload() {
        return timeUpload;
    }

    public void setTimeUpload(String timeUpload) {
        this.timeUpload = timeUpload;
    }

    public String getLangID() {
        return langID;
    }

    public void setLangID(String langID) {
        this.langID = langID;
    }

    public String getDayID() {
        return dayID;
    }

    public void setDayID(String dayID) {
        this.dayID = dayID;
    }

    public String getCertID() {
        return certID;
    }

    public void setCertID(String certID) {
        this.certID = certID;
    }
}
