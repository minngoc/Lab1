package com.example.lab2;

public class PersonalSalary {
    private String fullName;
    private int grossSalary;

    public PersonalSalary(){}

    public PersonalSalary(String fullName, int grossSalary){
        this.fullName = fullName;
        this.grossSalary = grossSalary;
    }

    public String getFullName(){
        return this.fullName;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public int getGrossSalary(){
        return this.grossSalary;
    }
    public void setGrossSalary(int grossSalary){
        this.grossSalary = grossSalary;
    }

    public double calculateNetSalary(){
        double tmp = grossSalary*(1-0.105);
        if(tmp <= 11000000)
            return tmp;
        return 11000000 + (tmp-11000000) * (1-0.05);
    }
}
