package com.sg.superhero.dao;

import com.sg.superhero.dto.Org;
import com.sg.superhero.dto.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;
import java.util.List;

public class OrgHelper {

    public static Org addOrg(Org org, JdbcTemplate jdbcTemplate) {
        final String sql = "INSERT INTO org(name, description, location) " +
                "VALUES(?, ?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, org.getName());
            statement.setString(2, org.getDescription());
            statement.setInt(3, org.getLocationId());

            return statement;
        }, keyHolder);

        org.setId(keyHolder.getKey().intValue());

        return org;
    }

    public static Org getOrgById(int id, JdbcTemplate jdbcTemplate) {
        final String sql = "SELECT id, name, description, location " +
                "FROM org WHERE id = ?;";
        return jdbcTemplate.queryForObject(sql, new OrgMapper(), id);
    }

    public static List<Org> getAllOrgs(JdbcTemplate jdbcTemplate) {
        final String sql = "SELECT * FROM org;";
        return jdbcTemplate.query(sql, new OrgMapper());
    }

    public static List<Org> getAllOrgByPerson(int personId, JdbcTemplate jdbcTemplate) {
        final String sql = "SELECT o.* FROM org o JOIN member m ON m.org = o.id " +
                "WHERE m.person = ?;";
        return jdbcTemplate.query(sql, new OrgMapper(), personId);
    }

    public static boolean updateOrg(Org org, JdbcTemplate jdbcTemplate) {
        final String sql = "UPDATE org SET name = ?, description = ?, " +
                "location = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                org.getName(),
                org.getDescription(),
                org.getLocationId(),
                org.getId());

        return true;
    }

    public static boolean deleteOrg(Org org, JdbcTemplate jdbcTemplate) {
        final String DELETE_ORG_MEMBER = "DELETE m.* FROM members m "
                + "JOIN org o ON m.org = o.id WHERE o.id = ?";
        jdbcTemplate.update(DELETE_ORG_MEMBER, org.getId());

        final String DELETE_ORG = "DELETE FROM org WHERE id = ?";
        jdbcTemplate.update(DELETE_ORG, org.getId());

        return true;
    }

    private static final class OrgMapper implements RowMapper<Org> {

        @Override
        public Org mapRow(ResultSet rs, int index) throws SQLException {
            Org org = new Org();
            org.setId(rs.getInt("id"));
            org.setName(rs.getString("name"));
            org.setDescription(rs.getString("description"));
            org.setLocationId(rs.getInt("location"));

            return org;
        }
    }
}
