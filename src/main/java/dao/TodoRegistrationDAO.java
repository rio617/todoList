package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.ToDoRegistration;

public class TodoRegistrationDAO {
	Connection con; // データベースとの接続、切断
	PreparedStatement stmt; // SQL文の準備、実行
	ResultSet rs; // SQL文の実行結果を受け取る

	private void connect() throws NamingException, SQLException {
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:comp/env/mariadb");
		this.con = ds.getConnection();
	}

	private void disconnect() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//カテゴリーでまとめる
	public List<String> findCategories() {
		List<String> list = new ArrayList<>();

		try {
			this.connect();
			stmt = con.prepareStatement("SELECT category FROM todoregistration GROUP BY category");
			rs = stmt.executeQuery();
			while (rs.next()) {
				String category = rs.getString("category");
				list.add(category);

			}
		} catch (NamingException | SQLException e) {
			System.out.println("接続失敗");
		} finally {
			this.disconnect();
		}
		return list;
	}

	// 全件取得する
	public List<ToDoRegistration> findAll() {
		List<ToDoRegistration> list = new ArrayList<>();
		try {
			this.connect();
			stmt = con.prepareStatement("SELECT * FROM todoregistration");
			rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String toDo = rs.getString("toDo");
				String limit = rs.getString("timelimit");
				String category = rs.getString("category");
				ToDoRegistration tdr = new ToDoRegistration(id, toDo, limit, category);
				list.add(tdr);
			}
		} catch (NamingException | SQLException e) {
			System.out.println("接続失敗");
		} finally {
			this.disconnect();
		}
		return list;
	}

	// 日付順にする
	public List<ToDoRegistration> ascDate() {
		List<ToDoRegistration> list = new ArrayList<>();
		try {
			this.connect();
			stmt = con.prepareStatement("SELECT * FROM todoregistration ORDER BY timelimit ASC");
			rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String toDo = rs.getString("toDo");
				String limit = rs.getString("timelimit");
				String category = rs.getString("category");

				ToDoRegistration tdr = new ToDoRegistration(id, toDo, limit, category);
				list.add(tdr);
			}

		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return list;
	}

	// 1件追加
	public boolean insertOne(ToDoRegistration tdr) {
		try {
			this.connect();
			stmt = con.prepareStatement("INSERT INTO todoregistration(todo,timelimit,category) VALUES(?,?,?)");
			stmt.setString(1, tdr.getToDo());
			stmt.setString(2, tdr.getLimit());
			stmt.setString(3, tdr.getCategory());
			int result = stmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return true;
	}
	

	// 1件削除
	public boolean deleteOne(int id) {
		try {
			this.connect();
			stmt = con.prepareStatement("DELETE FROM todoregistration WHERE id=?");
			stmt.setInt(1, id);
			int result = stmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return true;
	}

	// データベースに接続出来てるか確認
	public void connectCheck() {
		try {
			this.connect();
			System.out.println("OK");
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
	}
}
