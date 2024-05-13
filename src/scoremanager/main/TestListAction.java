package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String classNum = ""; // 入力されたクラス番号
        String subjectStr = ""; // 科目
        ClassNumDao cNumDao = new ClassNumDao(); // クラス番号Daoを初期化
        SubjectDao subjectDao = new SubjectDao();

        classNum = request.getParameter("f2");
        subjectStr = request.getParameter("subject");

        // DBからデータ取得
        // ログインユーザーの学校コードをもとにクラス番号の一覧を取得
        List<String> list = cNumDao.filter(teacher.getSchool());
        List<Subject> subjects = subjectDao.filter(teacher.getSchool());

        request.setAttribute("classNum", classNum);
        request.setAttribute("subjects", subjects);

        request.getRequestDispatcher("test_list.jsp").forward(request, response);
    }
}

