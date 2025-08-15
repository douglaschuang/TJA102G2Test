package com.babymate.staff.model;

public class Staff {
	private String staffName;
	
	public static void main (String[] args) {
		Staff staff = new Staff("emp1");
		
		System.out.println("Hello Git and "+ staff.getStaffName());
	}

	public Staff(String staffName) {
		super();
		this.staffName = staffName;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
}
