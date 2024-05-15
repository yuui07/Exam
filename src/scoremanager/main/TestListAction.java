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

        String entYearStr = "";
        String classNum = ""; // 入力されたクラス番号
        String isAttendStr = "";
        String numStr = "";
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
        String subjectStr = ""; // 科目
        SubjectDao subDao = new SubjectDao();
        Subject subject = null; // 初期化を null に変更

        entYearStr = request.getParameter("f1");
        classNum = request.getParameter("f2");
        subjectStr = request.getParameter("f3");
        numStr = request.getParameter("f4");

        // subjectStr が null または空文字でないかを確認
        if (subjectStr != null && !subjectStr.trim().isEmpty()) {
            subject = subDao.get(subjectStr, teacher.getSchool());
        } else {
            errors.put("f3", "有効な科目を指定してください。"); // エラーメッセージを追加
        }

        // subject が null の場合のチェックを追加
        if (errors.isEmpty() && subject == null) {
            errors.put("f3", "指定された科目が見つかりません。"); // エラーメッセージを追加
        }

        if (entYearStr != null && !entYearStr.trim().isEmpty()) {
            try {
                entYear = Integer.parseInt(entYearStr);
            } catch (NumberFormatException e) {
                errors.put("f1", "有効な入学年度を指定してください。"); // エラーメッセージを追加
            }
        }

        if (numStr != null && !numStr.trim().isEmpty()) {
            try {
                num = Integer.parseInt(numStr);
            } catch (NumberFormatException e) {
                errors.put("f4", "有効な番号を指定してください。"); // エラーメッセージを追加
            }
        }

        if (!errors.isEmpty()) { // エラーチェック
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("test_list.jsp").forward(request, response);
            return;
        }

        isAttend = Boolean.parseBoolean(isAttendStr);

        // DBからデータ取得
        List<String> list = cNumDao.filter(teacher.getSchool());

        if (entYear != 0 && !classNum.equals("0")) {
            // 入学年度とクラス番号を指定
            students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
        } else if (entYear != 0 && classNum.equals("0")) {
            // 入学年度のみ指定
            students = sDao.filter(teacher.getSchool(), entYear, isAttend);
        } else if (entYear == 0 && (classNum == null || classNum.equals("0"))) {
            // 指定なしの場合
            // 全ユーザーのデータを取得
            students = sDao.filter(teacher.getSchool(), isAttend);
        } else {
            errors.put("f1", "クラスを指定する場合は入学年度も指定してください。");
            request.setAttribute("errors", errors);
            // 全学生情報を取得
            students = sDao.filter(teacher.getSchool(), isAttend);
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("test_list.jsp").forward(request, response);
            return;
        }


        List<Integer> numSet = new ArrayList<>();
		for (int i = 1;i<3;i++){
			numSet.add(i);
		}
        // リストを初期化
        List<Integer> entYearSet = new ArrayList<>();
        // 10年前から1年後までをリストに追加
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        // DBからデータ取得
        // ログインユーザーの学校コードをもとにクラス番号の一覧を取得
        List<Subject> subjects = subDao.filter(teacher.getSchool());
        List<Test> counts = tDao.filter(entYear, classNum, subject, num, teacher.getSchool());

        request.setAttribute("f3", subjects);
        request.setAttribute("f4", counts);

        request.getRequestDispatcher("test_list.jsp").forward(request, response);
    }
}
