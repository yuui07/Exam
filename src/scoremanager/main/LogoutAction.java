package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class LogoutAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
//        // セッションを取得し、ログアウトする
//        HttpSession session = req.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
        // ログアウト後はログアウトJSPにフォワードする
        req.getRequestDispatcher("logout.jsp").forward(req, res);
    }
}
