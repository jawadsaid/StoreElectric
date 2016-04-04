package com.example.jawad.DrinkTonight.api.Login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
@Expose
private Integer id;
@SerializedName("username")
@Expose
private String username;
@SerializedName("password")
@Expose
private String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

/**
* 
* @return
* The id
*/
public Integer getId() {
return id;
}

/**
* 
* @param id
* The id
*/
public void setId(Integer id) {
this.id = id;
}

/**
* 
* @return
* The username
*/
public String getUsername() {
return username;
}

/**
* 
* @param username
* The username
*/
public void setUsername(String username) {
this.username = username;
}

/**
* 
* @return
* The password
*/
public String getPassword() {
return password;
}

/**
* 
* @param password
* The password
*/
public void setPassword(String password) {
this.password = password;
}

}