package com.studentmanagment.model;

public class Student {
	private int studentId;
	private String FirstName;
	private String LastName;
	private String DateOfBirth;
	private String gender;
	private String ContactNo;
	private String address;
	private String grade;

	public Student() {
		super();
	}

	public Student(String firstName, String lastName, String dateOfBirth, String gender, String contactNo,
			String address, String grade) {
		this.FirstName = firstName;
		this.LastName = lastName;
		this.DateOfBirth = dateOfBirth;
		this.gender = gender;
		this.ContactNo = contactNo;
		this.address = address;
		this.grade = grade;
	}

	public Student(int studentId, String firstName, String lastName, String dateOfBirth, String gender,
			String contactNo, String address, String grade) {
		this.studentId = studentId;
		this.FirstName = firstName;
		this.LastName = lastName;
		this.DateOfBirth = dateOfBirth;
		this.gender = gender;
		this.ContactNo = contactNo;
		this.address = address;
		this.grade = grade;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getDateOfBirth() {
		return DateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		DateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContactNo() {
		return ContactNo;
	}

	public void setContactNo(String contactNo) {
		ContactNo = contactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "\t studentId : " + studentId + "\n" + "\t FirstName : " + FirstName + "\n" + "\t LastName : " + LastName
				+ "\n" + "\t DateOfBirth : " + DateOfBirth + "\n" + "\t gender : " + gender + "\n" + "\t ContactNo : " + ContactNo
				+ "\n" + "\t address : " + address + "\n" + "\t grade : " + grade+"\n\n";
	}

}
