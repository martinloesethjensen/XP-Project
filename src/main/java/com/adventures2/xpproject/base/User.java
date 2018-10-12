package com.adventures2.xpproject.base;

public class User {
    private int user_id;
    private String username;
    private String password;
    private int niveau;
    private String realname;

    public User() {
    }

    public User(int user_id, String username, String password, int niveau, String realname) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.niveau = niveau;
        this.realname = realname;
    }

    public User(String username, String password, int niveau, String realname) {
        this.username = username;
        this.password = password;
        this.niveau = niveau;
        this.realname = realname;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", niveau=" + niveau +
                ", realname='" + realname + '\'' +
                '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
