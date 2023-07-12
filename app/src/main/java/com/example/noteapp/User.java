package com.example.noteapp;

import java.util.ArrayList;
import java.util.List;

public class User {
    public static List<User> userList = new ArrayList<User>();
    public String UserName;
    public String Email;
    public String Phone;
    public String Password;

    public User(String userName, String email, String phone, String password){
        UserName = userName;
        Email = email;
        Phone = phone;
        Password = password;
    }
}
