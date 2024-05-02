package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションを取得し、ログアウトする
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        // ログアウト後はログインページにリダイレクトする
        res.sendRedirect("login.jsp");
    }
}
