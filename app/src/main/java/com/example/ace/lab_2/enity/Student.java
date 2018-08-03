package com.example.ace.lab_2.enity;

import org.litepal.crud.LitePalSupport;

/**
 * Created by ace on 2018/6/25.
 */

public class Student extends LitePalSupport{

    private String number;
    private String name;
    private String phone;
    private String labgrade;
    private String theorygrage;
    private String major;

    public Student(){}

    public Student(String number, String name, String phone, String major,String alter) {
        this.number = number;
        this.name = name;
        this.phone = phone;
        this.major = major;
    }

    public Student(String number, String name, String phone, String major) {
        this.number = number;
        this.name = name;
        this.phone = phone;
        this.major = major;
        this.labgrade = "未打分";
        this.theorygrage = "未打分";
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabgrade() {
        return labgrade;
    }

    public void setLabgrade(String labgrade) {
        this.labgrade = labgrade;
    }

    public String getTheorygrage() {
        return theorygrage;
    }

    public void setTheorygrage(String theorygrage) {
        this.theorygrage = theorygrage;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
