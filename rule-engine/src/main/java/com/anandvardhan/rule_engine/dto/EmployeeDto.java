package com.anandvardhan.rule_engine.dto;

public class EmployeeDto {

	private int age;
	private String department ;
	private int salary;
	private int experience;
	
	public EmployeeDto(){
		
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}

	@Override
	public String toString() {
		return "EmployeeDto [age=" + age + ", department=" + department + ", salary=" + salary + ", experience="
				+ experience + "]";
	}
	
	
}
