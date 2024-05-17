package scoremanager.main;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;
public class StudentUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//TODO 自動生成されたメソッド·スタブ
		HttpSession session=request.getSession();//セッション
		Teacher teacher=(Teacher)session.getAttribute("user");

		String entYearStr="";//入力された入学年度
		String no="";
		String name="";
		String classNum="";//入力されたクラス番号
		int entYear=0;//入学年度
		boolean isAttend=false;//在学フラグ

		//List<Student>students=null;//学生リスト

		LocalDate todaysDate=LocalDate.now();//LocolDateインスタンスを取得
		int year=todaysDate.getYear();//現在の年を取得
		StudentDao sDao=new StudentDao();//学生dao
		Map<String, String>errors=new HashMap<>();//エラーメッセージ


		entYearStr=request.getParameter("entyear");//入学年度
		no=request.getParameter("no");//学生番号
		name=request.getParameter("name");//氏名
		classNum=request.getParameter("class_num");//クラス
		isAttend = "t".equals(request.getParameter("f5"));
		if (entYearStr!=null){
			//数値に変換
			entYear=Integer.parseInt(entYearStr);
		}
		System.out.println("---------------------");
		System.out.println(entYear);
		System.out.println(no);
		System.out.println(name);
		System.out.println(classNum);
		System.out.println("---------------------");
		if (entYearStr!=null){
			//数値に変換
			entYear=Integer.parseInt(entYearStr);
		}

		if (name.isEmpty()){

			errors.put("name", "氏名を選択してください");
			request.setAttribute("no", no);
			request.setAttribute("entyear", entYearStr);
			request.setAttribute("name", name);
			request.setAttribute("errors", errors);
			ClassNumDao cNumDao = new ClassNumDao();	// クラス番号Daoをインスタンス化
			List<String> list = cNumDao.filter(teacher.getSchool());
			request.setAttribute("class_num", list);//↓↓↓  同じく  ↓↓↓
			request.getRequestDispatcher("student_update.jsp").forward(request, response);

		}else if (classNum.equals("class_num")){

			errors.put("class_num", "クラスを入力してください");
			request.setAttribute("no", no);
			request.setAttribute("year", entYearStr);
			request.setAttribute("name", name);
			ClassNumDao cNumDao = new ClassNumDao();	// クラス番号Daoをインスタンス化
			List<String> list = cNumDao.filter(teacher.getSchool());
			request.setAttribute("class_num", list);//↓↓↓  同じく  ↓↓↓
			request.getRequestDispatcher("student_update.jsp").forward(request, response);

		}else {
			ClassNumDao cNumDao = new ClassNumDao();
			List<String> list = cNumDao.filter(teacher.getSchool());
			request.setAttribute("no", no);
			request.setAttribute("year", entYearStr);
			request.setAttribute("name", name);
			request.setAttribute("class_num", list);
		    request.getRequestDispatcher("student_update_done.jsp").forward(request, response);

		}

	}
}

		//在学フラグが送信されていた場合
//		if (isAttendStr!=null){
//			//在学フラグを立てる
//			isAttend=true;
//			//リクエストに在学フラグをセット
//			request.setAttribute("f3",isAttendStr);
//		}
		//リクエストに学生リストをセット
//		request.setAttribute("students",students);
//		//リクエストにデータをセット
//		request.setAttribute("class_num_set",list);
//		request.setAttribute("ent_year_set",entYearSet);
//
//		//JSPにフォワード 7
//		request.getRequestDispatcher("student_list.jsp").forward(request,response);


