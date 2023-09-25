package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TodoRegistrationDAO;

@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ------------------ 1件削除 -----------------------
		// リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");

		// 削除処理
		TodoRegistrationDAO deletedao = new TodoRegistrationDAO();
		deletedao.deleteOne(Integer.parseInt(id));

		//セッションスコープ呼び出し、1件削除しました。をmsgに代入し、セッションスコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("msg", "1件削除しました。");
		//削除された後のリダイレクト先
		response.sendRedirect("MainTodo");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ------------------ まとめて削除 -----------------------
		// リクエストパラメータを取得
		request.setCharacterEncoding("UTF-8");
		String[] check = request.getParameterValues("check");

		//インスタンス化
		TodoRegistrationDAO deletedao = new TodoRegistrationDAO();
		//セッションスコープ呼び出し
		HttpSession session = request.getSession();
		//チェックボックスにチェックがついたものを削除する
		if (check != null) {
			for (int i = check.length - 1; i >= 0; i--) {
				if (check[i] != null) {
					deletedao.deleteOne(Integer.parseInt(check[i]));

				}

			}
			//●件削除しました。をmsgに代入し、セッションスコープに保存
			session.setAttribute("msg", check.length + "件削除しました。");
		} else {
			//チェックボックスにチェックがついてないとき「チェックが入っていません」と入れる
			session.setAttribute("msg", "チェックが入っていません");
		}

		//削除された後のリダイレクト先
		response.sendRedirect("MainTodo");

	}

}
