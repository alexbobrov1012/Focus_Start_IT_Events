package com.example.iteventscheckin.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "members_table")
public class Member implements Serializable {

    @PrimaryKey
    private int id;

    private String phone;

    private String city;

    private String company;

    private String position;

    private String addition;

    private String registeredDate;

    private boolean isVisited;

    private String agreedByManager;

    private String lastName;

    private String firstName;

    private String patronymic;

    private String email;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddition() {
        return addition;
    }

    public void setAddition(String addition) {
        this.addition = addition;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public String getAgreedByManager() {
        return agreedByManager;
    }

    public void setAgreedByManager(String agreedByManager) {
        this.agreedByManager = agreedByManager;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", company='" + company + '\'' +
                ", position='" + position + '\'' +
                ", addition='" + addition + '\'' +
                ", registeredDate='" + registeredDate + '\'' +
                ", isVisited=" + isVisited +
                ", agreedByManager='" + agreedByManager + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
