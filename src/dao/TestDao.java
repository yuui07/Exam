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

public class TestDao extends Dao {
    private String baseSql = "SELECT * FROM TEST";

    public Test get(Student student, Subject subject, School school, int no) throws Exception {
        Test test = null;
        try (Connection conn = DriverManager.getConnection("jdbc:h2:~/exam", "sa", "");
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

    private List<Test> postFilter(ResultSet rs, School school) throws Exception {
        List<Test> tests = new ArrayList<>();
        StudentDao stuDao = new StudentDao();
        SubjectDao subDao = new SubjectDao();

        try {
            while (rs.next()) {
                Test test = new Test();
                test.setStudent(stuDao.get(rs.getString("student_no")));
                test.setSubject(subDao.get(rs.getString("subject_cd"), school));
                test.setSchool(school);
                test.setNo(rs.getInt("no"));
                test.setPoint(rs.getInt("point"));
                test.setClassNum(rs.getString("class_num"));

                tests.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
        List<Test> tests = new ArrayList<>();
        String order = "ORDER BY student_no ASC";
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(baseSql +
                 " WHERE STUDENT_NO IN (SELECT NO FROM STUDENT WHERE ENT_YEAR=? AND CLASS_NUM = ?) AND SUBJECT_CD = ? AND SCHOOL_CD = ?" + order)) {

            stmt.setInt(1, entYear);
            stmt.setString(2, classNum);
            stmt.setString(3, subject.getCd());
            stmt.setString(4, school.getCd());

            ResultSet rs = stmt.executeQuery();
            tests = postFilter(rs, school);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    public boolean save(List<Test> list) throws Exception {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:~/exam", "sa", "")) {
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

    private boolean save(Test test, Connection conn) throws Exception {
        String sql = "MERGE INTO TEST KEY (STUDENT_NO, SUBJECT_CD, SCHOOL_CD, NO) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, test.getStudent().getNo());
            stmt.setString(2, test.getSubject().getCd());
            stmt.setString(3, test.getSchool().getCd());
            stmt.setInt(4, test.getNo());
            stmt.setInt(5, test.getPoint());
            stmt.setString(6, test.getClassNum());
            int affectedRows = stmt.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(List<Test> list) throws Exception {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:~/exam", "sa", "")) {
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

    private boolean delete(Test test, Connection conn) throws Exception {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM TEST WHERE NO = ?")) {
            stmt.setInt(1, test.getNo());
            int affectedRows = stmt.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
