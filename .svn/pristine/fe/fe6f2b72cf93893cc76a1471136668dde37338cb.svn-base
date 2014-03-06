package edu.uprm.ece.icom4035.mygrades.managers;

import java.io.Serializable;

/**
 * Student is a basic data structure that stores student details and it's grades
 * in an SortedDoublyList.
 * 
 * <h4>Description</h4>
 * 
 * This is an implementation of an data type which handles specific information
 * for each instance created.
 * 
 * <h4>Notes</h4>
 * 
 * Each student can be initialized with a unique identifier using the
 * alternative constructor. This can only be achieved when creating an instance
 * of this class, since only getters are provided for this specific value.
 * 
 * @author Lixhjideny Méndez Ríos
 * 
 * @version 1.5
 * 
 */
public class Student implements Comparable<Student>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String firstName;
	private String lastName;
	private SortedDoublyList<Grade> grades;

	/**
	 * Creates a student with the default initialization (non-unique ID).
	 * 
	 * @param firstName
	 *            a String containing the student's first name
	 * @param lastName
	 *            a String containing the student's last name
	 */
	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.grades = new SortedDoublyList<Grade>();
	}

	/**
	 * Creates a student with the default initialization (non-unique ID).
	 * 
	 * @param firstName
	 *            a String containing the student's first name
	 * @param lastName
	 *            a String containing the student's last name
	 * @param grades
	 *            A Grade SortedDoublyList containing the student's grades
	 */
	public Student(String firstName, String lastName,
			SortedDoublyList<Grade> grades) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.grades = grades;
	}

	/**
	 * Creates a student with with a unique identification (ID) field.
	 * 
	 * @param id
	 *            A integer value with the desired unique identification number.
	 * @param firstName
	 *            a String containing the student's first name
	 * @param lastName
	 *            a String containing the student's last name
	 */
	public Student(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.grades = new SortedDoublyList<Grade>();
	}

	/**
	 * Creates a student with with a unique identification (ID) field.
	 * 
	 * @param id
	 *            A integer value with the desired unique identification number.
	 * @param firstName
	 *            a String containing the student's first name
	 * @param lastName
	 *            a String containing the student's last name
	 * @param grades
	 *            A Grade SortedDoublyList containing the student's grades
	 */
	public Student(int id, String firstName, String lastName,
			SortedDoublyList<Grade> grades) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.grades = grades;
	}

	/**
	 * @return The unique ID for this instance of a Contact.
	 */
	public int getID() {
		return id;
	}

	/**
	 * 
	 * @return A String containing the student's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return A String contining the student's last name.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return The amount of grades inside this student instance.
	 */
	public int getTotalGrades() {
		return this.grades.size();
	}

	/**
	 * @return The SortedDoublyList of all the grades in this student instance.
	 */
	public SortedDoublyList<Grade> getGrades() {
		return grades;
	}

	/**
	 * Modifies a student with the default initialization (non-unique ID).
	 * 
	 * @param firstName
	 *            a String containing the student's first name
	 * @param lastName
	 *            a String containing the student's last name
	 * @param grades
	 *            A Grade SortedDoublyList containing the student's grades
	 */
	public void editStudent(Student student) {
		this.firstName = student.firstName;
		this.lastName = student.lastName;
		this.grades = student.getGrades();
	}

	/**
	 * Adds a grade to the student's SortedDoublyList of grades.
	 * 
	 * @param grade
	 *            A grade containing the test name and the student's score
	 */
	public void addGrade(Grade grade) {
		this.grades.add(grade);
	}

	/**
	 * Compares this instance of a Student with another by their lastName
	 * fields.
	 * 
	 * @param another
	 *            Another instance of a Student to be compared with.
	 * 
	 * @return 0 if the strings are equal, a negative integer if this string is
	 *         before the specified string, or a positive integer if this string
	 *         is after the specified string.
	 */
	@Override
	public int compareTo(Student another) {
		return this.lastName.compareToIgnoreCase(another.lastName);
	}
}
