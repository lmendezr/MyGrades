package edu.uprm.ece.icom4035.mygrades.activities;

import edu.uprm.ece.icom4035.mygrades.R;
import edu.uprm.ece.icom4035.mygrades.managers.Grade;
import edu.uprm.ece.icom4035.mygrades.managers.StudentManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddGrade extends Activity {

	public static final String STUDENT_INDEX = "student_index";

	private StudentManager studentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_grade);

		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Obtain the student's index on the DLinkedList from the intent of the
	 * previous's activity.
	 * 
	 * @return the index of the desired student.
	 */
	public int getStudentIndex() {
		return Integer.parseInt(getIntent().getStringExtra(
				EditStudent.STUDENT_INDEX));
	}

	/**
	 * Handle the action after the user presses the save button on the
	 * activity's menu bar.
	 */
	public void saveGrade() {

		// Start the Student Manager so that the new grade gets written to the
		// respective student in the program's file.
		studentManager = new StudentManager();

		// Assign each EditText to the variables that hold each field on the new
		// grade.
		EditText gradeName = (EditText) findViewById(R.id.edit_grade_name_field);
		EditText gradeScore = (EditText) findViewById(R.id.edit_grade_score_field);

		// Handle (with AlertDialog) the situation that occurs if the user
		// doesn't fill the necessary fields.
		if (gradeName.getText().toString().isEmpty()
				|| gradeScore.getText().toString().isEmpty()) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.dialog_title_save_grade);
			builder.setMessage(R.string.dialog_message_save_grade);
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
			// in a new grade in the selected student.
			Grade newGrade = new Grade(gradeName.getText().toString(),
					Double.parseDouble(gradeScore.getText().toString()));
			studentManager.addGrade(getStudentIndex(), newGrade);
			Toast.makeText(getApplicationContext(), R.string.toast_grade_saved,
					Toast.LENGTH_SHORT).show();
			finish();
		}
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
			saveGrade();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
