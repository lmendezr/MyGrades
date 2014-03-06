package edu.uprm.ece.icom4035.mygrades.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import edu.uprm.ece.icom4035.mygrades.R;
import edu.uprm.ece.icom4035.mygrades.managers.Grade;
import edu.uprm.ece.icom4035.mygrades.managers.SortedDoublyList;

public class DetailedGradeListAdapter extends BaseAdapter {
	private Context mContext;
	private SortedDoublyList<Grade> mListGrades;

	public DetailedGradeListAdapter(Context context,
			SortedDoublyList<Grade> list) {
		mContext = context;
		mListGrades = list;
	}

	@Override
	public int getCount() {
		return mListGrades.size();
	}

	@Override
	public Object getItem(int pos) {
		return mListGrades.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		// get selected entry
		Grade entry = mListGrades.get(pos);

		// inflating list view layout if null
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.detailed_grade_row, null);
		}

		TextView textView = new TextView(mContext);

		// set name
		textView = (TextView) convertView.findViewById(R.id.display_test_name);
		textView.setText(entry.getGradeName());

		// set grade
		textView = (TextView) convertView
				.findViewById(R.id.display_test_score);
		textView.setText(((Double) entry.getScore()).toString());

		return convertView;
	}

}
