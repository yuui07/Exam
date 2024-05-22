package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(); // セッション情報を取得
        Teacher teacher = (Teacher) session.getAttribute("user");


		String no="";
		String name="";
		String classNum="";//入力されたクラス番号
		String entYearStr="";//入学年度
		boolean isAttend=false;//在学フラグ

        // パラメータの取得
        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String classNum = request.getParameter("class_num");
        String entYearStr = request.getParameter("entyear");
        boolean isAttend = "t".equals(request.getParameter("f5"));


        int entYear = 0;
        Map<String, String> errors = new HashMap<>(); // エラーメッセージ

		StudentDao sDao=new StudentDao();//学生dao
		Map<String, String>errors=new HashMap<>();//エラーメッセージ

        // 入学年度の変換
        try {
            entYear = Integer.parseInt(entYearStr);
        } catch (NumberFormatException e) {
            errors.put("entyear", "有効な入学年度を入力してください。");
        }

//入学年度
		no=request.getParameter("no");//学生番号
		name=request.getParameter("name");//氏名
		classNum=request.getParameter("class_num");//クラス
		entYearStr=request.getParameter(entYearStr);

        // エラーチェック
        if (name.isEmpty()) {
            errors.put("name", "氏名を入力してください。");
        }

		Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYearStr);
        student.setClassNum(classNum);
        student.setAttend(isAttend);
        student.setSchool(teacher.getSchool()); // 学校情報をセット
	    request.getRequestDispatcher("student_update_done.jsp").forward(request, response);


	    sDao.save(student);



		System.out.println("---------------------");
		System.out.println(entYearStr);
		System.out.println(no);
		System.out.println(name);
		System.out.println(classNum);
		System.out.println("---------------------");

        if (classNum.isEmpty()) {
            errors.put("class_num", "クラスを入力してください。");
        }


        // エラーがある場合の処理
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("no", no);
            request.setAttribute("entyear", entYearStr);
            request.setAttribute("name", name);

            ClassNumDao cNumDao = new ClassNumDao();
            List<String> list = cNumDao.filter(teacher.getSchool());
            request.setAttribute("class_num", list);

			errors.put("name", "氏名を選択してください");
			request.setAttribute("no", no);
			request.setAttribute("entyear", entYearStr);
			request.setAttribute("name", name);
			request.setAttribute("errors", errors);
			ClassNumDao cNumDao = new ClassNumDao();	// クラス番号Daoをインスタンス化
			List<String> list = cNumDao.filter(teacher.getSchool());
			request.setAttribute("class_num", list);//↓↓↓  同じく  ↓↓↓
			request.getRequestDispatcher("student_update.jsp").forward(request, response);

            request.getRequestDispatcher("student_update.jsp").forward(request, response);
            return;
        }


        // 学生情報の更新
        Student student = new Student();
        student.setNo(no);
        student.setName(name);
        student.setEntYear(entYear);
        student.setClassNum(classNum);
        student.setAttend(isAttend);
        student.setSchool(teacher.getSchool());


			errors.put("class_num", "クラスを入力してください");
			request.setAttribute("no", no);
			request.setAttribute("year", entYearStr);
			request.setAttribute("name", name);
			ClassNumDao cNumDao = new ClassNumDao();	// クラス番号Daoをインスタンス化
			List<String> list = cNumDao.filter(teacher.getSchool());
			request.setAttribute("class_num", list);//↓↓↓  同じく  ↓↓↓

        StudentDao sDao = new StudentDao();
        sDao.save(student);

        // 更新後の処理
        request.getRequestDispatcher("student_update_done.jsp").forward(request, response);
    }
}
