package pl.kusowski.forsquate_test;

import java.util.List;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FsqArrayAdapter extends ArrayAdapter<FsqVenue> {
	private final int TYPE_COUNT = 1, TYPE = 0;
	private LayoutInflater inflater;
	private List<FsqVenue> list;
	private Context context;
	private TextView name;
	
	public FsqArrayAdapter(Context context, int textViewResourceId, List<FsqVenue> list){
		super(context, textViewResourceId, list);
		this.context = context;
		this.list = list;
		inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public int getCount() {
		return this.list.size();
	}
	
	@Override
    public int getItemViewType(int position) {
        return TYPE;
    }

	public FsqVenue getItem(int index) {
		return this.list.get(index);
	}
	
	 @Override
     public int getViewTypeCount() {
         return TYPE_COUNT;
     }
	 
	 public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			// Get item
			FsqVenue fsqVenue = getItem(position);
			Log.i("Adapter", fsqVenue.getName());
			row = inflater.inflate(R.layout.fsq_row, parent, false);
			name = (TextView) row.findViewById(R.id.title);
			name.setText(fsqVenue.getName());
			
			return row;
		}
}
