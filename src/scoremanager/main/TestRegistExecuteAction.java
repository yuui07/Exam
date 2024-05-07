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
        String entYearStr;
        String classNum;
        String subject;
        int score;
        List<String> errors = new ArrayList<>();
        TestDao testDao = new TestDao();

        // リクエストパラメータ―の取得
        entYearStr = req.getParameter("f1");
        classNum = req.getParameter("f2");
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
        test.setentYearStr(entYearStr);
        test.setClassNum(classNum);
        test.setSubject(subject);
        test.setPoint(score);

        // データベースにテストデータを登録
        TestDao testDao = new TestDao();
        testDao.addTest(test);

        // 成績登録完了画面にリダイレクト
        res.sendRedirect("test_regist_done.jsp");
    }
}
