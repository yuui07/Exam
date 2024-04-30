package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao {
    private String baseSql = "SELECT * FROM TEST";

    public Test get(Student student, Subject subject, School school, int no) {
        Test test = null;
        try (Connection conn = DriverManager.getConnection("jdbc:yourdburl", "username", "password");
             PreparedStatement stmt = conn.prepareStatement(baseSql + " WHERE STUDENT_NO = ? AND SUBJECT_CD = ? AND SCHOOL_CD = ? AND NO = ?")) {

            stmt.setString(1, student.getNo());
            stmt.setString(2, subject.getCd());
            stmt.setString(3, school.getCd());
            stmt.setInt(4, no);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                test = new Test();
                // set properties
                test.setStudent(student);
                test.setSubject(subject);
                test.setSchool(school);
                test.setNo(rs.getInt("NO"));
                test.setPoint(rs.getInt("POINT"));
                test.setClassNum(rs.getString("CLASS_NUM"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }

    private List<Student> postFilter(ResultSet rs, School school) {
        List<Student> students = new ArrayList<>();
        try {
            while (rs.next()) {
                Student student = new Student();
                student.setNo(rs.getString("STUDENT_NO"));
                // Assuming Student class has a method to set School
                student.setSchool(school);
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public List<Student> filter(int entYear, String classNum, Subject subject, int num, School school) {
        List<Student> students = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:yourdburl", "username", "password");
             PreparedStatement stmt = conn.prepareStatement(baseSql + " WHERE ENTRY_YEAR = ? AND CLASS_NUM = ? AND SUBJECT_CD = ? AND NUM = ? AND SCHOOL_CD = ?")) {

            stmt.setInt(1, entYear);
            stmt.setString(2, classNum);
            stmt.setString(3, subject.getCd());
            stmt.setInt(4, num);
            stmt.setString(5, school.getCd());
            ResultSet rs = stmt.executeQuery();

            students = postFilter(rs, school);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public boolean save(List<Test> list) {
        try (Connection conn = DriverManager.getConnection("jdbc:yourdburl", "username", "password")) {
            for (Test test : list) {
                if (!save(test, conn)) {
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean save(Test test, Connection conn) {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO TEST (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO, POINT, CLASS_NUM) VALUES (?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, test.getstudent().getNo());
            stmt.setString(2, test.getsubject().getCd());
            stmt.setString(3, test.getSchool().getCd());
            stmt.setInt(4, test.getno());
            stmt.setInt(5, test.getpoint());
            stmt.setString(6, test.getclassNum());
            int affectedRows = stmt.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(List<Test> list) {
        try (Connection conn = DriverManager.getConnection("jdbc:yourdburl", "username", "password")) {
            for (Test test : list) {
                if (!delete(test, conn)) {
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean delete(Test test, Connection conn) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM TEST WHERE NO = ?")) {
            stmt.setInt(1, test.getno());
            int affectedRows = stmt.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}