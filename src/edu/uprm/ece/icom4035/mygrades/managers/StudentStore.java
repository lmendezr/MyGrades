package edu.uprm.ece.icom4035.mygrades.managers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import android.os.Environment;

/**
 * StudentStorage is the file manager of Grades Roll Book.
 * 
 * <h4>Description</h4>
 * 
 * StudentStore provides support for storage in Android's external storage
 * directory. It's main purpose is to load Students and their Grades from a text
 * file into a SortedDoublyList and store Students from this SortedDoublyList
 * back to the same text file.
 * 
 * @author Lixhjideny Méndez Ríos
 * 
 * @version 1.0
 * 
 */

public class StudentStore {

	// Instance variables
	public static final File cartridgeSlot = Environment
			.getExternalStorageDirectory();
	// public static final String cartridgeSlot = "E:/";
	public static final File cartridge = new File(cartridgeSlot,
			"/MyGrades.txt");
	private int readStudentsAmount;

	// Dummy Constructor
	public StudentStore() {
		readStudentsAmount = 0;
	}

	/**
	 * Loads the Students found in the program's storage text file into a
	 * SortedDoublyList.
	 * 
	 * @return A SortedDoublyList containing Students.
	 */
	public SortedDoublyList<Student> loadStudents() throws IOException {
		PrintWriter out = null;
		if (!cartridge.exists()) {
			cartridge.createNewFile();
			out = new PrintWriter(cartridge);
			out.println(readStudentsAmount);
			out.close();
			return new SortedDoublyList<Student>();
		}

		Scanner scanner = new Scanner(cartridge);
		if (!scanner.hasNext()) {
			out = new PrintWriter(cartridge);
			out.println(readStudentsAmount);
			out.close();
			scanner.close();
			return new SortedDoublyList<Student>();
		}

		SortedDoublyList<Student> storedStudents;
		this.readStudentsAmount = Integer.parseInt(scanner.nextLine());
		storedStudents = new SortedDoublyList<Student>();

		for (int i = 0; i < readStudentsAmount; ++i) {

			String firstName = scanner.nextLine();
			String lastName = scanner.nextLine();

			int gradesAmount = Integer.parseInt(scanner.nextLine());
			SortedDoublyList<Grade> grades = new SortedDoublyList<Grade>();

			for (int k = 0; k < gradesAmount; ++k) {
				String testName = scanner.nextLine();
				Double grade = Double.parseDouble(scanner.nextLine());
				grades.add(new Grade(testName, grade));
			}

			storedStudents.add(new Student(i, firstName, lastName, grades));
		}
		scanner.close();
		return storedStudents;
		// return new SortedDoublyList<Student>();
	}

	// Setter
	/**
	 * Writes Students and their Grades from a SortedDoublyList to the program's
	 * storage text file.
	 * 
	 * @param students
	 *            A SortedDoublyList containing Students.
	 */
	public void updateFile(SortedDoublyList<Student> students)
			throws IOException {
		if (students == null)
			throw new IllegalArgumentException(
					"The Sorted Array List shouldn't be null.");

		PrintWriter out = new PrintWriter(cartridge);
		out.println(students.size());
		for (int i = 0; i < students.size(); ++i) {

			out.println(students.get(i).getFirstName());
			out.println(students.get(i).getLastName());

			out.println(students.get(i).getTotalGrades());
			for (int k = 0; k < students.get(i).getTotalGrades(); ++k) {
				out.println(students.get(i).getGrades().get(k).getGradeName());
				out.println(students.get(i).getGrades().get(k).getScore());
			}
		}
		out.close();
	}
}
