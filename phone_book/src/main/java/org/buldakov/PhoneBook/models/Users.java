package org.buldakov.PhoneBook.models;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Component
public class Users {
    private int user_id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String user_name;

    @NotEmpty(message = "Error input")
    @Size(min = 7, max = 11, message = "Name should be 7 or 11 characters")
    private String phone;

    public Users() {

    }

    public Users(int id, String user_name, String user_phone) {
        this.user_id = id;
        this.user_name = user_name;
        this.phone = user_phone;
    }

    public int getId() {
        return user_id;
    }

    public void setId(int id) {
        this.user_id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPhone() {return phone;}

    public void setPhone(String user_phone) {this.phone = user_phone;}
}
