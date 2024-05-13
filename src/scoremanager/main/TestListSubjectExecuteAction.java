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

import bean.Student;
import bean.Test;

@WebServlet("/TestListSubjectExecuteAction")
public class TestListSubjectExecuteAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int entYear = Integer.parseInt(request.getParameter("entYear"));
        String classNum = request.getParameter("classNum");
        String subjectCd = request.getParameter("subjectCd");

        List<Test> tests = getTestsByDetails(entYear, classNum, subjectCd);

        if (tests.isEmpty()) {
            request.setAttribute("errorMessage", "指定された条件に一致する成績データは見つかりませんでした。");
        } else {
            request.setAttribute("tests", tests);
        }

        request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);
    }

    private List<Test> getTestsByDetails(int entYear, String classNum, String subjectCd) {
        List<Test> tests = new ArrayList<>();
        String url = "jdbc:h2:~/exam"; // H2データベースのパス
        String sql = "SELECT t.STUDENT_NO, t.POINT, t.NO, s.NAME " +
                     "FROM TEST t JOIN STUDENT s ON t.STUDENT_NO = s.NO " +
                     "WHERE s.ENT_YEAR = ? AND t.CLASS_NUM = ? AND t.SUBJECT_CD = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", ""); // データベース接続
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, entYear);
            stmt.setString(2, classNum);
            stmt.setString(3, subjectCd);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Test test = new Test();
                Student student = new Student();
                student.setNo(rs.getString("STUDENT_NO"));
                student.setName(rs.getString("NAME"));
                test.setStudent(student);
                test.setPoint(rs.getInt("POINT"));
                test.setNo(rs.getInt("NO"));
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