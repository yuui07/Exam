package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Test;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ローカル変数の宣言
        String url;
        String enrollmentYear;
        String className;
        String subject;
        int score;
        List<String> errors = new ArrayList<>();
        TestDao testDao = new TestDao();

        // リクエストパラメータ―の取得
        enrollmentYear = req.getParameter("enrollmentYear");
        className = req.getParameter("className");
        subject = req.getParameter("subject");
        String scoreStr = req.getParameter("score");

        // スコアが数値かつ0から100の範囲内かチェック
        try {
            score = Integer.parseInt(scoreStr);
            if (score < 0 || score > 100) {
                errors.add("0から100の範囲で入力してください");
            }
        } catch (NumberFormatException e) {
            errors.add("スコアは数値で入力してください");
        }

        // エラーメッセージがある場合は入力画面にフォワード
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("input.jsp").forward(req, res);
            return;
        }

        // テストデータの作成
        Test test = new Test();
        test.setEntYear(EntYear);
        test.setClassNum(className);
        test.setSubject(subject);
        test.setScore(score);

        // データベースにテストデータを登録
        testDao.addTest(test);

        // 成績登録完了画面にリダイレクト
        url = "test_regist_done.jsp";
        res.sendRedirect(url);
    }
}
