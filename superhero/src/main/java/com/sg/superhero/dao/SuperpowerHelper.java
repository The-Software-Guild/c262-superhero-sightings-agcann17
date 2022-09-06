package com.sg.superhero.dao;

import com.sg.superhero.dto.Person;
import com.sg.superhero.dto.Superpower;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;
import java.util.List;

public class SuperpowerHelper {

    public static Superpower addSuperpower(Superpower sp, JdbcTemplate jdbcTemplate) {
        final String sql = "INSERT INTO superpower(superpower) " +
                "VALUES(?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, sp.getSuperpower());

            return statement;
        }, keyHolder);

        sp.setId(keyHolder.getKey().intValue());

        return sp;
    }

    public static Superpower getSuperpowerById(int id, JdbcTemplate jdbcTemplate) {
        final String sql = "SELECT id, superpower FROM superpower WHERE id = ?;";
        return jdbcTemplate.queryForObject(sql, new SuperpowerMapper(), id);
    }

    public static List<Superpower> getAllSuperpowers(JdbcTemplate jdbcTemplate) {
        final String sql = "SELECT * FROM superpower;";
        return jdbcTemplate.query(sql, new SuperpowerMapper());
    }

    public static boolean updateSuperpower(Superpower sp, JdbcTemplate jdbcTemplate) {
        final String sql = "UPDATE superpower SET superpower = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                sp.getSuperpower(),
                sp.getId());

        return true;
    }

    public static boolean deleteSuperpower(Superpower sp, JdbcTemplate jdbcTemplate) {
        final String DELETE_PERSON = "DELETE p.* FROM person p "
                + "JOIN superpower sp ON p.superpower = sp.id WHERE sp.id = ?";
        jdbcTemplate.update(DELETE_PERSON, sp.getId());

        final String DELETE_SUPERPOWER = "DELETE FROM superpower WHERE id = ?";
        jdbcTemplate.update(DELETE_SUPERPOWER, sp.getId());

        return true;
    }

    public static final class SuperpowerMapper implements RowMapper<Superpower> {

        @Override
        public Superpower mapRow(ResultSet rs, int index) throws SQLException {
            Superpower sp = new Superpower();
            sp.setId(rs.getInt("id"));
            sp.setSuperpower(rs.getString("superpower"));

            return sp;
        }
    }
}
