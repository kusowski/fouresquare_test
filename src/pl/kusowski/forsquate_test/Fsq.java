package pl.kusowski.forsquate_test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.util.Log;

public class Fsq {
	private static final String URL = "https://api.foursquare.com/v2/venues/search?",
			CLIENT_ID = "TWBZUMCNFY0VRPPAVDVSVC1LD2MKUIL3FKRDHJCZSHMFHF5W",
			CLIENT_SECRET = "L5DM41WS4F0X4RXTHXKKW5AXNIQSDOJLM4HG24KXJE12DSLV",
			TOKEN = "&client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&v=20121015";
	
	
	public static ArrayList<FsqVenue> getNearestVenue(Float latitude, Float longitude){
		String final_url = URL + "ll=" + latitude.toString() + "," + longitude.toString() + TOKEN;
		Log.i("Fsq", final_url);
		String data = getDataFromUrl(final_url);
		ArrayList<FsqVenue> venueList = new ArrayList<FsqVenue>();
		
		try {
			JSONObject jsonObj 	= (JSONObject) new JSONTokener(data).nextValue();
			JSONArray groups	= (JSONArray) jsonObj.getJSONObject("response").getJSONArray("venues");
			
			for(int x=0; x < groups.length(); x++){
				JSONObject jVenue = (JSONObject) groups.get(x);
				JSONObject location = (JSONObject) jVenue.get("location");
				String address;
				
				try{
					address = location.getString("address");
				}catch (Exception e) {
					address = "Brak adresu";
				}
				
				FsqVenue venue = new FsqVenue(jVenue.getString("id"), jVenue.getString("name"), address);
				venueList.add(venue);
				
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return venueList;
	}
	
	private static String getDataFromUrl(String url) {
        String data = null;
 
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
 
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            data = EntityUtils.toString(httpEntity);
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return data;
    }
	
	static public float round(double d, int ic) {
    	NumberFormat nf = NumberFormat.getInstance();
    	nf.setMaximumFractionDigits(ic);
    	nf.setMinimumFractionDigits(ic);
    	return Float.parseFloat((nf.format(d)).replaceAll(",", ".").replaceAll(" ", "") );
    }
}
