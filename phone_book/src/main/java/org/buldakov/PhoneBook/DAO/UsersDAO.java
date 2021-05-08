package org.buldakov.PhoneBook.DAO;

import org.buldakov.PhoneBook.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsersDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UsersDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Users> getAll() {
        return jdbcTemplate.query("SELECT * FROM \"User\"", new UsersMapper());
    }

    public Users getOne(int id) {
        return jdbcTemplate.query("SELECT * FROM \"User\" WHERE user_id=?", new Object[]{id}, new UsersMapper())
                .stream().findAny().orElse(null);
    }

    public void Insert(Users users) {
        jdbcTemplate.update("INSERT INTO \"User\"(user_name, phone) VALUES (?,?)",users.getUser_name(),
                users.getPhone());
    }

    public void Update(Users users) {
        jdbcTemplate.update("UPDATE \"User\" set user_name = ?, phone = ? WHERE user_id = ?",users.getUser_name(),
                users.getPhone(), users.getId());
    }

    public List<Users> getUserbyName(String name) {
        return jdbcTemplate.query("SELECT * FROM \"User\" WHERE user_name LIKE '%' || ? || '%'",
                new Object[]{name},
                new UsersMapper()
        );
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM \"User\" WHERE user_id=?", id);
    }
}
