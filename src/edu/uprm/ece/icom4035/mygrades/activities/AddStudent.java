package edu.uprm.ece.icom4035.mygrades.activities;

import edu.uprm.ece.icom4035.mygrades.R;
import edu.uprm.ece.icom4035.mygrades.managers.Student;
import edu.uprm.ece.icom4035.mygrades.managers.StudentManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudent extends Activity {

	public static final String STUDENT_INDEX = "student_index";

	private StudentManager studentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_student);

		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Handle the action after the user presses the save button on the
	 * activity's menu bar.
	 */
	public void saveStudent() {

		// Start the Student Manager so that the new student gets written to the
		// program's file.
		studentManager = new StudentManager();

		// Assign each EditText to the variables that hold each field on the new
		// student.
		EditText firstName = (EditText) findViewById(R.id.edit_first_name_field);
		EditText lastName = (EditText) findViewById(R.id.edit_last_name_field);
		Student newStudent = new Student(-1, firstName.getText().toString(),
				lastName.getText().toString());

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
			// in a new student in the program's file.
			studentManager.addStudent(newStudent);
			Toast.makeText(getApplicationContext(),
					R.string.toast_student_saved, Toast.LENGTH_SHORT).show();
			
			// Starts the ViewStudent activity right after saving the
			// information with a temporary dummy position.
			startViewStudent(studentManager.getStudentPosition(-1));
			finish();
		}
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
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_accept:
			saveStudent();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
