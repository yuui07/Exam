package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject; // Subjectクラスをインポート
import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String classNum = req.getParameter("f2");
        String subjectStr = req.getParameter("subject");
        String scoreStr = req.getParameter("score");
        List<String> errors = new ArrayList<>();

        int score = 0;
        try {
            score = Integer.parseInt(scoreStr);
            if (score < 0 || score > 100) {
                errors.add("0から100の範囲で入力してください");
            }
        } catch (NumberFormatException e) {
            errors.add("スコアは数値で入力してください");
        }

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("input.jsp").forward(req, res);
            return;
        }

        Test test = new Test();
        test.setClassNum(classNum);

        Subject subject = new Subject();
        subject.setName(subjectStr); // SubjectクラスのsetNameが実装されていることが必要
        test.setSubject(subject);
        test.setPoint(score);

        // TestDaoを使用してデータベースにテストデータを保存
        TestDao testDao = new TestDao();
        List<Test> tests = new ArrayList<>();
        tests.add(test);
        if (testDao.save(tests)) {
            res.sendRedirect("test_regist_done.jsp");
        } else {
            errors.add("データベースへの登録に失敗しました。");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("input.jsp").forward(req, res);
        }
    }
}
//a
