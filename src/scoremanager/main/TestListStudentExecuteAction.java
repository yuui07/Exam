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

@WebServlet("/TestListStudentExecuteAction")
public class TestListStudentExecuteAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentNo = request.getParameter("studentNo");

        List<Test> tests = getTestsByStudentNo(studentNo);

        if (tests.isEmpty()) {
            request.setAttribute("errorMessage", "指定された学生番号の成績データは見つかりませんでした。");
        } else {
            request.setAttribute("tests", tests);
        }

        request.getRequestDispatcher("test_list_student.jsp").forward(request, response);
    }

    private List<Test> getTestsByStudentNo(String studentNo) {
        List<Test> tests = new ArrayList<>();
        String url = "jdbc:h2:~/exam"; // H2データベースのパス
        String sql = "SELECT * FROM TEST WHERE STUDENT_NO = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", ""); // データベース接続
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentNo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Test test = new Test();
                Student student = new Student();
                student.setNo(rs.getString("STUDENT_NO"));
                test.setStudent(student);
                test.setClassNum(rs.getString("CLASS_NUM"));
                test.setNo(rs.getInt("NO"));
                test.setPoint(rs.getInt("POINT"));
                // SubjectとSchoolの設定も必要であればここで行う
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