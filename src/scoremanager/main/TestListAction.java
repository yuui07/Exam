package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

@WebServlet("/testlist")
public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String subjectStr = request.getParameter("f3");
        String studentNumber = request.getParameter("student_number");

        int entYear = 0;
        int num = 0;
        boolean dep = false;
        List<Student> students = null;
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();

        StudentDao sDao = new StudentDao();
        ClassNumDao cNumDao = new ClassNumDao();
        TestDao tDao = new TestDao();
        SubjectDao subDao = new SubjectDao();

        Map<String, String> errors = new HashMap<>();
        Subject subject = null;

        // 入学年度のバリデーション
        if (entYearStr != null && !entYearStr.trim().isEmpty()) {
            try {
                entYear = Integer.parseInt(entYearStr);
            } catch (NumberFormatException e) {
                errors.put("f1", "有効な入学年度を指定してください。");
            }
        }

        // 学生番号のバリデーション
        if (studentNumber != null && !studentNumber.trim().isEmpty()) {
            try {
                num = Integer.parseInt(studentNumber);
            } catch (NumberFormatException e) {
                errors.put("student_number", "有効な学生番号を指定してください。");
            }
        }

        // 科目のバリデーション
        if (subjectStr != null && !subjectStr.trim().isEmpty() && !subjectStr.equals("0")) {
            subject = subDao.get(subjectStr, teacher.getSchool());
            if (subject == null) {
                errors.put("f3", "指定された科目が見つかりません。");
            }
        }

        // エラーチェック
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("test_list.jsp").forward(request, response);
            return;
        }

        // クラス番号のリスト取得
        List<String> classList = cNumDao.filter(teacher.getSchool());
        List<Subject> subjectList = subDao.filter(teacher.getSchool());

        // 学生リストの取得
        if (entYear != 0 && classNum != null && !classNum.equals("0")) {
            students = sDao.filter(teacher.getSchool(), entYear, classNum, true);
        } else if (entYear != 0) {
            students = sDao.filter(teacher.getSchool(), entYear, true);
        } else if (classNum == null || classNum.equals("0")) {
            students = sDao.filter(teacher.getSchool(), true);
        } else {
            errors.put("f1", "クラスを指定する場合は入学年度も指定してください。");
            request.setAttribute("errors", errors);
            students = sDao.filter(teacher.getSchool(), true);
        }

        // 入学年度リストの初期化
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        // リクエスト属性の設定
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subjectStr);
        request.setAttribute("student_number", studentNumber);
        request.setAttribute("students", students);
        request.setAttribute("class_num_set", classList);
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("subject_set", subjectList);
        request.setAttribute("errors", errors);

        // フォワード
        request.getRequestDispatcher("test_list.jsp").forward(request, response);
    }
}
