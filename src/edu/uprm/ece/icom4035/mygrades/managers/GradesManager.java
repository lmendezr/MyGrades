package edu.uprm.ece.icom4035.mygrades.managers;

import java.io.IOException;

/**
 * GradesManager is a bridge between the internal structure and the user
 * interface of MyStudents.
 * 
 * <h4>Description</h4>
 * 
 * GradesManager is the class that bridges Android activities with everything
 * related with the Tests. The main purpose of this class is to load tests from
 * the students in the file, store them in the SortedDoublyList, and display
 * stats for each test to the user.
 * 
 * @author Lixhjideny Méndez Ríos
 * 
 * @version 1.5
 * 
 */
public class GradesManager {

	private SortedDoublyList<Test> testsList;
	private StudentStore studentStore;

	/**
	 * Creates an instance of GradesManager, loading the Students from the file
	 * into an internal SortedDoublyList. Then launches the helper method that
	 * loads every Test into an another internal SortedDoublyList.
	 */
	public GradesManager() {
		studentStore = new StudentStore();
		SortedDoublyList<Student> studentsList;
		try {
			studentsList = studentStore.loadStudents();
			loadTests(studentsList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Extracts information from each student in order to obtain a list of
	 * Tests.
	 * 
	 * @param studentsList
	 *            a SortedDoublyList containing all the students from which Test
	 *            information needs to be gathered.
	 */
	private void loadTests(SortedDoublyList<Student> studentsList) {
		int k = 0;
		this.testsList = new SortedDoublyList<Test>();
		for (Student student : studentsList) {
			for (Grade grade : student.getGrades()) {
				int place = -1;
				for (int i = 0; i < this.testsList.size(); ++i) {
					if (grade.getGradeName().equalsIgnoreCase(
							testsList.get(i).getName())) {
						place = i;
					}
				}
				if (place == -1) {
					testsList.add(new Test(k, student, grade));
					k++;
				} else {
					testsList.get(place).addGrade(student, grade);
				}
			}
		}
	}

	/**
	 * @return The internal SortedDoublyList containing all the Tests currently
	 *         loaded in the program.
	 */
	public SortedDoublyList<Test> getAll() {
		return this.testsList;
	}

	/**
	 * Get the position of a Test in the SortedDoublyList by providing it's
	 * unique ID.
	 * 
	 * @param ID
	 *            The unique identification of the desired Contact.
	 * 
	 * @return The position in the SortedDoublyList of the desired Contact.
	 */
	public int getTestPosition(int ID) {
		for (int i = 0; i < testsList.size(); ++i) {
			if (ID == testsList.get(i).getID()) {
				return i;
			}
		}
		throw new IllegalArgumentException(
				"ID doesn't match any contacts in the list.");
	}
}
