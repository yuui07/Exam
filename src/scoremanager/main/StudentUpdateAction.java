package scoremanager.main;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class StudentUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // ローカル変数の宣言
        HttpSession session = req.getSession(); // セッション情報を取得
        Teacher teacher = (Teacher) session.getAttribute("user");

        // パラメータの取得
        String no = req.getParameter("no");
        String entYear = req.getParameter("entyear");
        String name = req.getParameter("name");

        // 現在の年を取得
        LocalDate todaysDate = LocalDate.now(); // LocalDateインスタンスを取得
        int year = todaysDate.getYear(); // 現在の年を取得
        ClassNumDao cNumDao = new ClassNumDao(); // クラス番号Daoをインスタンス化

        // クラス番号のリストを取得
        List<String> list = cNumDao.filter(teacher.getSchool());

        // リクエストにデータをセット
        req.setAttribute("class_num", list);
        req.setAttribute("no", no);
        req.setAttribute("entyear", entYear);
        req.setAttribute("name", name);

        // JSPへフォワード
        req.getRequestDispatcher("student_update.jsp").forward(req, res);
    }
}
