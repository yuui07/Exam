// SubjectListAction.java
package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        SubjectDao subjectDao = new SubjectDao();
        Map<String, String> errors = new HashMap<>();
        List<Subject> subjects = null;

        // Get data from the database
        subjects = subjectDao.getAllSubjects(); // Assuming you have a method to get all subjects from the database

        // Set attributes and forward to the JSP page
        request.setAttribute("subjects", subjects);
        request.getRequestDispatcher("subject_list.jsp").forward(request, response);
    }
}
