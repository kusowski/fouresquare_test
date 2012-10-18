package pl.kusowski.forsquate_test;

import java.util.ArrayList;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class FoursquareActivity extends Activity {
	private ArrayList<FsqVenue> venueList = new ArrayList<FsqVenue>();
	private FsqArrayAdapter adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_square);
        
        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
    	Criteria criteria = new Criteria();
    	criteria.setAccuracy(Criteria.ACCURACY_COARSE); 
		Location loc = null;
		
		try{
			String provider = lm.getBestProvider(criteria, true);
			loc = lm.getLastKnownLocation(provider);
		}catch (Exception e) {
			Log.i("Foursquare", e.getMessage());
        }
		
		
		if (loc != null){
			float lat = Fsq.round(loc.getLatitude(),1);
			float longi = Fsq.round(loc.getLongitude(),1);
			
			venueList = Fsq.getNearestVenue(lat, longi);
			adapter = new FsqArrayAdapter(this, R.layout.fsq_row, venueList);
			
			ListView lv = (ListView) this.findViewById(R.id.list);
			lv.setAdapter(adapter);
			
			lv.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long id) {
					Intent intent = new Intent(FoursquareActivity.this, VenueActivity.class);
					Bundle bundle = new Bundle();
					bundle.putParcelable("venue", venueList.get(position));
					intent.putExtras(bundle);
					startActivity(intent);
				}
			});
			
			
		}else{
			Toast.makeText(this, "Brak połączenia GPS", Toast.LENGTH_SHORT).show();
		}
    }
}