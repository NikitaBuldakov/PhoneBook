package org.buldakov.PhoneBook.DAO;

import org.buldakov.PhoneBook.models.PhoneBook;
import org.buldakov.PhoneBook.models.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhoneBookMapper implements RowMapper<PhoneBook> {
    @Override
    public PhoneBook mapRow(ResultSet resultSet, int i) throws SQLException {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.setId(resultSet.getInt("book_id"));
        phoneBook.setUser_id(resultSet.getInt("user_id"));
        phoneBook.setUser_name(resultSet.getString("user_name"));
        phoneBook.setUser_phone(resultSet.getString("user_phone"));

        return phoneBook;
    }
}
