package edu.uprm.ece.icom4035.mygrades.managers;

import java.io.IOException;

/**
 * StudentManager is a bridge between the internal structure and the user
 * interface of MyGrades.
 * 
 * <h4>Description</h4>
 * 
 * StudentManager is the class that bridges Android activities with everything
 * related with the Students. The main purpose of this class is to load students
 * from a file, store them in the SortedDoublyList, perform modifications, and
 * update the file with the latest changes from the user.
 * 
 * @author Lixhjideny Méndez Ríos
 * 
 * @version 1.5
 * 
 */
public class StudentManager {

	private SortedDoublyList<Student> studentsList;
	private StudentStore studentStore;

	/**
	 * Creates an instance of StudentManager, loading the Student from the file
	 * into the internal SortedDoublyList.
	 */
	public StudentManager() {
		try {
			this.studentStore = new StudentStore();
			this.studentsList = this.studentStore.loadStudents();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return The internal SortedDoublyList containing all the Students
	 *         currently loaded in the program.
	 */
	public SortedDoublyList<Student> getAll() {
		return this.studentsList;
	}

	/**
	 * Get the position of a student in the SortedDoublyList by providing it's
	 * unique ID.
	 * 
	 * @param ID
	 *            The unique identification of the desired Student.
	 * 
	 * @return The position in the SortedDoublyList of the desired Student.
	 */
	public int getStudentPosition(int ID) {
		for (int i = 0; i < studentsList.size(); ++i) {
			if (ID == studentsList.get(i).getID()) {
				return i;
			}
		}
		throw new IllegalArgumentException(
				"ID doesn't match any contacts in the list.");
	}

	// student operations
	/**
	 * Adds a new Student into the internal SortedDoublyList.
	 * 
	 * @param student
	 *            An instance of a Student containing all the information for
	 *            the student to be added.
	 */
	public boolean addStudent(Student student) {
		boolean result = this.studentsList.add(student);
		try {
			studentStore.updateFile(studentsList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Modifies a Student in the internal SortedDoublyList.
	 * 
	 * @param studentIndex
	 *            The index of the desired student. Expects a value within the
	 *            range 0 to studentsList.size()-1.
	 * @param student
	 *            An instance of a Student containing all the information for
	 *            the student to be modified.
	 */
	public void editStudent(int studentIndex, Student student) {
		studentsList.get(studentIndex).editStudent(student);
		sortContacts();
		// boolean result = this.studentsList.set(studentIndex, student);
		try {
			studentStore.updateFile(studentsList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sorts the entire list of contacts after modifying a specific contact
	 * details. Avoids errors inside the SortedDoublyList since sorting occurs
	 * only during addition of objects.
	 */
	public void sortContacts() {
		SortedDoublyList<Student> temp = new SortedDoublyList<Student>();
		for (Student e : this.studentsList) {
			temp.add(e);
		}
		this.studentsList.clear();
		this.studentsList = temp;
	}

	/**
	 * Removes Student stored in the internal SortedDoublyList.
	 * 
	 * @param studentIndex
	 *            The index of the desired student. Expects a value within the
	 *            range 0 to studentsList.size()-1.
	 */
	public boolean deleteStudent(int studentIndex) {
		boolean result = this.studentsList.remove(studentIndex);
		try {
			studentStore.updateFile(studentsList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Removes Student stored in the internal SortedDoublyList.
	 * 
	 * @param student
	 *            An instance of the student to be removed.
	 */
	public boolean deleteStudent(Student student) {
		boolean result = this.studentsList.remove(student);
		try {
			studentStore.updateFile(studentsList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Clears all the Students stored in the internal SortedDoublyList.
	 */
	public void clearStudents() {
		this.studentsList.clear();
		try {
			studentStore.updateFile(studentsList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// grade operations for a specific student
	/**
	 * Adds a new grade to a Student already loaded in the SortedDoublyList.
	 * 
	 * @param studentIndex
	 *            The index of the desired student. Expects a value within the
	 *            range 0 to studentsList.size()-1.
	 * @param grade
	 *            An instance of a Grade containing all the information for the
	 *            student's performance on this test.
	 */
	public void addGrade(int studentIndex, Grade grade) {
		this.studentsList.get(studentIndex).addGrade(grade);
		try {
			studentStore.updateFile(studentsList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds a new grade to a Student already loaded in the SortedDoublyList.
	 * 
	 * @param studentIndex
	 *            The index of the desired student. Expects a value within the
	 *            range 0 to studentsList.size()-1.
	 * @param gradeIndex
	 *            The index of the desired grade. Expects a value within the
	 *            range 0 to gradesList.size()-1.
	 * @param grade
	 *            An instance of a Grade containing all the information for the
	 *            student's performance on this test.
	 */
	public void editGrade(int studentIndex, int gradeIndex, Grade grade) {
		this.studentsList.get(studentIndex).getGrades().set(gradeIndex, grade);
		try {
			studentStore.updateFile(studentsList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Removes a grade from the student's grades list.
	 * 
	 * @param studentIndex
	 *            The index of the desired student. Expects a value within the
	 *            range 0 to studentsList.size()-1.
	 * @param gradeIndex
	 *            The index of the desired grade. Expects a value within the
	 *            range 0 to gradesList.size()-1.
	 */
	public void deleteGrade(int studentIndex, int gradeIndex) {
		this.studentsList.get(studentIndex).getGrades().remove(gradeIndex);
		try {
			studentStore.updateFile(studentsList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Clears all the Grades stored in the student's grades list.
	 * 
	 * @param studentIndex
	 *            The index of the desired student. Expects a value within the
	 *            range 0 to studentsList.size()-1.
	 */
	public void clearGrades(int studentIndex) {
		this.studentsList.get(studentIndex).getGrades().clear();
		try {
			studentStore.updateFile(studentsList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
