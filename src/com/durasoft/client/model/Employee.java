package com.durasoft.client.model;

public class Employee {
	private String name;
	private int yearsOfExperience;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYearsOfExperience() {
		return yearsOfExperience;
	}
	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
	public Employee(String name, int yearsOfExperience) {
		this.name = name;
		this.yearsOfExperience = yearsOfExperience;
	}
	public Employee() {
	}
}
