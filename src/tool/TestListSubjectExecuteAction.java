package tool;

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

@WebServlet("/TestListSubjectExecuteAction")
public class TestListSubjectExecuteAction extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String year = request.getParameter("year");
        String classNum = request.getParameter("class");
        String subjectName = request.getParameter("subject");

        List<Student> students = getStudentsByYearClassAndSubject(year, classNum, subjectName);

        if (students.isEmpty()) {
            request.setAttribute("errorMessage", "指定された条件に一致する学生は見つかりませんでした。");
        } else {
            request.setAttribute("students", students);
        }

        request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);
    }

    private List<Student> getStudentsByYearClassAndSubject(String year, String classNum, String subjectName) {
        List<Student> students = new ArrayList<>();
        String url = "jdbc:h2:~/test"; // 例: H2データベースのパス
        String sql = "SELECT STUDENT_NO, POINT FROM TEST WHERE CLASS_NUM = ? AND SUBJECT_CD = ?";

        try (Connection conn = DriverManager.getConnection(url, "sa", ""); // ここでのユーザ名とパスワードは適宜変更してください
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, classNum);
            stmt.setString(2, subjectName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String studentNo = rs.getString("STUDENT_NO");
                int point = rs.getInt("POINT");
                // データベースに名前がない場合、ダミーの名前を使用
//                students.add(new Student(studentNo, "Unknown", Integer.toString(point)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("データベースエラー: " + e.getMessage());
        }
        return students;
    }
}
