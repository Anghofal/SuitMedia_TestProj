package com.ahmadsapplication.app;

import com.google.gson.annotations.SerializedName;

public class DataList {
    int id;
    //@SerializedName("avatar")
    String email,first_name,last_name,avatar;

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getAvatar() {
        return avatar;
    }
}
