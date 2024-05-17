package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

<<<<<<< HEAD
        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String subjectStr = request.getParameter("f3");
        String numStr = request.getParameter("f4");
        String isAttendStr = request.getParameter("f5");

=======
        String entYearStr = "";
        String classNum = ""; // 入力されたクラス番号
        String isAttendStr = "";
        String numStr = "";
>>>>>>> branch 'master' of https://github.com/yuui07/Exam.git
        int entYear = 0;
        int num = 0;
        boolean isAttend = false;
        List<Student> students = null;
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();

        StudentDao sDao = new StudentDao();
        ClassNumDao cNumDao = new ClassNumDao();
        TestDao tDao = new TestDao();
        SubjectDao subDao = new SubjectDao();

<<<<<<< HEAD
        Map<String, String> errors = new HashMap<>();
        Subject subject = null;
=======
        entYearStr = request.getParameter("f1");
        classNum = request.getParameter("f2");
        subjectStr = request.getParameter("f3");
        numStr = request.getParameter("f4");
>>>>>>> branch 'master' of https://github.com/yuui07/Exam.git

        // 入学年度のバリデーション
        if (entYearStr != null && !entYearStr.trim().isEmpty()) {
            try {
                entYear = Integer.parseInt(entYearStr);
            } catch (NumberFormatException e) {
                errors.put("f1", "有効な入学年度を指定してください。");
            }
        }

        // 番号のバリデーション
        if (numStr != null && !numStr.trim().isEmpty()) {
            try {
                num = Integer.parseInt(numStr);
            } catch (NumberFormatException e) {
                errors.put("f4", "有効な番号を指定してください。");
            }
        }

<<<<<<< HEAD
        // 在学フラグのバリデーション
        if (isAttendStr != null && !isAttendStr.trim().isEmpty()) {
            isAttend = Boolean.parseBoolean(isAttendStr);
=======
        if (!errors.isEmpty()) { // エラーチェック
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("test_regist.jsp").forward(request, response);
            return;
>>>>>>> branch 'master' of https://github.com/yuui07/Exam.git
        }

        // 科目のバリデーション
        if (subjectStr != null && !subjectStr.trim().isEmpty()) {
            subject = subDao.get(subjectStr, teacher.getSchool());
            if (subject == null) {
                errors.put("f3", "指定された科目が見つかりません。");
            }
        } else {
            errors.put("f3", "有効な科目を指定してください。");
        }

        // エラーチェック
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("test_regist.jsp").forward(request, response);
            return;
        }

        // クラス番号のリスト取得
        List<String> classList = cNumDao.filter(teacher.getSchool());
        List<Subject> subjectList = subDao.filter(teacher.getSchool());

        // 学生リストの取得
        if (entYear != 0 && classNum != null && !classNum.equals("0")) {
            students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
        } else if (entYear != 0) {
            students = sDao.filter(teacher.getSchool(), entYear, isAttend);
        } else if (classNum == null || classNum.equals("0")) {
            students = sDao.filter(teacher.getSchool(), isAttend);
        } else {
            errors.put("f1", "クラスを指定する場合は入学年度も指定してください。");
            request.setAttribute("errors", errors);
            students = sDao.filter(teacher.getSchool(), isAttend);
        }

        // 入学年度リストの初期化
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        // 番号リストの初期化
        List<Integer> numSet = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            numSet.add(i);
        }

        // リクエスト属性の設定
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subjectList);
        request.setAttribute("f4", num);
        request.setAttribute("f5", isAttend);
        request.setAttribute("students", students);
        request.setAttribute("class_num_set", classList);
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("num_set", numSet);

<<<<<<< HEAD
        // フォワード
        request.getRequestDispatcher("test_list.jsp").forward(request, response);
=======
        request.setAttribute("f3", subjects);
        request.setAttribute("f4", counts);

        request.getRequestDispatcher("test_regist.jsp").forward(request, response);
>>>>>>> branch 'master' of https://github.com/yuui07/Exam.git
    }
}
