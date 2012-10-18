package pl.kusowski.forsquate_test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class VenueActivity extends Activity {
	private FsqVenue venue;
	private TextView header, address;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue);
        
        Bundle bundle = getIntent().getExtras();
        
        if (bundle != null && bundle.containsKey("venue")){
        	venue = (FsqVenue)bundle.get("venue");
        	
        	header = (TextView) findViewById(R.id.txt_header);
        	header.setText(venue.getName());
        	address = (TextView) findViewById(R.id.txt_address);
        	address.setText(getString(R.string.address)+ ": " +venue.getAddress());
        }
	}
}
