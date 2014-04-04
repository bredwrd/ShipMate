package ca.uwaterloo.syde.shipmate;

import ca.uwaterloo.syde.shipmate.control.DeliveryStandardCalculator;
import ca.uwaterloo.syde.shipmate.control.DomesticLettermailDeliveryStandardCalculator;
import ca.uwaterloo.syde.shipmate.control.GPSTracker;
import ca.uwaterloo.syde.shipmate.entity.PostalCode;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * HomeActivity
 * 
 *
 */
public class HomeActivity extends Activity {
	private DeliveryStandardCalculator deliveryStandardCalculator;
	private GPSTracker gpsTracker;
	private ProgressBar progress;
	private EditText editText;
	
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.home_layout);
		
		// Initiate location service.
		gpsTracker = new GPSTracker(this);
		
		// On run_button click, submit origin and destination for processing and display result.
		Button runButton = (Button) findViewById(R.id.run_button);
		runButton.setOnClickListener(new Button.OnClickListener() {  
	        public void onClick(View v)
            {
	        	// Fetch postal code keys from user input.
	        	EditText toPostalCodeField = (EditText) findViewById(R.id.to_postal_code);
	        	EditText fromPostalCodeField = (EditText) findViewById(R.id.from_postal_code);
	        	
	        	// Submit origin and destination for processing.
	        	PostalCode toPostalCode = new PostalCode(toPostalCodeField.getText().toString());
	        	PostalCode fromPostalCode = new PostalCode(fromPostalCodeField.getText().toString());
	        	deliveryStandardCalculator = new DomesticLettermailDeliveryStandardCalculator(toPostalCode, fromPostalCode);
	        	TextView resultTextView = (TextView)findViewById(R.id.runResults);
	        	
	        	// Get and display result.
	        	resultTextView.setText("Delivery Standard: " + deliveryStandardCalculator.getDeliveryStandard());
            }
         });
	}
	
	/**
	 * 
	 * @param v
	 * @author Frank Alfieri
	 */
	public void AddressButton_OnClick(final View v) {
		final Context context = this;
		Thread thread = new Thread(){
			String address_text;
	        public void run() {
				gpsTracker.getLocation();
				gpsTracker.getLongitude();
				gpsTracker.getLatitude();
			    address_text = gpsTracker.getGeocoderAddress(context).get(0).getPostalCode();
	        	runOnUiThread(new Runnable() {
					public void run() {
						//Set text
						editText.setText(address_text);
						//stop spinning wheel
				        progress.setVisibility(View.INVISIBLE);
					}
	            });
	        }
        };
        if (v.getId() == R.id.to_button){
			editText = (EditText)findViewById(R.id.to_postal_code);
        	progress = (ProgressBar) findViewById(R.id.to_gpsStatusProgress);
        	
        } else if (v.getId() == R.id.from_button){
			editText = (EditText)findViewById(R.id.from_postal_code);
        	progress = (ProgressBar) findViewById(R.id.from_gpsStatusProgress);
        }
		// Start spinning wheel.
        progress.setVisibility(View.VISIBLE);
        thread.start();
	}
}
