package ca.uwaterloo.syde.shipmate.control;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import ca.uwaterloo.syde.shipmate.entity.PostalCode;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

public class GeocoderController {
	public GeocoderController(){
		
	}
	
	public String getPostalCode(Context context) {
		double lng = getLong(context);
		double lat = getLat(context);
		List<Address> addresses = null;
		Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);
		try {
			addresses = geocoder.getFromLocation(lng, lat, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addresses.get(0).toString();
	}
	
	private double getLong(Context context){
		LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE); 
		Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		double longitude = location.getLongitude();
		return longitude;
	}
	private double getLat(Context context){
		LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE); 
		Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		double latitude = location.getLatitude();
		return latitude;
	}
}
