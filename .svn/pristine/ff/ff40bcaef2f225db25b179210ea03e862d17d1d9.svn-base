package edu.uprm.ece.icom4035.mygrades.managers;

import java.io.Serializable;

/**
 * Address is a basic data structure that stores a Test's name and a student's
 * grade.
 * 
 * <h4>Description</h4>
 * 
 * This is an implementation of an data type which handles specific information
 * for each instance created.
 * 
 */
public class Grade implements Comparable<Grade>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String gradeName;
	private double score;

	/**
	 * Creates an Grade with a default initialization.
	 * 
	 * @param gradeName
	 *            A String with the test's name
	 * @param score
	 *            The student's score in the test
	 */
	public Grade(String gradeName, double score) {
		this.gradeName = gradeName;
		this.score = score;
	}

	/**
	 * @return A String containing the test's name
	 */
	public String getGradeName() {
		return gradeName;
	}

	/**
	 * @return the student's score in the test
	 */
	public double getScore() {
		return score;
	}

	/**
	 * Modifies the test's name
	 * 
	 * @param gradeName
	 *            A String containing the new name
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	/**
	 * Modifies the student's score in the test. This works great for good
	 * professors that love their students and usually provide ways for them to
	 * get better.
	 * 
	 * @param score
	 *            The student's new score
	 */
	public void setScore(double score) {
		this.score = score;
	}

	/**
	 * Compares this instance of a Grade with another by their score fields.
	 * 
	 * @param another
	 *            Another instance of a Grade to be compared with.
	 * 
	 * @return 0 if the scores are equal, a negative integer if this score is
	 *         lesser the specified score, or a positive integer if this score
	 *         is greater the specified score.
	 */
	@Override
	public int compareTo(Grade another) {
		return this.gradeName.compareTo(another.gradeName);
	}

}