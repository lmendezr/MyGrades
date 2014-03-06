package edu.uprm.ece.icom4035.mygrades.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import edu.uprm.ece.icom4035.mygrades.R;
import edu.uprm.ece.icom4035.mygrades.adapters.DetailedGradeListAdapter;
import edu.uprm.ece.icom4035.mygrades.managers.Student;
import edu.uprm.ece.icom4035.mygrades.managers.StudentManager;

public class ShowStudent extends Activity {

	public static final String STUDENT_INDEX = "student_index";

	private StudentManager studentManager;
	private ListView listView;
	private DetailedGradeListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_student);

		// Show the Up button in the action bar.
		setupActionBar();

		populateDetails();
	}

	/**
	 * Obtain the student's index on the StudentManager's DLinkedList from the
	 * intent of the previous's activity.
	 * 
	 * @return the index of the desired student passed through the intent.
	 */
	public int getStudentIndex() {
		return Integer.parseInt(getIntent().getStringExtra(
				MainActivity.StudentsFragment.STUDENT_INDEX));
	}

	/**
	 * Populate the activity the with the information of the current student.
	 */
	public void populateDetails() {
		
		// Start the StudentManager so that the student information gets
		// displayed in the activity's window.
		studentManager = new StudentManager();
		TextView textView = new TextView(this);

		Student currentStudent = studentManager.getAll().get(getStudentIndex());

		// Display the student's information in the corresponding TextViews.
		textView = (TextView) findViewById(R.id.display_full_name);
		textView.setText(currentStudent.getFirstName() + " "
				+ currentStudent.getLastName());

		listView = (ListView) findViewById(R.id.grades_list);
		adapter = new DetailedGradeListAdapter(this, studentManager.getAll()
				.get(getStudentIndex()).getGrades());
		listView.setAdapter(adapter);
	}

	/**
	 * Starts the editStudent activity for the selected student in the
	 * DLinkedList.
	 * 
	 * @param studentIndex
	 *            the student's index in the DLinkedList
	 */
	public void editStudent(int studentIndex) {
		Intent intent = new Intent(this, EditStudent.class);
		String message = ((Integer) studentIndex).toString();
		intent.putExtra(STUDENT_INDEX, message);
		startActivityForResult(intent, 1);
	}

	/**
	 * Re-populate the activity in order to update any changes on the ListViews.
	 */
	@Override
	protected void onResume() {
		populateDetails();
		super.onResume();
	}

	/*
	 * Calls finish() on this activity if an activity that was started from this
	 * one sets it's result as 1.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == 1) {
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
		getMenuInflater().inflate(R.menu.edit_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_edit_student:
			editStudent(getStudentIndex());
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
