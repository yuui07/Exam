package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject; // 正しいパッケージからSubjectをインポート

public class SubjectDao {

    private Connection connection;

    public SubjectDao(Connection connection) {
        this.connection = connection;
    }

    public Subject get(String cd, School school) throws SQLException {
        String sql = "SELECT * FROM Subject WHERE CD = ? AND SCHOOL_CD = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cd);
            stmt.setString(2, school.getCd());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRowToSubject(rs, school);
            }
        }
        return null;
    }

    public List<Subject> filter(School school) throws SQLException {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT * FROM Subject WHERE SCHOOL_CD = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, school.getCd());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                subjects.add(mapRowToSubject(rs, school));
            }
        }
        return subjects;
    }

    public boolean save(Subject subject) throws SQLException {
        String sql = "INSERT INTO Subject (SCHOOL_CD, CD, NAME) VALUES (?, ?, ?) ON CONFLICT (CD, SCHOOL_CD) DO UPDATE SET NAME = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, subject.getSchool().getCd());
            stmt.setString(2, subject.getCd());
            stmt.setString(3, subject.getName());
            stmt.setString(4, subject.getName());
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean delete(Subject subject) throws SQLException {
        String sql = "DELETE FROM Subject WHERE CD = ? AND SCHOOL_CD = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, subject.getCd());
            stmt.setString(2, subject.getSchool().getCd());
            return stmt.executeUpdate() > 0;
        }
    }

    private Subject mapRowToSubject(ResultSet rs, School school) throws SQLException {
        Subject subject = new Subject();
        subject.setCd(rs.getString("CD"));
        subject.setName(rs.getString("NAME"));
        subject.setSchool(school);
        return subject;
    }
}
