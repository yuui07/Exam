package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {

    private String baseSql = "select * from student where school_cd=?";

    public Student get(String no) throws Exception {
        Student student = null;  // 初期化をnullに変更
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from student where no=?")) {

            statement.setString(1, no);
            try (ResultSet rSet = statement.executeQuery()) {
                if (rSet.next()) {
                    student = new Student();
                    student.setNo(rSet.getString("no"));
                    student.setName(rSet.getString("name"));
                    student.setEntYear(rSet.getInt("ent_year"));
                    student.setClassNum(rSet.getString("class_num"));
                    student.setAttend(rSet.getBoolean("is_attend"));
                    SchoolDao schoolDao = new SchoolDao();
                    student.setSchool(schoolDao.get(rSet.getString("school_cd")));
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return student;
    }

    private List<Student> postFilter(ResultSet rSet, School school) throws Exception {
        List<Student> list = new ArrayList<>();
        while (rSet.next()) {
            Student student = new Student();
            student.setNo(rSet.getString("no"));
            student.setName(rSet.getString("name"));
            student.setEntYear(rSet.getInt("ent_year"));
            student.setClassNum(rSet.getString("class_num"));
            student.setAttend(rSet.getBoolean("is_attend"));
            student.setSchool(school);
            list.add(student);
        }
        return list;
    }

    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        String condition = " and ent_year=? and class_num=?";
        String order = " order by no asc";
        String conditionIsAttend = isAttend ? " and is_attend=true" : "";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order)) {

            statement.setString(1, school.getCd());
            statement.setInt(2, entYear);
            statement.setString(3, classNum);
            try (ResultSet rSet = statement.executeQuery()) {
                list = postFilter(rSet, school);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        String condition = " and ent_year=?";
        String order = " order by no asc";
        String conditionIsAttend = isAttend ? " and is_attend=true" : "";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order)) {

            statement.setString(1, school.getCd());
            statement.setInt(2, entYear);
            try (ResultSet rSet = statement.executeQuery()) {
                list = postFilter(rSet, school);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public List<Student> filter(School school, boolean isAttend) throws Exception {
        List<Student> list = new ArrayList<>();
        String order = " order by no asc";
        String conditionIsAttend = isAttend ? " and is_attend=true" : "";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(baseSql + conditionIsAttend + order)) {

            statement.setString(1, school.getCd());
            try (ResultSet rSet = statement.executeQuery()) {
                list = postFilter(rSet, school);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    public boolean save(Student student) throws Exception {
        int count = 0;

        try (Connection connection = getConnection()) {
            Student old = get(student.getNo());
            if (old == null) {
                try (PreparedStatement statement = connection.prepareStatement(
                        "insert into student(no, name, ent_year, class_num, is_attend, school_cd) values (?, ?, ?, ?, ?, ?)")) {
                    statement.setString(1, student.getNo());
                    statement.setString(2, student.getName());
                    statement.setInt(3, student.getEntYear());
                    statement.setString(4, student.getClassNum());
                    statement.setBoolean(5, student.isAttend());
                    statement.setString(6, student.getSchool().getCd());
                    count = statement.executeUpdate();
                }
            } else {
                try (PreparedStatement statement = connection.prepareStatement(
                        "update student set name=?, ent_year=?, class_num=?, is_attend=? where no=?")) {
                    statement.setString(1, student.getName());
                    statement.setInt(2, student.getEntYear());
                    statement.setString(3, student.getClassNum());
                    statement.setBoolean(4, student.isAttend());
                    statement.setString(5, student.getNo());
                    count = statement.executeUpdate();
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return count > 0;
    }
}
