package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        setRequestData(request, response);
        request.getRequestDispatcher("student_list.jsp").forward(request, response);
    }

    public void setRequestData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String entYearStr = request.getParameter("f1"); // 入学年度の文字列
        String classNumStr = request.getParameter("f2"); // クラス番号の文字列
        int entYear = 0; // 入学年度の数値
        int classNum = 0; // クラス番号の数値

        // 文字列を数値に変換して格納
        if (entYearStr != null && !entYearStr.isEmpty()) {
            entYear = Integer.parseInt(entYearStr);
        }
        if (classNumStr != null && !classNumStr.isEmpty()) {
            classNum = Integer.parseInt(classNumStr);
        }

        // DAO初期化
        StudentDao studentDao = new StudentDao();
        ClassNumDao classNumDao = new ClassNumDao();

        // 学年一覧取得
        List<Integer> entYearList = new ArrayList<>();

        // クラス番号一覧取得
        List<String> classNumList = classNumDao.filter(null);

        // リクエスト属性にセット
        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("ent_year_set", entYearList);
        request.setAttribute("class_num_set", classNumList);
    }
}
//a