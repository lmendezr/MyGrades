package edu.uprm.ece.icom4035.mygrades.managers;

import java.io.Serializable;

/**
 * Test is a basic data structure that stores test details and students that
 * have taken it in an SortedDoublyList.
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
public class Test implements Comparable<Test>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int ID;
	private SortedDoublyList<Double> gradesList;
	private SortedDoublyList<Student> studentsList;

	public Test(int ID, Student student, Grade grade) {
		this.ID = ID;
		this.gradesList = new SortedDoublyList<Double>();
		this.studentsList = new SortedDoublyList<Student>();
		this.name = grade.getGradeName();
		this.gradesList.add(grade.getScore());

		SortedDoublyList<Grade> contactGrade = new SortedDoublyList<Grade>();
		contactGrade.add(grade);
		this.studentsList.add(new Student(student.getFirstName(), student
				.getLastName(), contactGrade));
	}

	/**
	 * @return A String containing the test's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return The unique ID for this instance of a Contact.
	 */
	public int getID() {
		return ID;
	}

	/**
	 * @return A SortedDoublyList containing the students that have taken this
	 *         test.
	 */
	public SortedDoublyList<Student> getStudentsTaken() {
		return this.studentsList;
	}

	/**
	 * @return The times this test has been taken.
	 */
	public int getTimesTaken() {
		return gradesList.size();
	}

	/**
	 * @return The minimum score in the list of students that have taken this
	 *         test.
	 */
	public double getMin() {
		return gradesList.first();
	}

	/**
	 * @return The maximum score in the list of students that have taken this
	 *         test.
	 */
	public double getMax() {
		return gradesList.last();
	}

	/**
	 * @return The average of the scores in the list of students that have taken
	 *         this test.
	 */
	public double getAvg() {
		return calcAvg(this.gradesList);
	}

	/**
	 * @return The standard deviation of the scores in the list of students that
	 *         have taken this test.
	 */
	public double getStdDev() {
		return calcStdDev(this.gradesList);
	}

	/**
	 * Adds a grade and a student to their respective SortedDoublyLists.
	 * 
	 * @param student
	 *            A student that took this test
	 * @param grade
	 *            The score of the student
	 */
	public void addGrade(Student student, Grade grade) {
		this.gradesList.add(grade.getScore());

		SortedDoublyList<Grade> contactGrade = new SortedDoublyList<Grade>();
		contactGrade.add(grade);
		this.studentsList.add(new Student(student.getFirstName(), student
				.getLastName(), contactGrade));
	}

	private Double calcAvg(SortedDoublyList<Double> values) {
		Double sum = 0.0;
		if (values.isEmpty()) {
			return 0.0;
		} else {
			for (Double mark : values) {
				sum += mark;
			}
			return sum.doubleValue() / values.size();
		}
	}

	/**
	 * Calculates the standard deviation in a list of doubles.
	 * 
	 * @param values
	 *            the list containing the doubles to evaluate
	 * @return the standard deviation in the list
	 */
	private double calcStdDev(SortedDoublyList<Double> values) {
		return Math.pow(calcVariance(values), 0.5);
	}

	/**
	 * Calculates the variance in a list of doubles.
	 * 
	 * @param values
	 *            the list containing the doubles to evaluate
	 * @return the variance in the list
	 */
	private double calcVariance(SortedDoublyList<Double> values) {
		double total = 0;
		double sTotal = 0;
		double scalar = 1 / (double) (values.size());
		for (int i = 0; i < values.size(); i++) {
			total += values.get(i);
			sTotal += Math.pow(values.get(i), 2);
		}
		return (scalar * (sTotal - (Math.pow(total, 2) / values.size())));
	}

	/**
	 * Compares this instance of a Test with another by their name fields.
	 * 
	 * @param another
	 *            Another instance of a Test to be compared with.
	 * 
	 * @return 0 if the strings are equal, a negative integer if this string is
	 *         before the specified string, or a positive integer if this string
	 *         is after the specified string.
	 */
	@Override
	public int compareTo(Test another) {
		return this.getName().compareTo(another.getName());
	}

}