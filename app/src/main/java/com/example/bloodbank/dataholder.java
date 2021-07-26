package com.example.bloodbank;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class dataholder implements Serializable {
    @Exclude
            private String id;

    String name,address,mobile,bloodgroup,pimage;

    public dataholder(){

    }

    public dataholder(String name, String address, String mobile, String bloodgroup, String pimage) {
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.bloodgroup = bloodgroup;
        this.pimage = pimage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
