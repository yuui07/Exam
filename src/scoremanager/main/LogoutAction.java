package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import tool.Action;

public class LogoutAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションを取得し、ログアウトする
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher)session.getAttribute("user");

        //認証済みのフラグをfalseにする
        teacher.setAuthenticated(false);
        //セッション属性の全削除
        session.invalidate();
        // ログアウト後はログアウトJSPにフォワードする
        req.getRequestDispatcher("logout.jsp").forward(req, res);
    }
}
