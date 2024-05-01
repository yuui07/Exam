package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ローカル変数の宣言
        String url = "";
        String entYear = "";
        String className = "";
        String subject = "";
        TestDao testDao = new TestDao();
        List<Test> testList = null;

        // リクエストパラメータ―の取得
        entYear = req.getParameter("enrollmentYear");
        className = req.getParameter("className");
        subject = req.getParameter("subject");

        // 入学年度、クラス、科目のいずれかが未入力の場合
        if (entYear == null || className == null || subject == null ||
            entYear.isEmpty() || className.isEmpty() || subject.isEmpty()) {
            // エラーメッセージをセット
            List<String> errors = new ArrayList<>();
            errors.add("入学年度とクラスと科目を選択してください");
            req.setAttribute("errors", errors);

            // フォワード
            url = "error.jsp";
            req.getRequestDispatcher(url).forward(req, res);
            return;
        }

        // 成績データの取得
        testList = testDao.getTestList(entYear, className, subject);

        // 成績データをリクエストスコープにセット
        req.setAttribute("testList", testList);

        // フォワード先のURLをセット
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}
