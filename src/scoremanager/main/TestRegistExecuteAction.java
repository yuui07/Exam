package scoremanager.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDao;

@WebServlet("/testregistexecute")
public class TestRegistExecuteAction extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        TestDao testDao = new TestDao();

        try {
            // 取得するパラメータ
            String[] studentNos = req.getParameterValues("student_no");
            String subjectCd = req.getParameter("subject_cd");
            String schoolCd = teacher.getSchool().getCd();
            String testNoStr = req.getParameter("test_no");

            if (studentNos != null && subjectCd != null && testNoStr != null) {
                int testNo = Integer.parseInt(testNoStr);
                List<Test> tests = new ArrayList<>();

                for (String studentNo : studentNos) {
                    String pointStr = req.getParameter("point_" + studentNo);
                    if (pointStr != null) {
                        int point = Integer.parseInt(pointStr);
                        Test test = new Test();
                        Student student = new Student();
                        student.setNo(studentNo);
                        test.setStudent(student);
                        test.setNo(testNo);
                        test.setPoint(point);
                        Subject subject = new Subject();
                        subject.setCd(subjectCd);
                        test.setSubject(subject);
                        test.setSchool(teacher.getSchool());
                        tests.add(test);
                    }
                }

                // テスト結果を保存
                if (testDao.save(tests)) {
                    res.sendRedirect(req.getContextPath() + "/scoremanager/main/test_regist_done.jsp");
                    return;
                } else {
                    req.setAttribute("message", "保存に失敗しました。");
                }
            } else {
                req.setAttribute("message", "必要な情報が不足しています。");
            }

            // 保存に失敗した場合、元の画面に戻る
            req.getRequestDispatcher("test_regist.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace(); // 例外をログに出力
            throw new ServletException("サーブレットの実行中にエラーが発生しました", e);
        }
    }
}
