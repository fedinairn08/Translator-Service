package com.tinkoff.translater.dao.implementation;

import com.tinkoff.translater.dao.UserDAO;
import com.tinkoff.translater.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
@RequiredArgsConstructor
public class JDBCUserDAO implements UserDAO {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public User save(User user) {
        String query = "INSERT INTO \"user\"" +
                "(ip)" +
                "VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.toString());

            return ps;
        }, keyHolder);


        Number generatedId = keyHolder.getKey();
        if (generatedId != null) {
            return new User(generatedId.longValue(), user.getIp());
        } else {
            throw new RuntimeException("Failed to retrieve generated key");
        }
    }
}
