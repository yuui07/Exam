package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		String cd=request.getParameter("cd");

		SubjectDao subjectDao = new SubjectDao();

		School school = teacher.getSchool();
		Subject subject = new Subject();
		subject.setCd(cd);
		subject.setSchool(school);

		boolean isDeleted = subjectDao.delete(subject);

		if (isDeleted) {

            // 科目が削除された場合、削除完了ページにリダイレクト
            response.sendRedirect("subject_delete_done.jsp");
        } else {
            // 科目が削除されなかった場合、エラーページにリダイレクトまたはエラーメッセージをセットして適切な処理を行う
            request.setAttribute("errorMessage", "科目の削除中にエラーが発生しました。");
            request.getRequestDispatcher("error_page.jsp").forward(request, response);
        }

	}
}