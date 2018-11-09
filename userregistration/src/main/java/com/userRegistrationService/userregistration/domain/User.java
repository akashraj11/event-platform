package com.userRegistrationService.userregistration.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

@Document
public class User {
    @Id
    private String email;
    private String name;
    private String phoneNumber;
    private String gender;
    private String city;
    @Transient
    private String password;
    private String genre[];
    private String language[];
    private String watchList[];

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public String[] getLanguage() {
        return language;
    }

    public void setLanguage(String[] language) {
        this.language = language;
    }

    public String[] getWatchList() {
        return watchList;
    }

    public void setWatchList(String[] watchList) {
        this.watchList = watchList;
    }


    public User() {
    }

    public User(String email, String name, String phoneNumber, String gender, String city, String password, String[] genre, String[] language, String[] watchList) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.city = city;
        this.password = password;
        this.genre = genre;
        this.language = language;
        this.watchList = watchList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                ", password='" + password + '\'' +
                ", genre=" + Arrays.toString(genre) +
                ", language=" + Arrays.toString(language) +
                ", watchList=" + Arrays.toString(watchList) +
                '}';
    }
}
