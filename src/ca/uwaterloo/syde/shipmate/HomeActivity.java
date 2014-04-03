package ca.uwaterloo.syde.shipmate;

import ca.uwaterloo.syde.shipmate.control.DataUpdaterService;
import ca.uwaterloo.syde.shipmate.control.DeliveryStandardCalculator;
import ca.uwaterloo.syde.shipmate.control.DomesticLettermailDeliveryStandardCalculator;
import ca.uwaterloo.syde.shipmate.control.GPSTracker;
import ca.uwaterloo.syde.shipmate.entity.PostalCode;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomeActivity extends Activity {
	//TODO TextView updateStatusText;
	DataUpdaterService dataUpdaterService = new DataUpdaterService();
	DeliveryStandardCalculator deliveryStandardCalculator;
	GPSTracker gpsTracker = new GPSTracker(this);
	
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.home_layout);
		Button runButton = (Button) findViewById(R.id.run_button);
		runButton.setOnClickListener(new Button.OnClickListener() {  
	        public void onClick(View v)
            {
	        	EditText toPostalCodeField = (EditText) findViewById(R.id.to_postal_code);
	        	EditText fromPostalCodeField = (EditText) findViewById(R.id.from_postal_code);
	        	PostalCode toPostalCode = new PostalCode(toPostalCodeField.getText().toString());
	        	PostalCode fromPostalCode = new PostalCode(fromPostalCodeField.getText().toString());
	        	deliveryStandardCalculator = new DomesticLettermailDeliveryStandardCalculator(toPostalCode, fromPostalCode);
            }
         });
	}
	public void AddressButton_OnClick(View v) {
		gpsTracker.getLocation();
		gpsTracker.getLongitude();
		gpsTracker.getLatitude();
		String address_text = gpsTracker.getGeocoderAddress(this).get(0).getPostalCode();
		if (v.getId() == R.id.to_button){
			EditText tv2 = (EditText)findViewById(R.id.to_postal_code);
			tv2.setText(address_text);
			
		} else if (v.getId() == R.id.from_button){
			EditText tv2 = (EditText)findViewById(R.id.from_postal_code);
			tv2.setText(address_text);
		}
		
	}
}
