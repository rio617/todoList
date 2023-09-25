package model;

import java.io.Serializable;

public class ToDoRegistration implements Serializable {
	private int id; //todo登録番号
	private String toDo; //やることを登録する
	private String limit; //期限
	private String category; //todoカテゴリー

	
	public ToDoRegistration() {
		
	}
	
	public ToDoRegistration(String toDo, String limit,String category) {
		this.toDo = toDo;
		this.limit = limit;
		this.category=category;
	}
	
	public ToDoRegistration(int id,String toDo, String limit,String category) {
		this(toDo,limit,category);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getToDo() {
		return toDo;
	}

	public void setToDo(String toDo) {
		this.toDo = toDo;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
