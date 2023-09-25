package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TodoRegistrationDAO;
import model.ToDoRegistration;

@WebServlet("/MainTodo")
public class MainTodo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// ------------------ 日付順 -----------------------
		TodoRegistrationDAO dao = new TodoRegistrationDAO();
		//リクエストスコープに保存 日付順にする
		List<ToDoRegistration> list = dao.ascDate();
		request.setAttribute("list", list);

		//セッションスコープ呼び出し
		HttpSession session = request.getSession();
		//セッションスコープからmsg取得
		String msg = (String) session.getAttribute("msg");
		//セッションスコープ削除
		session.removeAttribute("msg");
		//リクエストスコープに保存
		request.setAttribute("msg", msg);

		//フォワード先
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/todoRegister.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
