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

		Teacher teacher = (Teacher)session.getAttribute("user");
		String no="";
		String entYear="";

		String name = "";

		LocalDate todaysDate = LocalDate.now();	// LocalDateインスタンスを取得
		int year = todaysDate.getYear();	// 現在の年を取得
		ClassNumDao cNumDao = new ClassNumDao();	// クラス番号Daoをインスタンス化

		entYear=req.getParameter("entyear");

		no=req.getParameter("no");
		name = req.getParameter("name");


		//DBへデータ保存 5

		//なし

		//レスポンス値をセット 6
		System.out.println("aaaa");
		System.out.println(no);
		System.out.println(entYear);
		System.out.println(name);
		List<String> list = cNumDao.filter(teacher.getSchool());
		req.setAttribute("class_num", list);//↓↓↓  同じく  ↓↓↓

		req.setAttribute("no", no);	// リクエストにデータをセット

		req.setAttribute("entyear", entYear);//↓↓↓  同じく  ↓↓↓
		req.setAttribute("name", name);//↓↓↓  同じく  ↓↓↓a



		//JSPへフォワード 7

		req.getRequestDispatcher("student_update.jsp").forward(req, res); // 学生一覧まで画面遷移

	}

}
