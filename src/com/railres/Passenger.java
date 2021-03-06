package com.railres;

public class Passenger {
	
	private static int id = 1;
	private String name;
	private int age;
	private String gender;
	private String berthPreference;
	private int  pnr;
	
	
	
	
	public Passenger(String name, int age, String gender, String berthPreference) {
		this.setName(name);
		this.setAge(age);
		this.setGender(gender);
		this.setBerthPreference(berthPreference);;
		this.setPnr();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getBerthPreference() {
		return berthPreference;
	}
	public void setBerthPreference(String berthPreference) {
		this.berthPreference = berthPreference;
	}
	public int getPnr() {
		return pnr;
	}
	public void setPnr() {
		this.pnr = id++;
	}
	
	
	
	@Override
	public String toString() {
		return "Passenger [Name = " + name + ", Age = " + age + ", Gender = " + gender + ", PNR = " + pnr + "]";
	}
	
}
