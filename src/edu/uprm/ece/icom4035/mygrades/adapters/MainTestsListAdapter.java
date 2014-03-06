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
import edu.uprm.ece.icom4035.mygrades.managers.Test;

public class MainTestsListAdapter extends BaseAdapter implements Filterable {
	private Context mContext;
	private SortedDoublyList<Test> mListTests;
	private SortedDoublyList<Test> mListTestsOriginal;
	private filter_here filter = new filter_here();

	public MainTestsListAdapter(Context context, SortedDoublyList<Test> list) {
		mContext = context;
		mListTests = list;
		mListTestsOriginal = list;
	}

	@Override
	public void notifyDataSetInvalidated() {
		this.mListTests = this.mListTestsOriginal;
		super.notifyDataSetInvalidated();
	}

	@Override
	public int getCount() {
		return mListTests.size();
	}

	@Override
	public Object getItem(int pos) {
		return mListTests.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return mListTests.get(pos).getID();
		// return pos;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		// get selected entry
		Test entry = mListTests.get(pos);

		// inflating list view layout if null
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.simple_row, null);
		}

		TextView textView = new TextView(mContext);

		// set name
		textView = (TextView) convertView
				.findViewById(R.id.display_item_name);
		textView.setText(entry.getName());

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
				Result.values = mListTests;
				Result.count = mListTests.size();
				return Result;
			}

			SortedDoublyList<Test> Filtered_Names = new SortedDoublyList<Test>();
			String filterString = constraint.toString().toLowerCase(
					Locale.getDefault());
			String filterableString;

			for (int i = 0; i < mListTests.size(); i++) {
				filterableString = mListTests.get(i).getName();
				if (filterableString.toLowerCase(Locale.getDefault()).contains(
						filterString)) {
					Filtered_Names.add(mListTests.get(i));
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
			mListTests = (SortedDoublyList<Test>) results.values;
			notifyDataSetChanged();
		}

	}

}
