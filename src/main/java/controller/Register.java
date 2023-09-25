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

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//データベースに接続出来てるか確認
		TodoRegistrationDAO dao = new TodoRegistrationDAO();
		dao.connectCheck();

		// ------------------ カテゴリーでまとめる -----------------------
		//リクエストスコープに保存
		List<String> categories = dao.findCategories();
		request.setAttribute("categories", categories);

		//フォワード
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/todoList.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// ------------------ 1件登録 -----------------------
		//リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String toDo = request.getParameter("toDo");
		String limit = request.getParameter("limit");
		String category = request.getParameter("category");
		String newcategory=request.getParameter("newcategory");
		
		if(!newcategory.equals("")) {
			category=newcategory;
		}

		//取得したリクエストパラメータをまとめる
		ToDoRegistration tdr = new ToDoRegistration(toDo, limit, category);

		//インスタンス化
		TodoRegistrationDAO dao = new TodoRegistrationDAO();
		dao.insertOne(tdr);

		//セッションスコープ呼び出し、1件登録しました。をmsgに代入し、セッションスコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("msg", "1件登録しました。");
		//登録した後のリダイレクト
		response.sendRedirect("MainTodo");
	}

}
