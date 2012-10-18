package pl.kusowski.forsquate_test;

import android.os.Parcel;
import android.os.Parcelable;

public class FsqVenue implements Parcelable{
	private String id, name, address;
	
	public FsqVenue(String id, String name){
		this.id = id;
		this.name = name;
	}
	
	public FsqVenue(String id, String name, String address){
		this.id = id;
		this.name = name;
		this.address = address;
	}
	
	public FsqVenue(Parcel in) {
		id = in.readString();
		name = in.readString();
		address = in.readString();
	}

	public String getId(){
		return this.id;
	}
	public String getName(){
		return this.name;
	}
	public String getAddress(){
		return this.address;
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(name);
		dest.writeString(address);
		
	}
	
	// this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<FsqVenue> CREATOR = new Parcelable.Creator<FsqVenue>() {
        public FsqVenue createFromParcel(Parcel in) {
            return new FsqVenue(in);
        }

        public FsqVenue[] newArray(int size) {
            return new FsqVenue[size];
        }
    };
}
