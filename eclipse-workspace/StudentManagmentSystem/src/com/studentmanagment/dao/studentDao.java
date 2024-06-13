package com.studentmanagment.dao;

import java.util.ArrayList;
import java.util.List;
import com.studentmanagment.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class studentDao {
	private Connection connection;
	private PreparedStatement psmt;
	private ResultSet rs;

	public studentDao(Connection connection) {
		this.connection = connection;
	}

	public void addStudent(List<Student> student) throws SQLException {

		String addStudentQuery = "insert into students(firstName, lastName, dateOFBirth, gender, contactNo, address, grade) values(?,?,?,?,?,?,?)";
		try {
			psmt = connection.prepareStatement(addStudentQuery);
			for (Student students : student) {

				psmt.setString(1, students.getFirstName());
				psmt.setString(2, students.getLastName());
				psmt.setString(3, students.getDateOfBirth());
				psmt.setString(4, students.getGender());
				psmt.setString(5, students.getContactNo());
				psmt.setString(6, students.getAddress());
				psmt.setString(7, students.getGrade());
				psmt.addBatch();
			}
			psmt.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Student> searchStudents(String searchTerm) throws SQLException {
		List<Student> students = new ArrayList<>();
		String searchQuery = "select * from students where firstName like ? or lastName like ?";
		try {
			psmt = connection.prepareStatement(searchQuery);
			psmt.setString(1, "%" + searchTerm + "%");
			psmt.setString(2, "%" + searchTerm + "%");
			rs = psmt.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setStudentId(rs.getInt("studentId"));
				student.setFirstName(rs.getString("firstName"));
				student.setLastName(rs.getString("lastName"));
				student.setDateOfBirth(rs.getString("dateOfBirth"));
				student.setGender(rs.getString("gender"));
				student.setContactNo(rs.getString("contactNo"));
				student.setAddress(rs.getString("address"));
				student.setGrade(rs.getString("grade"));
				students.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}

	public Student searchStudentById(int studentId) throws SQLException {
		Student student = null;
		String searchQuery = "select * from students where studentId = ?";
		try {
			psmt = connection.prepareStatement(searchQuery);
			psmt.setInt(1, studentId);
			rs = psmt.executeQuery();
			if (rs.next()) {
				student = new Student();
				student.setStudentId(rs.getInt("studentId"));
				student.setFirstName(rs.getString("firstName"));
				student.setLastName(rs.getString("lastName"));
				student.setDateOfBirth(rs.getString("dateOfBirth"));
				student.setGender(rs.getString("gender"));
				student.setContactNo(rs.getString("contactNo"));
				student.setAddress(rs.getString("address"));
				student.setGrade(rs.getString("grade"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	public void updateStudent(Student student) throws SQLException {
		String updateQuery = "update students set firstName = ?,lastName = ?, dateOfBirth = ?, gender = ?, contactNo = ?, address = ?, grade = ? where studentId = ?";
		try {
			psmt = connection.prepareStatement(updateQuery);
			psmt.setString(1, student.getFirstName());
			psmt.setString(2, student.getLastName());
			psmt.setString(3, student.getDateOfBirth());
			psmt.setString(4, student.getGender());
			psmt.setString(5, student.getContactNo());
			psmt.setString(6, student.getAddress());
			psmt.setString(7, student.getGrade());
			psmt.setInt(8, student.getStudentId());
			psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteStudent(int studentId) throws SQLException {
		String deleteQuery = "delete from students where studentId = ?";
		try {
			psmt = connection.prepareStatement(deleteQuery);
			psmt.setInt(1, studentId);
			psmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public List<Student> sortStudents(String criteria) throws SQLException {
		List<Student> students = new ArrayList<>();
		String sortStudentsQuery = "select * from students order by " + criteria;
		try {
			psmt = connection.prepareStatement(sortStudentsQuery);
			rs = psmt.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setStudentId(rs.getInt("studentId"));
				student.setFirstName(rs.getString("firstName"));
				student.setLastName(rs.getString("lastName"));
				student.setDateOfBirth(rs.getString("dateOfBirth"));
				student.setGender(rs.getString("gender"));
				student.setContactNo(rs.getString("contactNo"));
				student.setAddress(rs.getString("address"));
				student.setGrade(rs.getString("grade"));
				students.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return students;
	}

}
