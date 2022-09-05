package com.sg.superhero.dao;

import com.sg.superhero.dto.Member;
import com.sg.superhero.dto.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;
import java.util.List;

public class MemberHelper {

    public static Member addMember(Member member, JdbcTemplate jdbcTemplate) {
        final String sql = "INSERT INTO member(person, org) " +
                "VALUES(?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, member.getPersonId());
            statement.setInt(2, member.getOrgId());

            return statement;
        }, keyHolder);

        return member;
    }

    public static List<Member> getAllMembers(JdbcTemplate jdbcTemplate) {
        final String sql = "SELECT * FROM member;";
        return jdbcTemplate.query(sql, new MemberMapper());
    }

    public static boolean deleteMember(Member member, JdbcTemplate jdbcTemplate) {
        final String DELETE_MEMBER = "DELETE FROM member WHERE person = ? AND org = ?";
        jdbcTemplate.update(DELETE_MEMBER, member.getPersonId(), member.getOrgId());

        return true;
    }

    private static final class MemberMapper implements RowMapper<Member> {

        @Override
        public Member mapRow(ResultSet rs, int index) throws SQLException {
            Member m = new Member();
            m.setPersonId(rs.getInt("person"));
            m.setOrgId(rs.getInt("org"));

            return m;
        }
    }

}
