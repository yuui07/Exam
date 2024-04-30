package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao {
    private String baseSql = "SELECT STUDENT_NO, NAME, POINT, NO FROM TEST WHERE SUBJECT_CD = ? AND SCHOOL_CD = ? AND CLASS_NUM = ?";

    private Connection connection;

    public TestListSubjectDao(Connection connection) {
        this.connection = connection;
    }

    private List<TestListSubject> postFilter(ResultSet rs) throws SQLException {
        List<TestListSubject> testListSubjects = new ArrayList<>();
        Map<String, TestListSubject> map = new HashMap<>();

        while (rs.next()) {
            String studentNo = rs.getString("STUDENT_NO");
            if (!map.containsKey(studentNo)) {
                TestListSubject tls = new TestListSubject();
                tls.setStudentNo(studentNo);
                tls.setStudentName(rs.getString("NAME")); // 仮に学生名が ResultSet に含まれると仮定
                tls.setPoints(new HashMap<>());
                map.put(studentNo, tls);
            }
            TestListSubject tls = map.get(studentNo);
            tls.getPoints().put(rs.getInt("NO"), rs.getInt("POINT"));
        }

        testListSubjects.addAll(map.values());
        return testListSubjects;
    }

    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws SQLException {
        List<TestListSubject> testListSubjects = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(baseSql)) {
            stmt.setString(1, subject.getCd());
            stmt.setString(2, school.getCd());
            stmt.setString(3, classNum);
            try (ResultSet rs = stmt.executeQuery()) {
                testListSubjects = postFilter(rs);
                testListSubjects.forEach(tls -> tls.setClassNum(classNum));
                testListSubjects.forEach(tls -> tls.setEntYear(entYear));
            }
        }
        return testListSubjects;
    }
}
