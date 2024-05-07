package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		String cd = request.getParameter("cd");

		List<Subject> subjects=null;//科目リスト
		SubjectDao sDao = new SubjectDao();//科目Dao
		School school = teacher.getSchool();


		Subject subject = sDao.get(cd, school);
		System.out.println(cd);


		request.setAttribute("subject", subject);

		request.getRequestDispatcher("subject_delete.jsp").forward(request, response);
	}
}
