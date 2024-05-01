package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class Logoutaction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		req.getRequestDispatcher("logout.jsp").forward(req, res);
	}
}
