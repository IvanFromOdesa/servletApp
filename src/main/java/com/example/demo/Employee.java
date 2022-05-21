package com.example.demo;

public class Employee {

    private int id;
    private String name;
    private String country;
    private String email;
    private String experience;
    private String salary;
    private String working_hours;

    public void setExperience(String experience) {this.experience = experience;}

    public String getExperience() {return experience;}

    public void setSalary(String salary) {this.salary = salary;}

    public String getSalary() {return salary;}

    public void setWorking_hours(String working_hours) {this.working_hours = working_hours;}

    public String getWorking_hours() {return working_hours;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
