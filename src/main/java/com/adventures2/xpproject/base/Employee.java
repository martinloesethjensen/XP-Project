package com.adventures2.xpproject.base;

public class Employee {
	private int id;
	private String realname;
	private boolean monday;
	private boolean tuesday;
	private boolean wednesday;
	private boolean thursday;
	private boolean friday;

	public Employee() {
	}

	public Employee(int id, String realname) {
		this.id = id;
		this.realname = realname;
	}

	//Constructor for scheme
	public Employee(String realname, boolean monday, boolean tuesday, boolean wednesday, boolean thursday, boolean friday) {
		this.realname = realname;
		this.monday = monday;
		this.tuesday = tuesday;
		this.wednesday = wednesday;
		this.thursday = thursday;
		this.friday = friday;
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

	public boolean isMonday() {
		return monday;
	}

	public boolean isTuesday() {
		return tuesday;
	}

	public boolean isWednesday() {
		return wednesday;
	}

	public boolean isThursday() {
		return thursday;
	}

	public boolean isFriday() {
		return friday;
	}
}
