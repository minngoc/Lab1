package com.example.mobile_labs_20521669;

public class User {
    private String userName;
    private String fullName;
    private String password;
    private String phone;

    public User(){

    }

    public User(String userName, String fullName, String password, String phone) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
