package edu.uprm.ece.icom4035.mygrades.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import edu.uprm.ece.icom4035.mygrades.R;
import edu.uprm.ece.icom4035.mygrades.adapters.SimpleGradeListAdapter;
import edu.uprm.ece.icom4035.mygrades.managers.Student;
import edu.uprm.ece.icom4035.mygrades.managers.StudentManager;

public class EditStudent extends Activity {

	public static final String STUDENT_INDEX = "student_index";
	public static final String GRADE_INDEX = "grade_index";

	private StudentManager studentManager;
	private ListView listView;
	private SimpleGradeListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_student);
		// Show the Up button in the action bar.
		setupActionBar();

		populateDetails();
		populateGrades();
	}

	/**
	 * Obtain the student's index on the DLinkedList from the intent of the
	 * previous's activity.
	 * 
	 * @return the index of the desired student passed through the intent.
	 */
	public int getStudentIndex() {
		return Integer.parseInt(getIntent().getStringExtra(
				ShowStudent.STUDENT_INDEX));
	}

	/**
	 * Populate the activity the contact's current information
	 */
	public void populateDetails() {

		// Start the Student Manager so that the student information gets
		// displayed in the activity's window.
		studentManager = new StudentManager();
		TextView textView = new TextView(this);

		Student currentStudent = studentManager.getAll().get(getStudentIndex());

		// Display the student's information in the corresponding TextViews.
		textView = (TextView) findViewById(R.id.edit_first_name_field);
		textView.setText(currentStudent.getFirstName());

		textView = (TextView) findViewById(R.id.edit_last_name_field);
		textView.setText(currentStudent.getLastName());
	}

	/**
	 * Populate the activity the all of the contact's current grades.
	 */
	public void populateGrades() {

		// Start the Student Manager so that the student information gets
		// displayed in the activity's window.
		studentManager = new StudentManager();

		listView = (ListView) findViewById(R.id.edit_grades_list);

		// Fill the ArrayAdapter and ListView items with all the information on
		// the contact's grades DLinkedList.
		adapter = new SimpleGradeListAdapter(this, studentManager.getAll()
				.get(getStudentIndex()).getGrades());
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				editGrade(getStudentIndex(), position);
			}
		});
	}

	/**
	 * Handle the action after the user presses the save button on the
	 * activity's menu bar.
	 */
	public void saveStudent() {

		// Assign each EditText to the variables that hold each field on the new
		// student.
		EditText firstName = (EditText) findViewById(R.id.edit_first_name_field);
		EditText lastName = (EditText) findViewById(R.id.edit_last_name_field);

		// Create a student with the new information and all of it's grades.
		Student newStudent = new Student(firstName.getText().toString(),
				lastName.getText().toString(), studentManager.getAll()
						.get(getStudentIndex()).getGrades());

		// Handle (with AlertDialog) the situation that occurs if the user
		// doesn't fill the necessary fields.
		if (firstName.getText().toString().isEmpty()
				|| lastName.getText().toString().isEmpty()) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.dialog_title_save_student);
			builder.setMessage(R.string.dialog_message_save_student);
			builder.setNeutralButton(R.string.dialog_confirm,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});
			AlertDialog alert = builder.create();
			alert.show();
		} else {
			// Every field was correctly filled, proceed saving the information
			// in the selected student in the program's file.
			studentManager.editStudent(getStudentIndex(), newStudent);

			// Verify the new student's position in the DLinkedList in order to
			// start the correct ShowStudent activity.
			int studentPosition = studentManager
					.getStudentPosition((int) adapter
							.getItemId(getStudentIndex()));
			Toast.makeText(getApplicationContext(),
					R.string.toast_student_saved, Toast.LENGTH_SHORT).show();
			startViewStudent(studentPosition);

			// Sends the signal to call finish() on the previous ShowStudent
			// activity (with the old or wrong student's index)
			setResult(1);
			finish();
		}
	}

	/**
	 * Handle the action after the user presses the "Add Grade" button on the
	 * activity's window.
	 * 
	 * @param view
	 *            the view of the button's parent activity
	 */
	public void addGrade(View view) {
		Intent intent = new Intent(this, AddGrade.class);
		String message = ((Integer) getStudentIndex()).toString();
		intent.putExtra(STUDENT_INDEX, message);
		startActivity(intent);
	}

	/**
	 * Starts the editGrade activity for the selected grade in the current
	 * student.
	 * 
	 * @param studentIndex
	 *            the student's index in the DLinkedList
	 * @param gradeIndex
	 *            the grade's index in the student's DLinkedList
	 */
	public void editGrade(int studentIndex, int gradeIndex) {
		Intent intent = new Intent(this, EditGrade.class);
		String message = ((Integer) studentIndex).toString();
		String message2 = ((Integer) gradeIndex).toString();
		intent.putExtra(STUDENT_INDEX, message);
		intent.putExtra(GRADE_INDEX, message2);
		startActivity(intent);
	}

	/**
	 * Handle the action after the user presses the "Delete Student" button on
	 * the activity's window.
	 * 
	 * @param view
	 *            the view of the button's parent activity
	 */
	public void deleteStudent(View view) {

		// Confirms (with AlertDialog) whether the user really wants to delete
		// the student.
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.dialog_title_delete);
		builder.setMessage(R.string.dialog_message_delete_student);
		builder.setNegativeButton(R.string.dialog_deny,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		builder.setPositiveButton(R.string.dialog_affirm,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						studentManager.deleteStudent(getStudentIndex());
						Toast.makeText(getApplicationContext(),
								R.string.toast_student_deleted,
								Toast.LENGTH_SHORT).show();

						// Sends the signal to call finish() on the previous
						// ShowStudent activity (A.K.A. go back to the
						// MainActivity)
						setResult(1);
						finish();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();

	}

	/**
	 * Starts the ViewStudent activity with the desired student.
	 * 
	 * @param studentIndex
	 *            the student's index in the DLinkedList.
	 */
	public void startViewStudent(int studentIndex) {
		Intent intent = new Intent(this, ShowStudent.class);
		String message = ((Integer) studentIndex).toString();
		intent.putExtra(STUDENT_INDEX, message);
		startActivity(intent);
	}

	/**
	 * Re-populate the activity in order to update any changes on the ListViews.
	 */
	@Override
	protected void onResume() {
		populateGrades();
		super.onResume();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.save_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		case R.id.action_accept:
			saveStudent();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
