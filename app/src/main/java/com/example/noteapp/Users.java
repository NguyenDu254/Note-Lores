package com.example.noteapp;

public class Users {
//    public static List<User> userList = new ArrayList<User>();
String UserId, UserName, Email;

    public Users(String userId, String userName, String email) {
        UserId = userId;
        UserName = userName;
        Email = email;
    }

    public Users() {
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
