package com.example.jawad.DrinkTonight.api.Login;

import java.util.ArrayList;
import java.util.List;


public class UserResponse {

private Boolean success;

private List<User> users = new ArrayList<User>();

    @Override
    public String toString() {
        return "UserResponse{" +
                "success=" + success +
                ", users=" + users +
                '}';
    }

    /**
* 
* @return
* The success
*/
public Boolean getSuccess() {
return success;
}

/**
* 
* @param success
* The success
*/
public void setSuccess(Boolean success) {
this.success = success;
}

/**
* 
* @return
* The users
*/
public List<User> getUsers() {
return users;
}

/**
* 
* @param users
* The users
*/
public void setUsers(List<User> users) {
this.users = users;
}

}
