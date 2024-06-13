package com.studentmanagment.main;

import com.studentmanagment.util.*;
import com.studentmanagment.dao.*;
import com.studentmanagment.model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static Scanner scanner = new Scanner(System.in);

	public static studentDao studentdao;

	public static void main(String[] args) {
		System.out.println("\t\t********** Student Managment System *************");

		while (true) {
			printMenu();
			System.out.print("\n\tEnter Your Choice : ");
			int choice = scanner.nextInt();
			scanner.nextLine();
			try (Connection connection = DbConnection.getConnection()) {
				studentdao = new studentDao(connection);
				switch (choice) {
				case 1: {
					// add student
					addStudent();
					break;
				}
				case 2: {
					// update Student
					updateStudent();
					break;
				}
				case 3: {
					// search Student
					searchStudent();
					break;
				}
				case 4: {
					// delete Student
					deleteStudent();
					break;
				}
				case 5: {
					// sort students
					sortStudents();
					break;
				}
				case 6: {
					// Exit the system
					System.out.println("\n\n\tThank You for using the System...");
					System.exit(0);
					break;
				}
				default:
					System.out.println("Invalid choice. Please try again.");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public static void printMenu() {

		System.out.println("\n\t\t1. Add Student");
		System.out.println("\t\t2. Search Student");
		System.out.println("\t\t3. Delete Student");
		System.out.println("\t\t4. Update Student");
		System.out.println("\t\t5. Sort Students");
		System.out.println("\t\t6. Exit!");
	}

	public static void addStudent() {
		List<Student> students = new ArrayList<>();
		boolean addedSuccessfully = false;
		do {

			System.out.print("\n\tEnter First Name : ");
			String firstname = scanner.nextLine();
			System.out.print("\tEnter Last Name : ");
			String lastname = scanner.nextLine();
			System.out.print("\tEnter Date of Birth(YYYY-MM-DD) : ");
			String dateOfBirth = scanner.nextLine();
			System.out.print("\tEnter Gender : ");
			String gender = scanner.nextLine();
			System.out.print("\tEnter Contact No: ");
			String contactNo = scanner.nextLine();
			System.out.print("\tEnter Address: ");
			String address = scanner.nextLine();
			System.out.print("\tEnter Grade: ");
			String grade = scanner.nextLine();

			Student student = new Student(firstname, lastname, dateOfBirth, gender, contactNo, address, grade);
			students.add(student);
			System.out.print("\n\tDo you want to add another student? (y/n): ");
		} while (scanner.nextLine().equalsIgnoreCase("y"));

		try {
			studentdao.addStudent(students);
			addedSuccessfully = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (addedSuccessfully) {
			System.out.println("\n\n\tStudents added Successfully!");
		} else {
			System.out.println("Failed to add students.");
		}

	}

	public static void searchStudent() {

		System.out.print("\n\tEnter first name or last name to search : ");
		String searchTerm = scanner.nextLine();
		try {
			List<Student> students = studentdao.searchStudents(searchTerm);
			if (students.isEmpty()) {
				System.out.println("\n\tNo students found matching the search criteria.");
			} else {
				System.out.println("\n\tSearch Results : ");
				for (Student student : students) {
					System.out.println(student);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\n\tFailed to search for students.");
		}
	}

	public static void updateStudent() {
		System.out.print("\n\tEnter the student ID : ");
		int studentId = scanner.nextInt();
		try {
			List<Student> students = studentdao.searchStudents("");
			Student studentToUpdate = null;
			for (Student student : students) {
				if (student.getStudentId() == studentId) {
					studentToUpdate = student;
					break;
				}
			}

			if (studentToUpdate == null) {
				System.out.println("\n\t No student found with the given ID");
				return;
			}
			scanner.nextLine();
			System.out.print("\n\tEnter new First Name(leave blank to keep current : " + studentToUpdate.getFirstName()
					+ ") : ");
			String firstName = scanner.nextLine();
			if (!firstName.trim().isEmpty()) {
				studentToUpdate.setFirstName(firstName);
			}

			System.out.print(
					"\n\tEnter new last Name(leave blank to keep current : " + studentToUpdate.getLastName() + ") : ");
			String lastName = scanner.nextLine();
			if (!lastName.trim().isEmpty()) {
				studentToUpdate.setLastName(lastName);
			}

			System.out.print("\tEnter new Date of Birth (YYYY-MM-DD) (leave blank to keep current: "
					+ studentToUpdate.getDateOfBirth() + "): ");
			String dateOfBirth = scanner.nextLine();
			if (!dateOfBirth.trim().isEmpty()) {
				studentToUpdate.setDateOfBirth(dateOfBirth);
			}

			System.out.print("\tEnter new Gender (leave blank to keep current: " + studentToUpdate.getGender() + "): ");
			String gender = scanner.nextLine();
			if (!gender.trim().isEmpty()) {
				studentToUpdate.setGender(gender);
			}

			System.out.print(
					"\tEnter new Contact No (leave blank to keep current: " + studentToUpdate.getContactNo() + "): ");
			String contactNo = scanner.nextLine();
			if (!contactNo.trim().isEmpty()) {
				studentToUpdate.setContactNo(contactNo);
			}

			System.out
					.print("\tEnter new Address (leave blank to keep current: " + studentToUpdate.getAddress() + "): ");
			String address = scanner.nextLine();
			if (!address.trim().isEmpty()) {
				studentToUpdate.setAddress(address);
			}

			System.out.print("\tEnter new Grade (leave blank to keep current: " + studentToUpdate.getGrade() + "): ");
			String grade = scanner.nextLine();
			if (!grade.trim().isEmpty()) {
				studentToUpdate.setGrade(grade);
			}

			studentdao.updateStudent(studentToUpdate);
			System.out.println("\n\tStudent data updated successfully");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to update student.");
		}
	}

	public static void deleteStudent() {
		System.out.print("\n\tEnter Student ID : ");
		int studentId = scanner.nextInt();
		try {
			Student studentToDelete = studentdao.searchStudentById(studentId);
			if (studentToDelete == null) {
				System.out.println("\n\tNo student found with ID " + studentId);
				return;
			}

			// Display student information
			System.out.println("\n\tStudent Found:");
			System.out.println("\tStudent ID: " + studentToDelete.getStudentId());
			System.out.println("\tFirst Name: " + studentToDelete.getFirstName());
			System.out.println("\tLast Name: " + studentToDelete.getLastName());
			System.out.println("\tDate of Birth: " + studentToDelete.getDateOfBirth());
			System.out.println("\tGender: " + studentToDelete.getGender());
			System.out.println("\tContact No: " + studentToDelete.getContactNo());
			System.out.println("\tAddress: " + studentToDelete.getAddress());
			System.out.println("\tGrade: " + studentToDelete.getGrade());
			scanner.nextLine();
			System.out.print("\n\t Do you want to delete this student data (y/n): ");
			String confirm = scanner.nextLine().trim().toLowerCase();
			if (confirm.equals("y")) {
				studentdao.deleteStudent(studentId);
				System.out.println("\n\tStudent with ID " + studentId + " deleted successfully");
			} else {
				System.out.println("\n\tDeletion canceled.");
			}

		} catch (Exception e) {
			System.out.println("Failed to delete student.");
		}

	}

	public static void sortStudents() {
		System.out.print("\n\t Enter the sorting criteria(firstName/lastName/grade) : ");
		String criteria = scanner.nextLine();
		try {
			List<Student> students = studentdao.sortStudents(criteria);
			if (students.isEmpty()) {
				System.out.println("\n\tNo students found.");
			} else {
				System.out.println("\n\tSorted Students: ");
				for (Student student : students) {
					System.out.println(student);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\n\tFailed to sort students.");
		}
	}

}
