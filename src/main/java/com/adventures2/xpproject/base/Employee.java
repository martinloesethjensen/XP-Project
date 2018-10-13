package com.adventures2.xpproject.base;

public class Employee {
	private int id;
	private String realname;

	public Employee() {
	}

	public Employee(int id, String realname) {
		this.id = id;
		this.realname = realname;
	}

	public Employee(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Employee{" +
			"id=" + id +
			", realname='" + realname + '\'' +
			'}';
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
}
