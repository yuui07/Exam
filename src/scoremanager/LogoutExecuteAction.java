package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class LogoutExecuteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String url = "";
		{
			url = "logout.jsp";
		req.getRequestDispatcher(url).forward(req, res);
	}
}
}