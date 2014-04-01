package ca.uwaterloo.syde.shipmate;

import java.io.IOException;

import ca.uwaterloo.syde.shipmate.control.DataUpdaterService;
import ca.uwaterloo.syde.shipmate.control.DeliveryStandardCalculator;
import ca.uwaterloo.syde.shipmate.control.DomesticLettermailDeliveryStandardCalculator;
import ca.uwaterloo.syde.shipmate.control.GeocoderController;
import ca.uwaterloo.syde.shipmate.entity.PostalCode;
import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class HomeActivity extends Activity {
	//TODO TextView updateStatusText;
	DataUpdaterService dataUpdaterService = new DataUpdaterService();
	DeliveryStandardCalculator deliveryStandardCalculator;
	GeocoderController geocodercontroller = new GeocoderController();
	
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.home_layout);
	}
	public void AddressButton_OnClick(View v) {
		//String address_text = geocodercontroller.getPostalCode();
		if (v.getId() == R.id.to_button){
			EditText tv2 = (EditText)findViewById(R.id.to_postal_code);
			//tv2.setText(address_text.toString());
			tv2.setText("M1N 2P1");
		} else if (v.getId() == R.id.from_button){
			EditText tv2 = (EditText)findViewById(R.id.from_postal_code);
			//tv2.setText(address_text.toString());
			tv2.setText("L3T 2H5");
		}
		
	}
}
