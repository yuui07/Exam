package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession(); // セッション
		Teacher teacher =(Teacher)session.getAttribute("user");

		LocalDate todaysDate = LocalDate.now(); //LocalDateインスタンス取得
		int year =todaysDate.getYear(); // 現在の年を取得
		ClassNumDao cNumDao = new ClassNumDao(); // クラス番号Dao
		SubjectDao subjectDao = new SubjectDao(); //科目Dao

		List<String> list=cNumDao.filter(teacher.getSchool());
		List<Subject> subjectList=subjectDao.filter(teacher.getSchool());

		List<Integer> numSet= new ArrayList<>();
		for (int i = 1; i < 101; i++){
			numSet.add(i);
		}
		// リストを初期化
		List<Integer> entYearSet=new ArrayList<>();
		// 10年前から1年後までの年をリストに追加
		for (int i = year-10;i<year+1;i++){
			entYearSet.add(i);
		}

		// データをリクエストにセット
		req.setAttribute("class_num_set", list);
		req.setAttribute("ent_year_set", entYearSet);
		req.setAttribute("subject_set", subjectList);
		req.setAttribute("num_set", numSet);

//		既に何らかが入力されている場合にそれに応じた処理を行う
		try{
				TestRequestData(req, res);
		} catch (NullPointerException e ){
			req.getRequestDispatcher("test_regist.jsp").forward(req, res);
		} catch (NumberFormatException nume){
			req.getRequestDispatcher("test_regist.jsp").forward(req, res);
		}
	}

	private void TestRequestData(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession(); // セッション
		Teacher teacher =(Teacher)session.getAttribute("user");

		String entYearStr ="";
		String classNum ="";
		String subjectName ="";
		String numOfTime="";
		int entYear=0;
		int num=0;
		boolean deployment = false;
		List<Test> test = null;
		Student students = null;
		Subject subject = new Subject();
		TestDao testDao = new TestDao();
		SubjectDao subjectDao = new SubjectDao();
		Map<String,String> errors = new HashMap<>(); // エラーメッセージ

//		入力値の確認
		// リクエストパラメータの取得
		entYearStr=req.getParameter("f1");
		classNum=req.getParameter("f2");
		subjectName=req.getParameter("f3");
		numOfTime=req.getParameter("f4");

//		入力値の型変換
		entYear=Integer.parseInt(entYearStr);
		num=Integer.parseInt(numOfTime);
		subject=subjectDao.get(subjectName, teacher.getSchool());

		if (entYearStr != null && !classNum.equals("0") && subject != null && !numOfTime.equals("0")){
		// 成績管理一覧で表示するために必要なデータを取得
			test = testDao.filter(entYear, classNum, subject, num, teacher.getSchool());//直し
			students = ((Test) test).getStudent();
		// 値セット
			req.setAttribute("test_result", test);
			req.setAttribute("subject_name", subject);
			req.setAttribute("test_no", num);
			req.setAttribute("students", students);
			deployment = true;
		}
		else {
//		入学年度、クラス、科目、回数のいずれかが未入力の場合[入学年度とクラスと科目と回数を選択してください]と表示
			errors.put("all", "入学年度とクラスと科目と回数を選択してください");
			req.setAttribute("errors", errors);
		}
		req.setAttribute("dep", deployment);
		req.getRequestDispatcher("test_regist.jsp").forward(req, res);
	}
}