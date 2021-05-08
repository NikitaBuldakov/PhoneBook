package org.buldakov.PhoneBook.DAO;

import org.buldakov.PhoneBook.models.PhoneBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PhoneBookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PhoneBookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<PhoneBook> getAll(int id) {
        return jdbcTemplate.query("SELECT * FROM phone_book WHERE user_id=?", new Object[]{id}, new PhoneBookMapper());
    }

    public PhoneBook getOne(int id) {
        return jdbcTemplate.query("SELECT * FROM phone_book WHERE book_id=?", new Object[]{id}, new PhoneBookMapper())
                .stream().findAny().orElse(null);
    }

    public void Insert(PhoneBook phoneBook) {
        jdbcTemplate.update("INSERT INTO phone_book(user_id, user_name, user_phone) VALUES(?,?,?)", phoneBook.getUser_id(),
                phoneBook.getUser_name(), phoneBook.getUser_phone());
    }

    public void Update(PhoneBook phoneBook) {
        jdbcTemplate.update("UPDATE phone_book SET user_name=?, user_phone=? WHERE book_id=?",phoneBook.getUser_name(),
                phoneBook.getUser_phone(), phoneBook.getId());
    }

    public PhoneBook getOnebyPhone(String user_phone, int user_id){
        return jdbcTemplate.query("SELECT * FROM phone_book WHERE user_phone=? AND user_id=?", new Object[]{user_phone, user_id}, new PhoneBookMapper())
                .stream().findAny().orElse(null);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM phone_book WHERE book_id=?", id);
    }
}
