package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		List<Subject> subjects=null;//科目リスト
		SubjectDao sDao = new SubjectDao();//科目Dao


		subjects = sDao.filter(teacher.getSchool());


		request.setAttribute("subjects", subjects);

		request.getRequestDispatcher("subject_list.jsp").forward(request, response);

	}
}