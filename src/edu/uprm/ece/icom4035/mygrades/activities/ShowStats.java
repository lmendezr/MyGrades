package edu.uprm.ece.icom4035.mygrades.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import edu.uprm.ece.icom4035.mygrades.R;
import edu.uprm.ece.icom4035.mygrades.adapters.SimpleStudentListAdapter;
import edu.uprm.ece.icom4035.mygrades.managers.GradesManager;
import edu.uprm.ece.icom4035.mygrades.managers.Test;

public class ShowStats extends Activity {

	private GradesManager gradesManager;
	private ListView listView;
	private SimpleStudentListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_test);

		// Show the Up button in the action bar.
		setupActionBar();

		populateDetails();
	}

	/**
	 * Obtain the test's index on the GradesManager's DLinkedList from the intent of
	 * the previous's activity.
	 * 
	 * @return the index of the desired test passed through the intent.
	 */
	public int getTestIndex() {
		return Integer.parseInt(getIntent().getStringExtra(
				MainActivity.StatsFragment.TEST_INDEX));
	}

	/**
	 * Populate the activity the with the information of the current test.
	 */
	public void populateDetails() {
		// A variable that will hold the rounded values.
		double roundOff;

		// Start the GradesManager so that the grade's information gets
		// displayed in the activity's window.
		gradesManager = new GradesManager();
		TextView textView = new TextView(this);

		Test currentTest = gradesManager.getAll().get(getTestIndex());

		// Display the test's information in the corresponding TextViews.
		textView = (TextView) findViewById(R.id.display_name);
		textView.setText(currentTest.getName());

		textView = (TextView) findViewById(R.id.display_num_students);
		textView.setText(((Integer) currentTest.getTimesTaken()).toString());

		textView = (TextView) findViewById(R.id.display_min);
		roundOff = Math.round(((Double) currentTest.getMin()) * 1000.0) / 1000.0;
		textView.setText(((Double) roundOff).toString());

		textView = (TextView) findViewById(R.id.display_max);
		roundOff = Math.round(((Double) currentTest.getMax()) * 1000.0) / 1000.0;
		textView.setText(((Double) roundOff).toString());

		textView = (TextView) findViewById(R.id.display_avg);
		roundOff = Math.round(((Double) currentTest.getAvg()) * 1000.0) / 1000.0;
		textView.setText(((Double) roundOff).toString());

		textView = (TextView) findViewById(R.id.display_std_dev);
		roundOff = Math.round(((Double) currentTest.getStdDev()) * 1000.0) / 1000.0;
		textView.setText(((Double) roundOff).toString());

		listView = (ListView) findViewById(R.id.grades_list);
		adapter = new SimpleStudentListAdapter(this,
				currentTest.getStudentsTaken());
		listView.setAdapter(adapter);

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
		getMenuInflater().inflate(R.menu.plain_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
