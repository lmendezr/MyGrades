package edu.uprm.ece.icom4035.mygrades.activities;

import edu.uprm.ece.icom4035.mygrades.R;
import edu.uprm.ece.icom4035.mygrades.managers.Grade;
import edu.uprm.ece.icom4035.mygrades.managers.Student;
import edu.uprm.ece.icom4035.mygrades.managers.StudentManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditGrade extends Activity {

	private StudentManager studentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_grade);

		// Show the Up button in the action bar.
		setupActionBar();

		populateDetails();
	}

	/**
	 * Obtain the student's index on the DLinkedList from the intent of the
	 * previous's activity.
	 * 
	 * @return the index of the desired student passed through the intent.
	 */
	public int getStudentIndex() {
		return Integer.parseInt(getIntent().getStringExtra(
				EditStudent.STUDENT_INDEX));
	}

	/**
	 * Obtain the grade's index on the student's DLinkedList from the intent of
	 * the previous's activity.
	 * 
	 * @return the index of the desired grade passed through the intent.
	 */
	public int getGradeIndex() {
		return Integer.parseInt(getIntent().getStringExtra(
				EditStudent.GRADE_INDEX));
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
		textView = (TextView) findViewById(R.id.edit_grade_name_field);
		textView.setText(currentStudent.getGrades().get(getGradeIndex())
				.getGradeName());

		textView = (TextView) findViewById(R.id.edit_grade_score_field);
		textView.setText(((Double) currentStudent.getGrades()
				.get(getGradeIndex()).getScore()).toString());
	}

	/**
	 * Handle the action after the user presses the save button on the
	 * activity's menu bar.
	 */
	public void saveGrade() {

		// Assign each EditText to the variables that hold each field on the
		// grade's information.
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
			// Every field was correctly filled, proceed saving the new
			// information in the selected grade in the selected student.
			Grade newGrade = new Grade(gradeName.getText().toString(),
					Double.parseDouble(gradeScore.getText().toString()));
			studentManager.editGrade(getStudentIndex(), getGradeIndex(),
					newGrade);
			Toast.makeText(getApplicationContext(), R.string.toast_grade_saved,
					Toast.LENGTH_SHORT).show();
			finish();
		}
	}

	/**
	 * Handle the action after the user presses the delete button on the
	 * activity's menu bar.
	 * 
	 * @param view
	 *            the view of the button's parent activity
	 */
	public void deleteGrade(View view) {

		// Confirms (with AlertDialog) whether the user really wants to delete
		// the grade.
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.dialog_title_delete);
		builder.setMessage(R.string.dialog_message_delete_grade);
		builder.setNegativeButton(R.string.dialog_deny,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		builder.setPositiveButton(R.string.dialog_affirm,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						studentManager.deleteGrade(getStudentIndex(),
								getGradeIndex());
						Toast.makeText(getApplicationContext(),
								R.string.toast_grade_deleted,
								Toast.LENGTH_SHORT).show();
						finish();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
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
