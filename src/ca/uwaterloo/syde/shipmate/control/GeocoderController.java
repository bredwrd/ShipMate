package ca.uwaterloo.syde.shipmate.control;

import ca.uwaterloo.syde.shipmate.entity.PostalCode;
import android.location.Geocoder;

public class GeocoderController {
	
	public GeocoderController(){
		
	}
	
	public PostalCode getPostalCode(){
		
		return new PostalCode("");
	}
	
	private double getLongitude(){
		
		return 0;
	}
	
	private double getLatitude(){
		
		return 0;
	}
}
