package scoremanager.main;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(); // セッション
        Teacher teacher = (Teacher) session.getAttribute("user");

        String entYearStr = ""; // 入力された入学年度
        String no = ""; // 入力された学生番号
        String name = ""; // 入力された氏名
        String classNum = ""; // 入力されたクラス番号
        int entYear = 0; // 入学年度

        LocalDate todaysDate = LocalDate.now(); // LocalDateインスタンスを取得
        int year = todaysDate.getYear(); // 現在の年を取得
        StudentDao sDao = new StudentDao(); // 学生dao
        Map<String, String> errors = new HashMap<>(); // エラーメッセージ

        // パラメータの取得
        entYearStr = request.getParameter("f1");
        no = request.getParameter("f2");
        name = request.getParameter("f3");
        classNum = request.getParameter("f4");

        // デバッグ出力
        System.out.println(entYearStr);
        System.out.println(no);
        System.out.println(name);
        System.out.println(classNum);

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
            request.getRequestDispatcher("StudentCreate.action").forward(request, response);
            return;
        }

        // 学生番号の重複チェック
        if (sDao.get(no) != null) {
            errors.put("f2", "学生番号が重複しています");
            request.setAttribute("errors", errors);
            request.setAttribute("no", no);
            request.setAttribute("name", name);
            request.setAttribute("class_num", classNum);
            request.getRequestDispatcher("StudentCreate.action").forward(request, response);
            return;
        }

        // 新しい学生情報の作成
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setAttend(true); // 在学フラグをセット
        student.setSchool(teacher.getSchool()); // 学校情報をセット

        // 学生情報をデータベースに保存
        sDao.save(student);
        request.getRequestDispatcher("student_create_done.jsp").forward(request, response);
    }
}
