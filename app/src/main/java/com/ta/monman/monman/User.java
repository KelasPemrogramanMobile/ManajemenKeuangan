package com.ta.monman.monman;

/**
 * Created by Ranma
 */


//this is very simple class and it only contains the user attributes, a constructor and the getters
// you can easily do this by right click -> generate -> constructor and getters
public class User {

    private int id;
    private String username, email, gender, nama_lengkap;

    public User( String nama_lengkap, int id, String username, String email, String gender) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.nama_lengkap = nama_lengkap;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

}
