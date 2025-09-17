package com.example.assignment3;

import java.io.Serializable;

public class User implements Serializable {
    String name;
    String email;
    String role;

    public User(){}
    public User (String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setRole(String role){
        this.role = role;
    }

    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getRole(){
        return role;
    }

}
