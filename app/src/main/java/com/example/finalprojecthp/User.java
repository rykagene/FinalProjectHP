package com.example.finalprojecthp;

public class User {

    private String email, fname, lname, pass, house;


    public User() {

    }

    public User(String email,String pass,String fname, String lname,   String house) {
        this.email = email;
        this.pass = pass;
        this.fname = fname;
        this.lname = lname;
        this.house = house;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }
}
