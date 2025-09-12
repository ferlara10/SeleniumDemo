package com.example.app.utils;

public class Contact {
    private String name;
    private String email;
    private String phone;
    private String id;

    public Contact(){
        this.name = null;
        this.email = null;
        this.phone = null;
        this.id = null;
    }

    public Contact(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getID() {
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public void setId(String id){
        this.id = id;
    }

}
