package edu.uprm.ece.icom4035.mygrades.adapters;

import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import edu.uprm.ece.icom4035.mygrades.R;
import edu.uprm.ece.icom4035.mygrades.managers.SortedDoublyList;
import edu.uprm.ece.icom4035.mygrades.managers.Student;

public class MainStudentsListAdapter extends BaseAdapter implements Filterable {
	private Context mContext;
	private SortedDoublyList<Student> mListStudents;
	private SortedDoublyList<Student> mListStudentsOriginal;
	private filter_here filter = new filter_here();

	public MainStudentsListAdapter(Context context, SortedDoublyList<Student> list) {
		mContext = context;
		mListStudents = list;
		mListStudentsOriginal = list;
	}

	@Override
	public void notifyDataSetInvalidated() {
		this.mListStudents = this.mListStudentsOriginal;
		super.notifyDataSetInvalidated();
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
			convertView = inflater.inflate(R.layout.simple_row, null);
		}

		TextView textView = new TextView(mContext);

		// set name
		textView = (TextView) convertView
				.findViewById(R.id.display_item_name);
		textView.setText(entry.getFirstName() + " " + entry.getLastName());
		
		return convertView;
	}

	@Override
	public Filter getFilter() {
		return this.filter;
	}

	public class filter_here extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {

			FilterResults Result = new FilterResults();
			// if constraint is empty return the original names
			if (constraint == null || constraint.length() == 0) {
				Result.values = mListStudents;
				Result.count = mListStudents.size();
				return Result;
			}

			SortedDoublyList<Student> Filtered_Names = new SortedDoublyList<Student>();
			String filterString = constraint.toString().toLowerCase(
					Locale.getDefault());
			String filterableString, filterableString2;

			for (int i = 0; i < mListStudents.size(); i++) {
				filterableString = mListStudents.get(i).getFirstName();
				filterableString2 = mListStudents.get(i).getLastName();
				if (filterableString.toLowerCase(Locale.getDefault()).contains(
						filterString)
						|| filterableString2.toLowerCase(Locale.getDefault())
								.contains(filterString)) {
					Filtered_Names.add(mListStudents.get(i));
				}
			}
			Result.values = Filtered_Names;
			Result.count = Filtered_Names.size();

			return Result;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			mListStudents = (SortedDoublyList<Student>) results.values;
			notifyDataSetChanged();
		}

	}

}
