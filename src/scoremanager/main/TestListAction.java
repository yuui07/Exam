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
        Subject school = (Subject)session.getAttribute("user");

        String classNum = ""; // 入力されたクラス番号-
        String subjectStr = ""; //科目＊
        ClassNumDao cNumDao = new ClassNumDao(); // クラス番号Daoを初期化
        SubjectDao subjectDao = new SubjectDao();

        classNum = request.getParameter("f2");
        subjectStr = request.getParameter("");


        // DBからデータ取得 3
        // ログインユーザーの学校コードをもとにクラス番号の一覧を取得
        List<String> list = cNumDao.filter(teacher.getSchool());
        List<String> list = subjectDao.filter(school.getCd());


        request.setAttribute("f2", classNum);
        request.getAttribute("",subjectStr);


        request.setAttribute("class_num_set", list);
        request.setAttribute("subject_set",list );


        request.getRequestDispatcher("test_list.jsp").forward(request, response);

    }
}
