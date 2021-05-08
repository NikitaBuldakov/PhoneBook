package org.buldakov.PhoneBook.DAO;

import org.buldakov.PhoneBook.models.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersMapper implements RowMapper<Users> {
    @Override
    public Users mapRow(ResultSet resultSet, int i) throws SQLException {
        Users users = new Users();

        users.setId(resultSet.getInt("user_id"));
        users.setUser_name(resultSet.getString("user_name"));
        users.setPhone(resultSet.getString("phone"));

        return users;
    }
}
