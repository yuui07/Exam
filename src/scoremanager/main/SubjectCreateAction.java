package scoremanager.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import tool.Action;

public class SubjectCreateAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ユーザーのセッションを取得
        HttpSession session = request.getSession();
        
        // ユーザーがログインしているかを確認
        Teacher teacher = (Teacher) session.getAttribute("user");
        if (teacher == null) {
            // ログインしていない場合はログインページにリダイレクト
            response.sendRedirect("login.jsp");
            return;
        }
        
        // 科目登録画面にリダイレクト
        response.sendRedirect("subject_create.jsp");
    }
}
