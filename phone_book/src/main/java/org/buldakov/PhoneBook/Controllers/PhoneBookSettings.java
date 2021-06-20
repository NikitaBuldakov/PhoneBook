package org.buldakov.PhoneBook.Controllers;


import java.util.List;
import org.buldakov.PhoneBook.DAO.PhoneBookDAO;
import org.buldakov.PhoneBook.models.PhoneBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@Controller
public class PhoneBookSettings {

    private final PhoneBookDAO phoneBookDAO;
    private PhoneBook phoneBook;

    @Autowired
    public PhoneBookSettings(PhoneBookDAO phoneBookDAO, PhoneBook phoneBook){
        this.phoneBookDAO = phoneBookDAO;
        this.phoneBook = phoneBook;

    }


    @CrossOrigin
    @GetMapping("/PhoneBooks/getAll")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PhoneBook>> sendList(@RequestParam("user_id") int id) {
        List<PhoneBook> phoneBooks = phoneBookDAO.getAll(id);
        return ResponseEntity.ok().body(phoneBooks);
    }



    @CrossOrigin
    @GetMapping("/PhoneBooks/getOne")
    @ResponseStatus(HttpStatus.OK)
    public HttpEntity<PhoneBook> sendNote(@RequestParam("book_id") int id) {

        phoneBook = phoneBookDAO.getOne(id);
        return new HttpEntity<>(phoneBook);
    }



    @CrossOrigin
    @PostMapping("/PhoneBooks/Save")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpEntity<PhoneBook> saveNote(@Valid @RequestBody PhoneBook phoneBook) {

        phoneBookDAO.Insert(phoneBook);
        return new HttpEntity<>(phoneBook);

    }



    @CrossOrigin
    @PostMapping("/PhoneBooks/Update")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpEntity<PhoneBook> updateNote(@Valid @RequestBody PhoneBook phoneBook) {
        phoneBookDAO.Update(phoneBook);
        return new HttpEntity<>(phoneBook);

    }

    @CrossOrigin
    @PostMapping("/PhoneBooks/Delete")
    public ResponseEntity<HttpStatus> deleteNote(@Valid @RequestBody PhoneBook phoneBook) {
        phoneBookDAO.delete(phoneBook.getId());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @CrossOrigin
    @PostMapping("/PhoneBooks/getbyPhone")
    @ResponseStatus(HttpStatus.OK)
    public HttpEntity<PhoneBook> sendNotebyPhone(@Valid @RequestBody PhoneBook phoneBook) {
        PhoneBook p = phoneBookDAO.getOnebyPhone(phoneBook.getUser_phone(), phoneBook.getUser_id());
        return new HttpEntity<>(p);
    }
}+
