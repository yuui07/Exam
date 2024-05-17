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
import bean.Test;
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

        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String subjectStr = request.getParameter("f3");
        String numStr = request.getParameter("f4");
        String isAttendStr = request.getParameter("isAttend");

        int entYear = 0;
        int num = 0;
        boolean isAttend = false;
        List<Student> students = null;
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        StudentDao sDao = new StudentDao();
        ClassNumDao cNumDao = new ClassNumDao();
        TestDao tDao = new TestDao();
        Map<String, String> errors = new HashMap<>();
        SubjectDao subDao = new SubjectDao();
        Subject subject = null;

        // パラメータのバリデーションとエラーチェック
        if (subjectStr != null && !subjectStr.trim().isEmpty()) {
            subject = subDao.get(subjectStr, teacher.getSchool());
            if (subject == null) {
                errors.put("f3", "指定された科目が見つかりません。");
            }
        } else {
            errors.put("f3", "有効な科目を指定してください。");
        }

        if (entYearStr != null && !entYearStr.trim().isEmpty()) {
            try {
                entYear = Integer.parseInt(entYearStr);
            } catch (NumberFormatException e) {
                errors.put("f1", "有効な入学年度を指定してください。");
            }
        }

        if (numStr != null && !numStr.trim().isEmpty()) {
            try {
                num = Integer.parseInt(numStr);
            } catch (NumberFormatException e) {
                errors.put("f4", "有効な番号を指定してください。");
            }
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("test_list.jsp").forward(request, response);
            return;
        }

        // パラメータの処理
        isAttend = Boolean.parseBoolean(isAttendStr);

        // データベースからクラス番号の一覧を取得
        List<String> classNumList = cNumDao.filter(teacher.getSchool());

        // 学生データの取得
        if (entYear != 0 && !classNum.equals("0")) {
            students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
        } else if (entYear != 0 && classNum.equals("0")) {
            students = sDao.filter(teacher.getSchool(), entYear, isAttend);
        } else if (entYear == 0 && (classNum == null || classNum.equals("0"))) {
            students = sDao.filter(teacher.getSchool(), isAttend);
        } else {
            errors.put("f1", "クラスを指定する場合は入学年度も指定してください。");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("test_list.jsp").forward(request, response);
            return;
        }

        // 他のエラーチェック
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("test_list.jsp").forward(request, response);
            return;
        }

        // 受験回数のリストを作成
        List<Integer> numSet = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            numSet.add(i);
        }

        // 入学年度のリストを作成
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        // 科目とテストデータの取得
        List<Subject> subjects = subDao.filter(teacher.getSchool());
        List<Test> tests = tDao.filter(entYear, classNum, subject, num, teacher.getSchool());
        if (tests == null || tests.isEmpty()) {
            errors.put("testError", "指定された条件でテストデータが見つかりませんでした。");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("test_list.jsp").forward(request, response);
            return;
        }

        // リクエスト属性にセット
        request.setAttribute("tests", tests);
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subjects);
        request.setAttribute("f4", numSet);

        // JSPへフォワード
        request.getRequestDispatcher("test_list.jsp").forward(request, response);
    }
}
