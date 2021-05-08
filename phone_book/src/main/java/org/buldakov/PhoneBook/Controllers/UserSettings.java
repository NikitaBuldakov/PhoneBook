package org.buldakov.PhoneBook.Controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.buldakov.PhoneBook.DAO.UsersDAO;
import org.buldakov.PhoneBook.models.PhoneBook;
import org.buldakov.PhoneBook.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserSettings {

    private final UsersDAO usersDAO;
    private Users users;

    @Autowired
    public UserSettings(Users users, UsersDAO usersDAO) {
        this.users = users;
        this.usersDAO= usersDAO;
    }

    @CrossOrigin
    @GetMapping("/Users/getAll")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Users>>  sendList() {
        List<Users> users = usersDAO.getAll();
        return ResponseEntity.ok().body(users);
    }

    @CrossOrigin
    @GetMapping("/Users/getOne")
    @ResponseStatus(HttpStatus.OK)
    public HttpEntity<Users> sendUser(@RequestParam("user_id") int id) {
        users = usersDAO.getOne(id);
        return new HttpEntity<>(users);
    }


    @CrossOrigin
    @PostMapping("/Users/Save")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpEntity<Users> saveNote(@Valid @RequestBody Users users) {
        usersDAO.Insert(users);
        return new HttpEntity<>(users);
    }

    @CrossOrigin
    @PostMapping("/Users/Update")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpEntity<Users> updateUser(@Valid @RequestBody Users users) {
        usersDAO.Update(users);
        return new HttpEntity<>(users);
    }

    @CrossOrigin
    @PostMapping("/Users/Delete")
    public ResponseEntity<Users> deleteUser(@Valid @RequestBody Users users) {
        usersDAO.delete(users.getId());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @GetMapping("/Users/getbyName")
    @ResponseStatus(HttpStatus.OK)
    public HttpEntity<List<Users>> getUserbyName(@RequestParam("user_name") String user_name) {
        List<Users> user = usersDAO.getUserbyName(user_name);
        return new HttpEntity<>(user);
    }

}
