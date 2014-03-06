package edu.uprm.ece.icom4035.mygrades.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import edu.uprm.ece.icom4035.mygrades.R;
import edu.uprm.ece.icom4035.mygrades.managers.SortedDoublyList;
import edu.uprm.ece.icom4035.mygrades.managers.Student;

public class SimpleStudentListAdapter extends BaseAdapter {
	private Context mContext;
	private SortedDoublyList<Student> mListStudents;

	public SimpleStudentListAdapter(Context context,
			SortedDoublyList<Student> list) {
		mContext = context;
		mListStudents = list;
	}

	@Override
	public int getCount() {
		return mListStudents.size();
	}

	@Override
	public Object getItem(int pos) {
		return mListStudents.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return mListStudents.get(pos).getID();
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		// get selected entry
		Student entry = mListStudents.get(pos);

		// inflating list view layout if null
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.detailed_student_row, null);
		}

		TextView textView = new TextView(mContext);

		// set name
		textView = (TextView) convertView
				.findViewById(R.id.display_item_name);
		textView.setText(entry.getFirstName().toUpperCase() + " "
				+ entry.getLastName().toUpperCase());

		// set grade
		textView = (TextView) convertView
				.findViewById(R.id.display_student_grade);
		textView.setText(((Double) entry.getGrades().get(0).getScore())
				.toString());

		return convertView;
	}

}
