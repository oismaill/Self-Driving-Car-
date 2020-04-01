package com.example.carapp.Entites;

import java.util.ArrayList;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ArrayList<Car> userCars = new ArrayList<>();
    private UserType usertype;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUsertype() {
        return usertype;
    }

    public void setUsertype(UserType usertype) {
        this.usertype = usertype;
    }

    public ArrayList<Car> getUserCars() {
        return userCars;
    }

    public void setUserCars(ArrayList<Car> userCars) {
        this.userCars = userCars;
    }
}
