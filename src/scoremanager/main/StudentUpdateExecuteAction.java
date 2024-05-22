package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(); // セッション
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 入力フィールドの値を取得
        String entYearStr = request.getParameter("entyear");
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");
        boolean isAttend = "true".equals(request.getParameter("is_attend"));

        int entYear = 0; // 入学年度
        Map<String, String> errors = new HashMap<>(); // エラーメッセージ

        // 入力チェック
        if (no == null || no.trim().isEmpty()) {
            errors.put("f2", "学生番号を入力してください");
        }

        if (name == null || name.trim().isEmpty()) {
            errors.put("f3", "氏名を入力してください");
        }

        if (entYearStr == null || entYearStr.trim().isEmpty()) {
            errors.put("f1", "入学年度を選択してください");
        } else {
            try {
                entYear = Integer.parseInt(entYearStr);
            } catch (NumberFormatException e) {
                errors.put("f1", "有効な入学年度を入力してください");
            }
        }

        if (classNum == null || classNum.trim().isEmpty()) {
            errors.put("f4", "クラス番号を入力してください");
        }

        // エラーがある場合は入力画面に戻る
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("no", no);
            request.setAttribute("name", name);
            request.setAttribute("class_num", classNum);
            request.setAttribute("entyear", entYearStr);
            ClassNumDao cNumDao = new ClassNumDao(); // クラス番号Daoをインスタンス化
            request.setAttribute("class_num_set", cNumDao.filter(teacher.getSchool()));
            request.getRequestDispatcher("StudentUpdate.action").forward(request, response);
            return;
        }

        // 新しい学生情報の作成
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setAttend(isAttend);
        student.setSchool(teacher.getSchool()); // 学校情報をセット

        // 学生情報をデータベースに保存
        StudentDao sDao = new StudentDao();
        sDao.save(student);
        request.getRequestDispatcher("student_update_done.jsp").forward(request, response);
    }
}
