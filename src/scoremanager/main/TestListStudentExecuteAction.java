package scoremanager.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

@WebServlet("/scoremanager/main/TestListStudentExecute")
public class TestListStudentExecuteAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentNo = request.getParameter("student_number");
        String entYear = request.getParameter("ent_year");
        String classNum = request.getParameter("class_num");
        String subjectCd = request.getParameter("subject_cd");

        List<Test> tests = new ArrayList<>();

        if (studentNo != null && !studentNo.isEmpty()) {
            tests = getTestsByStudentNo(studentNo);
        } else {
            tests = getTestsByCriteria(entYear, classNum, subjectCd);
        }

        if (tests.isEmpty()) {
            request.setAttribute("errorMessage", "指定された条件の成績データは見つかりませんでした。");
        } else {
            request.setAttribute("tests", tests);
        }

        request.getRequestDispatcher("test_list_student.jsp").forward(request, response);
    }

    private List<Test> getTestsByStudentNo(String studentNo) {
        List<Test> tests = new ArrayList<>();
        String url = "jdbc:h2:~/exam"; // H2データベースのパス
        String sql = "SELECT t.*, s.name as student_name, sub.name as subject_name, sc.name as school_name FROM TEST t " +
                     "JOIN STUDENT s ON t.STUDENT_NO = s.no " +
                     "JOIN SUBJECT sub ON t.SUBJECT_CD = sub.cd " +
                     "JOIN SCHOOL sc ON t.SCHOOL_CD = sc.cd " +
                     "WHERE t.STUDENT_NO = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", ""); // データベース接続
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentNo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Test test = new Test();

                Student student = new Student();
                student.setNo(rs.getString("STUDENT_NO"));
                student.setName(rs.getString("student_name"));

                Subject subject = new Subject();
                subject.setCd(rs.getString("SUBJECT_CD"));
                subject.setName(rs.getString("subject_name"));

                School school = new School();
                school.setCd(rs.getString("SCHOOL_CD"));
                school.setName(rs.getString("school_name"));

                test.setStudent(student);
                test.setClassNum(rs.getString("CLASS_NUM"));
                test.setSubject(subject);
                test.setSchool(school);
                test.setNo(rs.getInt("NO"));
                test.setPoint(rs.getInt("POINT"));

                tests.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("データベースエラー: " + e.getMessage());
        }
        return tests;
    }

    private List<Test> getTestsByCriteria(String entYear, String classNum, String subjectCd) {
        List<Test> tests = new ArrayList<>();
        String url = "jdbc:h2:~/exam"; // H2データベースのパス
        StringBuilder sql = new StringBuilder(
            "SELECT t.*, s.name as student_name, sub.name as subject_name, sc.name as school_name FROM TEST t " +
            "JOIN STUDENT s ON t.STUDENT_NO = s.no " +
            "JOIN SUBJECT sub ON t.SUBJECT_CD = sub.cd " +
            "JOIN SCHOOL sc ON t.SCHOOL_CD = sc.cd " +
            "WHERE 1=1"
        );

        if (entYear != null && !entYear.isEmpty()) {
            sql.append(" AND s.ent_year = ?");
        }
        if (classNum != null && !classNum.isEmpty()) {
            sql.append(" AND t.class_num = ?");
        }
        if (subjectCd != null && !subjectCd.isEmpty()) {
            sql.append(" AND t.subject_cd = ?");
        }

        try (Connection conn = DriverManager.getConnection(url, "sa", ""); // データベース接続
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            if (entYear != null && !entYear.isEmpty()) {
                stmt.setString(paramIndex++, entYear);
            }
            if (classNum != null && !classNum.isEmpty()) {
                stmt.setString(paramIndex++, classNum);
            }
            if (subjectCd != null && !subjectCd.isEmpty()) {
                stmt.setString(paramIndex++, subjectCd);
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Test test = new Test();

                Student student = new Student();
                student.setNo(rs.getString("STUDENT_NO"));
                student.setName(rs.getString("student_name"));

                Subject subject = new Subject();
                subject.setCd(rs.getString("SUBJECT_CD"));
                subject.setName(rs.getString("subject_name"));

                School school = new School();
                school.setCd(rs.getString("SCHOOL_CD"));
                school.setName(rs.getString("school_name"));

                test.setStudent(student);
                test.setClassNum(rs.getString("CLASS_NUM"));
                test.setSubject(subject);
                test.setSchool(school);
                test.setNo(rs.getInt("NO"));
                test.setPoint(rs.getInt("POINT"));

                tests.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("データベースエラー: " + e.getMessage());
        }
        return tests;
    }
}
//a