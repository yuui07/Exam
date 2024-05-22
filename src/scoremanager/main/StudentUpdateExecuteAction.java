package scoremanager.main;

import java.util.HashMap;
import java.util.List;
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
        HttpSession session = request.getSession(); // セッション情報を取得
        Teacher teacher = (Teacher) session.getAttribute("user");

        // パラメータの取得
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");
        String entYearStr = request.getParameter("entyear");
        boolean isAttend = "t".equals(request.getParameter("f5"));

        int entYear = 0;
        Map<String, String> errors = new HashMap<>(); // エラーメッセージ

        // 入学年度の変換
        try {
            entYear = Integer.parseInt(entYearStr);
        } catch (NumberFormatException e) {
            errors.put("entyear", "有効な入学年度を入力してください。");
        }

        // エラーチェック
        if (name.isEmpty()) {
            errors.put("name", "氏名を入力してください。");
        }

        if (classNum.isEmpty()) {
            errors.put("class_num", "クラスを入力してください。");
        }

        // エラーがある場合の処理
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("no", no);
            request.setAttribute("entyear", entYearStr);
            request.setAttribute("name", name);

            ClassNumDao cNumDao = new ClassNumDao();
            List<String> list = cNumDao.filter(teacher.getSchool());
            request.setAttribute("class_num", list);

            request.getRequestDispatcher("student_update.jsp").forward(request, response);
            return;
        }

        // 学生情報の更新
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setAttend(isAttend);
        student.setSchool(teacher.getSchool());

        StudentDao sDao = new StudentDao();
        sDao.save(student);

        // 更新後の処理
        request.getRequestDispatcher("student_update_done.jsp").forward(request, response);
    }
}
