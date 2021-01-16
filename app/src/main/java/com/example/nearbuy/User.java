package com.example.nearbuy;

public class User {
private String name, phone , mail ,address;
    public User(){

    }

    public User(String name, String mail, String phone, String address) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.address = address;

    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

}

