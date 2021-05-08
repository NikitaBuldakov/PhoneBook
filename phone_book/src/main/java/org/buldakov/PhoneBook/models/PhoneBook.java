package org.buldakov.PhoneBook.models;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Component
public class PhoneBook {


    private int book_id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String user_name;

    @NotEmpty(message = "Error input")
    @Size(min = 7, max = 11, message = "Name should be 7 or 11 characters")
    private String user_phone;

    private int user_id;

    public PhoneBook() {}

    public PhoneBook(int book_id, String user_name, int user_id, String user_phone) {
        this.book_id = book_id;
        this.user_name = user_name;
        this.user_id = user_id;
        this.user_phone = user_phone;
    }

    public int getId() {
        return book_id;
    }

    public void setId(int id) {
        this.book_id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_phone() {return user_phone;}

    public void setUser_phone(String user_phone) {this.user_phone = user_phone;}
}
