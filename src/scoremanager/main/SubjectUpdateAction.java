package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");


        Subject subject = new Subject();
        request.setAttribute("cd", cd);
		request.setAttribute("name", name);
		System.out.println(name);


        SubjectDao subjectDao = new SubjectDao();

        request.getRequestDispatcher("subject_update.jsp").forward(request, response);
    }
}
