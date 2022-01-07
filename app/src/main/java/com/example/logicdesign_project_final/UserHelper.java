package com.example.logicdesign_project_final;

import android.view.View;

public class UserHelper {
    String fullname, DoB,email, phone, password,studentID,ID;

    public UserHelper() {
    }
    public UserHelper(String fullname, String Dob,String studentID, String email,String password ,String phone, String ID ) {
        this.fullname = fullname;
        this.DoB = Dob;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.studentID=studentID;
        this.ID = ID;
    }

    public String getFullName() {
        return fullname;
    }

    public void setFullName(String name) {
        this.fullname = name;
    }

    public String getDoB() {
        return DoB;
    }

    public void setDoB(String DoB) {
        this.DoB = DoB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public String getstudentID() {
        return studentID;
    }

    public void setstudentID(String studentID) {
        this.studentID = studentID;
    }

    public void update(View view){

    }
}