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
	Context context;
	public GeocoderController(){
		
	}
	
	public String getPostalCode() {
		//double lng = getLong();
		//double lat = getLat();
		List<Address> addresses = null;
		Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);
		try {
			addresses = geocoder.getFromLocation(49.891, -97.168, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addresses.get(0).toString();
	}
	
	private double getLong(){
		LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE); 
		Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		double longitude = location.getLongitude();
		return longitude;
	}
	private double getLat(){
		LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE); 
		Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		double latitude = location.getLatitude();
		return latitude;
	}
}
